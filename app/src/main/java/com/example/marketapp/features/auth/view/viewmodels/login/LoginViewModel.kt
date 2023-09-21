package com.example.marketapp.features.auth.view.viewmodels.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.marketapp.core.infrastructure.Api.ProductApi
import com.example.marketapp.destinations.ResetPasswordMethodsScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    var productApi: ProductApi,
) : ViewModel() {

    var state = mutableStateOf(LoginState())

    fun updatePassword(paasword : String){
        state.value = state.value.copy(
            password = paasword
        )
    }

    fun updateUsername(username : String){
        state.value = state.value.copy(
            username = username
        )
    }

    fun updatePasswordSecureState(){
        state.value = state.value.copy(
            isPasswordSecure = !state.value.isPasswordSecure
        )
    }

    private fun updateRememberMeState(){
        state.value = state.value.copy(
            rememberMe = !state.value.rememberMe
        )
    }

    private fun onForgotPasswordClick(navigator: DestinationsNavigator){

        navigator.navigate(ResetPasswordMethodsScreenDestination())

        Log.v("Nav","navigated to Reset password")

    }

    fun onEvent(event : LoginEvent){

        when(event){
            is LoginEvent.Login -> {
//                viewModelScope.launch{
//                    val r = productApi.getProducts()
//
//                    Log.v("Products Res",r.products[1].title)
//                }
            }
            is LoginEvent.Register -> {

            }
            is LoginEvent.LoginWithGoogle -> {

            }
            is LoginEvent.RememberMe -> {
                updateRememberMeState()
            }
            is LoginEvent.ForgotPassword -> {
                onForgotPasswordClick(event.navigator)
            }
        }
    }

}