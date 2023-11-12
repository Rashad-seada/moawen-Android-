package com.example.marketapp.features.wallet.view.viewmodel.wallet

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketapp.R
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.destinations.SelectLocationScreenDestination
import com.example.marketapp.destinations.WalletMessageScreenDestination
import com.example.marketapp.features.order.infrastructure.api.requests.MakeOrderStep1Input
import com.example.marketapp.features.order.view.viewmodel.order.OrderViewModel
import com.example.marketapp.features.wallet.domain.usecase.ChargeBalanceUseCase
import com.example.marketapp.features.wallet.domain.usecase.GetBalanceUseCase
import com.example.marketapp.features.wallet.view.screens.WalletMessageScreen
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WalletViewModel @Inject constructor(
    private val chargeBalanceUseCase: ChargeBalanceUseCase,
    private val getBalanceUseCase: GetBalanceUseCase
) : ViewModel() {

    var state by mutableStateOf (WalletState())
    private var job : Job? = null

    fun onEvent(event: WalletEvent){
        when(event){
            is WalletEvent.OnBalanceRecharge -> {
                onBalanceRecharge(event.navigator,event.context)
            }
            is WalletEvent.OnGetBalance -> {
                onGetBalance(event.context)
            }
        }
    }

    fun updateImageState(image: Uri?) {
        if(image != null){
            state = state.copy(moneyTransferPhoto = image)
        }
    }

    private fun onGetBalance(context: Context) {
        if (job == null){
            state = state.copy(balanceError = null)

            job = viewModelScope.launch(Dispatchers.IO) {
                state = state.copy(balanceIsLoading = true)
                val response = getBalanceUseCase(
                    (CoreViewModel.user?.token ?: ""),
                    context,
                )
                state = state.copy(balanceIsLoading = false)

                if(response.failure != null) {
                    state = state.copy(balanceError = response.failure.message)

                } else {
                    state = state.copy(balance = response.data?.data?.wallet?.balance?.toDouble()?: 0.0)
                }
            }
            job = null
        }
    }

    private fun onBalanceRecharge(navigator: DestinationsNavigator, context: Context) {

        if(state.moneyTransferPhoto == null) {
            state = state.copy(chargeBalanceError = context.getString(R.string.select_image))
            return
        }

        if (job == null){
            state = state.copy(chargeBalanceError = null)

            job = viewModelScope.launch(Dispatchers.IO) {
                state = state.copy(chargeBalanceIsLoading = true)
                val response = chargeBalanceUseCase(
                    CoreViewModel.user!!.token,
                    state.moneyTransferPhoto!!,
                    context,
                )
                state = state.copy(chargeBalanceIsLoading = false)

                if(response.failure != null) {
                    CoreViewModel.showSnackbar(("Error:" + response.failure.message))

                } else {
                    viewModelScope.launch(Dispatchers.Main) {
                        navigator.navigate(WalletMessageScreenDestination)
                    }

                }
            }
            job = null
        }
    }



}