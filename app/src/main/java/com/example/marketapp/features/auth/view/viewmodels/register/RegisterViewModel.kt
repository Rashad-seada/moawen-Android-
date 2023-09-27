package com.example.marketapp.features.auth.view.viewmodels.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.marketapp.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {

    var state by mutableStateOf(RegisterState())

    fun updateUsername(username: String){
        state = state.copy(
            username = username
        )
    }

    fun updateEmail(email: String){
        state = state.copy(
            email = email
        )
    }

    fun updatePhone(phone: String){
        state= state.copy(
            phone = phone
        )
    }

    fun updatePassword(password: String){
        state= state.copy(
            password = password
        )
    }

    fun updatePasswordRenter(passwordRenter: String) {
        state= state.copy(
            passwordRenter = passwordRenter
        )
    }

    fun updateIsPasswordSecure(){
        state= state.copy(
            isPasswordSecure = !state.isPasswordSecure
        )
    }

    fun updateIsPasswordRenterSecure(){
        state= state.copy(
            isPasswordRenterSecure = !state.isPasswordRenterSecure
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