package com.example.marketapp.features.auth.view.viewmodels.reset_password

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.marketapp.destinations.ResetPasswordByEmailScreenDestination
import com.example.marketapp.destinations.ResetPasswordByPhoneScreenDestination
import com.example.marketapp.destinations.ResetPasswordPinScreenDestination
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
        navigator.navigate(ResetPasswordByPhoneScreenDestination)
    }

    fun onSendCodeToEmailClick(navigator: DestinationsNavigator){
        navigator.navigate(ResetPasswordPinScreenDestination)
    }

    fun onSendCodeToPhoneClick(navigator: DestinationsNavigator){

        navigator.navigate(ResetPasswordPinScreenDestination)
    }

    fun onValidateClick(navigator: DestinationsNavigator){

    }

    fun onResendClickClick(){
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

    fun updatePin(pinCode: String) {
        state.value = state.value.copy(
            pinCode = pinCode
        )
    }

    fun onEvent(event : ResetPasswordMethodsEvent){
        when(event) {
            is ResetPasswordMethodsEvent.OnBackButtonClick -> {
                event.navigator?.let { onResetPasswordMethodsScreenBackClick(it) }
            }
            is ResetPasswordMethodsEvent.OnResetWithEmailClick -> {
                event.navigator?.let { onResetPasswordByEmailClick(it) }
            }
            is ResetPasswordMethodsEvent.OnResetWithPhoneClick -> {
                event.navigator?.let { onResetPasswordByPhoneClick(it) }
            }
            is ResetPasswordMethodsEvent.OnSendCodeToEmailClick -> {
                event.navigator?.let { onSendCodeToEmailClick(it) }
            }
            is ResetPasswordMethodsEvent.OnSendCodeToPhoneClick -> {
                event.navigator?.let { onSendCodeToPhoneClick(it) }
            }
            is ResetPasswordMethodsEvent.OnValidateClick -> {
                event.navigator?.let { onValidateClick(it) }
            }
            is ResetPasswordMethodsEvent.OnResendClickClick -> {
                onResendClickClick()
            }
        }

    }



}