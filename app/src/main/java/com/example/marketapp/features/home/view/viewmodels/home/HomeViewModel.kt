package com.example.marketapp.features.home.view.viewmodels.home

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.destinations.OrderScreenDestination
import com.example.marketapp.features.auth.domain.usecases.SaveUserInfoUseCase
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
    private val saveUserInfoUseCase: SaveUserInfoUseCase,

    ) : ViewModel() {

    var state by mutableStateOf(HomeState())
    private var job: Job? = null


    private fun onMakeOrderClick(navigator: DestinationsNavigator,context: Context){
        navigator.navigate(OrderScreenDestination)
    }

    private fun onHomePageInit(context: Context) {
        if(job == null){
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

                    saveUserInfoUseCase(
                        CoreViewModel.user!!.copy(
                            image = response.data.data.order.user_image,
                            fullname = response.data.data.order.user_name,

                            ),
                        context,
                        0
                    )

                    CoreViewModel.user = CoreViewModel.user!!.copy(
                        image = response.data.data.order.user_image,
                        fullname = response.data.data.order.user_name,
                    )
                }


                state = state.copy(isHomePageLoading = false)


            }
            job = null
        }

    }


    fun onEvent(event : HomeEvent) {
        when(event){
            is HomeEvent.OnMakeOrderClick -> {
                onMakeOrderClick(event.navigator,event.context)
            }
            is HomeEvent.OnHomePageInit -> {
                onHomePageInit(event.context)
            }
        }
    }

    }
