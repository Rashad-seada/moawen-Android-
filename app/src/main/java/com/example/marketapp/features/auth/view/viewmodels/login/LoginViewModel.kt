package com.example.marketapp.features.auth.view.viewmodels.login

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketapp.core.util.usecase.ValidateEmailUseCase
import com.example.marketapp.core.util.usecase.ValidatePasswordUseCase
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
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,

) : ViewModel() {

    val loginScreenId = 0
    var state by mutableStateOf(LoginState())
    private var job : Job? = null


    fun updatePassword(paasword : String){
        state = state.copy(
            password = paasword
        )
    }

    fun updateUsername(username : String){
        state = state.copy(
            username = username
        )
    }

    fun updatePasswordSecureState(){
        state = state.copy(
            isPasswordSecure = !state.isPasswordSecure
        )
    }

    private fun updateRememberMeState(){
        state= state.copy(
            rememberMe = !state.rememberMe
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

            state = state.copy(isLoginLoading = true)
            val response = loginUseCase(state.username,state.password,context,loginScreenId)
            state = state.copy(isLoginLoading = false)

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
                validateForm(event.context) { onLoginClick(event.context) }
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

    private fun validateForm(context: Context, callBackFunction : ()-> Unit){
        val emailResult = validateEmailUseCase(state.username,context)
        val passwordResult = validatePasswordUseCase(state.password,context)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any {
            it.failure != null
        }

        state = state.copy(
            usernameError = emailResult.failure?.message,
            passwordError = passwordResult.failure?.message
        )

        if(hasError) {
            return
        }

        callBackFunction()

    }



}