package com.example.marketapp.features.auth.view.viewmodels.register

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketapp.core.util.usecase.ValidateEmailLocalUseCase
import com.example.marketapp.core.util.usecase.ValidatePasswordLocalUseCase
import com.example.marketapp.core.util.usecase.ValidatePasswordRepeatedLocalUseCase
import com.example.marketapp.core.util.usecase.ValidatePhoneLocalUseCase
import com.example.marketapp.core.util.usecase.ValidateUsernameLocalUseCase
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.destinations.LoginScreenDestination
import com.example.marketapp.features.auth.domain.usecases.RegisterUseCase
import com.example.marketapp.features.auth.domain.usecases.ValidateEmailUseCase
import com.example.marketapp.features.auth.domain.usecases.ValidatePhoneUseCase
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePhoneUseCase: ValidatePhoneUseCase,

    private val validateUsernameLocalUseCase: ValidateUsernameLocalUseCase,
    private val validateEmailLocalUseCase: ValidateEmailLocalUseCase,
    private val validatePhoneLocalUseCase: ValidatePhoneLocalUseCase,
    private val validatePasswordLocalUseCase: ValidatePasswordLocalUseCase,
    private val validatePasswordRepeatedLocalUseCase: ValidatePasswordRepeatedLocalUseCase
) : ViewModel() {

    var state by mutableStateOf(RegisterState())
    private var job : Job? = null
    private val registerScreenId = 1

    fun updateUsername(username: String) {
        state = state.copy(
            username = username
        )
    }

    fun updateEmail(email: String,context: Context) {
        state = state.copy(
            email = email
        )

        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(isValidatingEmail = true)
            val response = validateEmailUseCase(email,context,registerScreenId)
            state = state.copy(isValidatingEmail = false)

            if(response.failure != null) {
                state = state.copy(isEmailValid = true)
            } else {
                CoreViewModel.showSnackbar(("Message:" + (response.data?.msg?.trim() ?: "")))
                state = state.copy(isEmailValid = false)
            }
        }


    }

    fun updatePhone(phone: String) {
        state = state.copy(
            phone = phone
        )
    }

    fun updatePassword(password: String) {
        state = state.copy(
            password = password
        )
    }

    fun updatePasswordRenter(passwordRenter: String) {
        state = state.copy(
            passwordRenter = passwordRenter
        )
    }

    fun updateIsPasswordSecure() {
        state = state.copy(
            isPasswordSecure = !state.isPasswordSecure
        )
    }

    fun updateIsPasswordRenterSecure() {
        state = state.copy(
            isPasswordRenterSecure = !state.isPasswordRenterSecure
        )
    }

    fun updatePhoneWithCountryCode(phoneWithCountryCode : String,context: Context) {
        state = state.copy(
            phoneWithCountryCode = phoneWithCountryCode
        )
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(isValidatingPhone = true)
            val response = validatePhoneUseCase(phoneWithCountryCode,context,registerScreenId)
            state = state.copy(isValidatingPhone = false)

            if(response.failure != null) {
                state = state.copy(isPhoneValid = true)
            } else {
                CoreViewModel.showSnackbar(("Message:" + (response.data?.msg?.trim() ?: "")))
                state = state.copy(isPhoneValid = false)
            }
        }
    }


    private fun register(navigator: DestinationsNavigator,context: Context) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(isRegisterLoading = true)
            val response = registerUseCase(
                username = state.username,
                email = state.email,
                phone = state.phoneWithCountryCode,
                password = state.password,
                context = context,
                screenId = registerScreenId,
            )
            state = state.copy(isRegisterLoading = false)

            if(response.failure != null) {
                CoreViewModel.showSnackbar(("Error:" + response.failure.message.trim()))
            } else {
                CoreViewModel.showSnackbar(("Success:" + (response.data?.msg?.trim() ?: "")))
            }

        }
    }

    private fun onBackClick(navigator: DestinationsNavigator) {
        navigator.popBackStack()
    }

    private fun onLoginClick(navigator: DestinationsNavigator) {
        navigator.navigate(LoginScreenDestination())
    }


    fun onEvent(event: RegisterEvent) {

        when (event) {
            is RegisterEvent.Register -> {
                validateForm(event.context, callBackFunction = { register(event.navigator,event.context) })
            }

            is RegisterEvent.OnBackClick -> {
                onBackClick(event.navigator)
            }

            is RegisterEvent.OnLoginClick -> {
                onLoginClick(event.navigator)
            }
        }

    }

    private fun validateForm(context: Context, callBackFunction: () -> Unit) {

        val usernameResult = validateUsernameLocalUseCase(state.username, context)
        val emailResult = validateEmailLocalUseCase(state.email, context)
        val phoneResult = validatePhoneLocalUseCase(state.phone, context)
        val passwordResult = validatePasswordLocalUseCase(state.password, context)
        val passwordRepeatedResult =
            validatePasswordRepeatedLocalUseCase(state.passwordRenter, state.password, context)


        val hasError = listOf(
            usernameResult,
            emailResult,
            phoneResult,
            passwordResult,
            passwordRepeatedResult
        ).any {
            it.failure != null
        }

        state = state.copy(
            usernameError = usernameResult.failure?.message,
            emailError = emailResult.failure?.message,
            phoneError = phoneResult.failure?.message,
            passwordError = passwordResult.failure?.message,
            passwordRenterError = passwordRepeatedResult.failure?.message
        )

        if (hasError) {
            return
        }

        callBackFunction()

    }



}