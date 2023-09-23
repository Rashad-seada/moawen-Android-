package com.example.marketapp.features.auth.view.viewmodels.reset_password

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.marketapp.destinations.ResetPasswordByEmailScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(): ViewModel() {

    var state = mutableStateOf(ResetPasswordState())

    fun onResetPasswordMethodsScreenBackClick(navigator: DestinationsNavigator){
        navigator.popBackStack()
    }

    fun onResetPasswordByEmailClick(navigator: DestinationsNavigator){
        navigator.navigate(ResetPasswordByEmailScreenDestination)
    }

    fun onResetPasswordByPhoneClick(navigator: DestinationsNavigator){

    }

    fun onSendCodeToEmailClick(navigator: DestinationsNavigator){

    }

    fun onSendCodeToPhoneClick(navigator: DestinationsNavigator){

    }

    fun updateEmail(email : String ){
        state.value = state.value.copy(
            email = email
        )
    }

    fun updatePhoneNumber(phone : String ){
        state.value = state.value.copy(
            phone = phone
        )
    }

    fun onEvent(event : ResetPasswordMethodsEvent){
        when(event) {
            is ResetPasswordMethodsEvent.OnBackButtonClick -> {
                onResetPasswordMethodsScreenBackClick(event.navigator)
            }
            is ResetPasswordMethodsEvent.OnResetWithEmailClick -> {
                onResetPasswordByEmailClick(event.navigator)
            }
            is ResetPasswordMethodsEvent.OnResetWithPhoneClick -> {
                onResetPasswordByPhoneClick(event.navigator)
            }
            is ResetPasswordMethodsEvent.OnSendCodeToEmailClick -> {
                onSendCodeToEmailClick(event.navigator)
            }
            is ResetPasswordMethodsEvent.OnSendCodeToPhoneClick -> {
                onSendCodeToPhoneClick(event.navigator)
            }
        }

    }

}