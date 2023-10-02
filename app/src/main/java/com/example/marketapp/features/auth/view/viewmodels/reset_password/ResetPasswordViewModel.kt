package com.example.marketapp.features.auth.view.viewmodels.reset_password

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
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.core.views.components.PhoneNumber
import com.example.marketapp.destinations.DoneMessageScreenDestination
import com.example.marketapp.destinations.LoginScreenDestination
import com.example.marketapp.destinations.ResetPasswordByPhoneScreenDestination
import com.example.marketapp.destinations.ResetPasswordNewPasswordScreenDestination
import com.example.marketapp.destinations.ResetPasswordPinScreenDestination
import com.example.marketapp.features.auth.domain.usecases.ResetPasswordUseCase
import com.example.marketapp.features.auth.domain.usecases.SendCodeToPhoneUseCase
import com.example.marketapp.features.auth.domain.usecases.CheckCodeSentUseCase
import com.example.marketapp.features.auth.infrastructure.api.request.CheckCodeSentRequest
import com.example.marketapp.features.auth.infrastructure.api.request.ResetPasswordRequest
import com.example.marketapp.features.auth.infrastructure.api.request.SendCodeToPhoneRequest
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val sendCodeToPhoneUseCase: SendCodeToPhoneUseCase,
    private val checkCodeSentUseCase: CheckCodeSentUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,

    private val validateEmailLocalUseCase: ValidateEmailLocalUseCase,
    private val validatePhoneLocalUseCase: ValidatePhoneLocalUseCase,
    private val validatePasswordLocalUseCase: ValidatePasswordLocalUseCase,
    private val validatePasswordRepeatedLocalUseCase: ValidatePasswordRepeatedLocalUseCase,



    ): ViewModel() {

    var state by mutableStateOf(ResetPasswordState())

    private fun onDoneMessageScreenClick(navigator: DestinationsNavigator) {
        navigator.navigate(LoginScreenDestination())
    }

    private fun onResetPasswordMethodsScreenBackClick(navigator: DestinationsNavigator){
        navigator.popBackStack()
    }


    private fun onResetPasswordByPhoneClick(navigator: DestinationsNavigator){
        navigator.navigate(ResetPasswordByPhoneScreenDestination)
    }

    private fun onSendCodeToPhoneClick(navigator: DestinationsNavigator, context: Context){
        viewModelScope.launch (Dispatchers.IO){
            state = state.copy(isSendingPinCode = true)
            var response = sendCodeToPhoneUseCase(
                sendCodeToPhoneRequest = SendCodeToPhoneRequest(
                    countryCode = state.countryCode,
                    phone = state.phone
                ),
                context = context,
            )
            state = state.copy(isSendingPinCode = false)

            if(response.failure != null) {
                CoreViewModel.showSnackbar(("Error:" + response.failure!!.message))
            } else {
                viewModelScope.launch (Dispatchers.Main){
                    navigator.navigate(ResetPasswordPinScreenDestination())
                }
            }
        }
    }

    private fun onValidateClick(navigator: DestinationsNavigator, context: Context){
        viewModelScope.launch (Dispatchers.IO){
            state = state.copy(isValidatingPinCode = true)
            val response = checkCodeSentUseCase(
                CheckCodeSentRequest(
                    countryCode = state.countryCode,
                    phone = state.phone,
                    otp = state.pinCode
                ),
                context = context,
            )
            state = state.copy(isValidatingPinCode = false)

            if(response.failure != null) {
                CoreViewModel.showSnackbar(("Error:" + response.failure.message))
            } else {
                viewModelScope.launch (Dispatchers.Main){
                    navigator.navigate(ResetPasswordNewPasswordScreenDestination())
                }
            }
        }
    }

    private fun onSettingNewPassword(navigator: DestinationsNavigator, context: Context){
        viewModelScope.launch (Dispatchers.IO){
            state = state.copy(isResettingPassword = true)
            val response = resetPasswordUseCase(
                resetPasswordRequest = ResetPasswordRequest(
                    phone = state.phone,
                    countryCode = state.countryCode,
                    otp = state.pinCode,
                    password = state.newPassword,
                    confirmPassword = state.newPasswordRepeated,
                ),
                context = context,
            )
            state = state.copy(isResettingPassword = false)

            if(response.failure != null) {
                CoreViewModel.showSnackbar(("Error:" + response.failure.message))
            } else {
                viewModelScope.launch (Dispatchers.Main){
                    navigator.navigate(DoneMessageScreenDestination())
                }
            }
        }
    }

    private fun onResendClick(navigator: DestinationsNavigator, context: Context){
        viewModelScope.launch (Dispatchers.IO){
            state = state.copy(isSendingPinCode = true)
            val response = sendCodeToPhoneUseCase(
                sendCodeToPhoneRequest = SendCodeToPhoneRequest(
                    countryCode = state.countryCode,
                    phone = state.phone
                ),
                context = context,
            )
            state = state.copy(isSendingPinCode = false)

            if(response.failure != null) {
                CoreViewModel.showSnackbar(("Error:" + response.failure.message))
            } else {
                CoreViewModel.showSnackbar(("Message:" + (response.data?.message ?: "")))
            }
        }
    }

    fun updateEmail(email : String ){
        state = state.copy(
            email = email
        )
    }

    fun updatePhoneNumber(phone : String ){
        state = state.copy(
            phone = phone
        )
    }

    fun updatePin(pinCode: String) {
        state = state.copy(
            pinCode = pinCode
        )
    }

    fun updatePhoneNumberWithCountryCode(phoneNumber: PhoneNumber) {
        state = state.copy(
            countryCode = phoneNumber.countryCode
        )
    }

    fun updateNewPassword(newPassword: String) {
        state = state.copy(
            newPassword = newPassword
        )
    }

    fun updateNewPasswordRepeated(newPasswordRepeated: String) {
        state = state.copy(
            newPasswordRepeated = newPasswordRepeated
        )
    }


    fun onEvent(event : ResetPasswordMethodsEvent){
        when(event) {
            is ResetPasswordMethodsEvent.OnBackButtonClick -> {
                event.navigator?.let { onResetPasswordMethodsScreenBackClick(it) }
            }
            is ResetPasswordMethodsEvent.OnResetWithPhoneClick -> {
                event.navigator?.let { onResetPasswordByPhoneClick(it) }
            }

            is ResetPasswordMethodsEvent.OnSendCodeToPhoneClick -> {
                validatePhone(event.context , callBackFunction = { event.navigator?.let { onSendCodeToPhoneClick(it,event.context) } })
            }
            is ResetPasswordMethodsEvent.OnValidateClick -> {
                event.navigator?.let { onValidateClick(it,event.context) }
            }
            is ResetPasswordMethodsEvent.OnResendClickClick -> {
                event.navigator?.let { onResendClick(it,event.context) }
            }
            is ResetPasswordMethodsEvent.OnDoneMessageScreenClick -> {
                onDoneMessageScreenClick(event.navigator)
            }
            is ResetPasswordMethodsEvent.OnSettingNewPasswordClick -> {
                validateNewPassword(event.context , callBackFunction = { event.navigator?.let { onSettingNewPassword(it,event.context) }})
            }
        }

    }

    private fun validateNewPassword(context: Context, callBackFunction : ()-> Unit){
        val passwordResult = validatePasswordLocalUseCase(state.newPassword, context)
        val passwordRpeatedResult = validatePasswordRepeatedLocalUseCase(state.newPassword, state.newPasswordRepeated,context)

        val hasError = listOf(
            passwordResult,
            passwordRpeatedResult,
        ).any {
            it.failure != null
        }

        state = state.copy(
            newPasswordError = passwordResult.failure?.message,
            newPasswordRepeatedError = passwordRpeatedResult.failure?.message,
        )

        if (hasError) {
            return
        }

        callBackFunction()
    }

    private fun validateEmail(context: Context, callBackFunction : ()-> Unit){
        val emailResult = validateEmailLocalUseCase(state.email, context)

        val hasError = emailResult.failure != null

        state = state.copy(
            emailError = emailResult.failure?.message,
        )

        if (hasError) {
            return
        }

        callBackFunction()
    }

    private fun validatePhone(context: Context, callBackFunction : ()-> Unit){
        val phoneResult = validatePhoneLocalUseCase(state.phone, context)

        val hasError = phoneResult.failure != null

        state = state.copy(
            phoneError = phoneResult.failure?.message,
        )

        if (hasError) {
            return
        }

        callBackFunction()
    }




}