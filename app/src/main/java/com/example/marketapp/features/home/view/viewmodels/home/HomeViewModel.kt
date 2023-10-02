package com.example.marketapp.features.home.view.viewmodels.home

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.destinations.OrderScreenDestination
import com.example.marketapp.features.home.domain.usecases.HomeUseCase
import com.example.marketapp.features.order.domain.usecases.order.MakeOrderStep2UseCase
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    ) : ViewModel() {

    var state by mutableStateOf(HomeState())
    private var job: Job? = null

    private fun onContactClick(navigator: DestinationsNavigator,context: Context){

    }

    private fun onMakeOrderClick(navigator: DestinationsNavigator,context: Context){
        navigator.navigate(OrderScreenDestination)
    }

    private fun onHomePageInit(context: Context) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(
                error = null,
                isHomePageLoading = true
            )
            val response = homeUseCase(
                CoreViewModel.user!!.token,
                context
            )
                if (response.failure != null) {
                    CoreViewModel.showSnackbar(("Error:" + response.failure.message))

                    state = state.copy(
                        error = response.failure.message
                    )

                } else {
                    state = state.copy(
                        profileImage = response.data!!.data.order.user_image,
                        username = response.data.data.order.user_name,
                        wallet = response.data.data.order.user_wallet_balance.toDouble(),
                        assistantName = response.data.data.order.delivery_name,
                        assistantImage = response.data.data.order.delivery_image
                    )
                }


            state = state.copy(isHomePageLoading = false)


        }
    }


    fun onEvent(event : HomeEvent) {
        when(event){
            is HomeEvent.OnContactClick -> {
                onContactClick(event.navigator,event.context)
            }
            is HomeEvent.OnMakeOrderClick -> {
                onMakeOrderClick(event.navigator,event.context)
            }
            is HomeEvent.OnHomePageInit -> {
                onHomePageInit(event.context)
            }
        }
    }

    }
