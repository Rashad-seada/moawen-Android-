package com.example.marketapp.features.auth.view.viewmodels.login

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.destinations.RegisterScreenDestination
import com.example.marketapp.destinations.ResetPasswordMethodsScreenDestination
import com.example.marketapp.features.auth.domain.usecases.LoginUseCase
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    val loginScreenId = 0
    var state = mutableStateOf(LoginState())
    private var job : Job? = null

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
    }

    private fun onRegisterClick(navigator: DestinationsNavigator){
        navigator.navigate(RegisterScreenDestination())
    }

    private fun onBackClick(navigator: DestinationsNavigator) {
        navigator.popBackStack()
    }

    private fun onLoginClick(context: Context){
        job?.cancel()
        job = viewModelScope.launch {

            state.value = state.value.copy(isLoginLoading = true)
            val response = loginUseCase(state.value.username,state.value.password,context,loginScreenId)
            state.value = state.value.copy(isLoginLoading = false)

            if(response.failure != null) {
                CoreViewModel.showSnackbar(("Error: " + response.failure.message))
            } else {
                CoreViewModel.showSnackbar(("Success: " + response.data?.msg))
            }

        }



    }

    fun onEvent(event : LoginEvent){

        when(event){
            is LoginEvent.Login -> {
                onLoginClick(event.context)
            }
            is LoginEvent.Register -> {
                onRegisterClick(event.navigator)
            }
            is LoginEvent.LoginWithGoogle -> {

            }
            is LoginEvent.RememberMe -> {
                updateRememberMeState()
            }
            is LoginEvent.ForgotPassword -> {
                onForgotPasswordClick(event.navigator)
            }

            is LoginEvent.OnBackClick -> {
                onBackClick(event.navigator)
            }
        }
    }



}