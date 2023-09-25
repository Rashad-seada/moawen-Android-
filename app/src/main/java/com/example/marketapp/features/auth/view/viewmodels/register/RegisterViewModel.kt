package com.example.marketapp.features.auth.view.viewmodels.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.marketapp.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {

    var state = mutableStateOf(RegisterState())

    fun updateUsername(username: String){
        state.value = state.value.copy(
            username = username
        )
    }

    fun updateEmail(email: String){
        state.value = state.value.copy(
            email = email
        )
    }

    fun updatePhone(phone: String){
        state.value = state.value.copy(
            phone = phone
        )
    }

    fun updatePassword(password: String){
        state.value = state.value.copy(
            password = password
        )
    }

    fun updatePasswordRenter(passwordRenter: String) {
        state.value = state.value.copy(
            passwordRenter = passwordRenter
        )
    }

    fun updateIsPasswordSecure(){
        state.value = state.value.copy(
            isPasswordSecure = !state.value.isPasswordSecure
        )
    }

    fun updateIsPasswordRenterSecure(){
        state.value = state.value.copy(
            isPasswordRenterSecure = !state.value.isPasswordRenterSecure
        )
    }

    private fun register(navigator: DestinationsNavigator){

    }

    private fun onBackClick(navigator: DestinationsNavigator){
        navigator.popBackStack()
    }

    private fun onLoginClick(navigator: DestinationsNavigator){
        navigator.navigate(LoginScreenDestination())
    }



    fun onEvent(event : RegisterEvent){

        when(event) {
            is RegisterEvent.Register -> {
                register(event.navigator)
            }
            is RegisterEvent.OnBackClick -> {
                onBackClick(event.navigator)
            }
            is RegisterEvent.OnLoginClick -> {
                onLoginClick(event.navigator)
            }
        }

    }



}