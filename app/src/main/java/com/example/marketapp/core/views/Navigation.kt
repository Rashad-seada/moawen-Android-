package com.example.marketapp.core.views

//import com.example.marketapp.destinations.MethodsScreenDestination
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marketapp.NavGraphs
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Cairo
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral200
import com.example.marketapp.core.ui.theme.Neutral300
import com.example.marketapp.core.ui.theme.Neutral400
import com.example.marketapp.core.ui.theme.Neutral600
import com.example.marketapp.core.ui.theme.Neutral700
import com.example.marketapp.core.ui.theme.Neutral800
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.core.views.screens.DoneMessageScreen
import com.example.marketapp.core.views.screens.OnBoardingScreen
import com.example.marketapp.core.views.screens.SplashScreen
import com.example.marketapp.destinations.DoneMessageScreenDestination
import com.example.marketapp.destinations.LoginMethodsScreenDestination
import com.example.marketapp.destinations.LoginScreenDestination
import com.example.marketapp.destinations.OnBoardingScreenDestination
import com.example.marketapp.destinations.RegisterScreenDestination
import com.example.marketapp.destinations.ResetPasswordByEmailScreenDestination
import com.example.marketapp.destinations.ResetPasswordByPhoneScreenDestination
import com.example.marketapp.destinations.ResetPasswordMethodsScreenDestination
import com.example.marketapp.destinations.ResetPasswordNewPasswordScreenDestination
import com.example.marketapp.destinations.ResetPasswordPinScreenDestination
import com.example.marketapp.destinations.SplashScreenDestination
import com.example.marketapp.features.auth.view.screens.login.LoginScreen
import com.example.marketapp.features.auth.view.screens.methods.LoginMethodsScreen
import com.example.marketapp.features.auth.view.screens.register.RegisterScreen
import com.example.marketapp.features.auth.view.screens.reset_password.ResetPasswordByEmailScreen
import com.example.marketapp.features.auth.view.screens.reset_password.ResetPasswordByPhoneScreen
import com.example.marketapp.features.auth.view.screens.reset_password.ResetPasswordMethodsScreen
import com.example.marketapp.features.auth.view.screens.reset_password.ResetPasswordNewPasswordScreen
import com.example.marketapp.features.auth.view.screens.reset_password.ResetPasswordPinScreen
import com.example.marketapp.features.auth.view.viewmodels.login.LoginEvent
import com.example.marketapp.features.auth.view.viewmodels.login.LoginViewModel
import com.example.marketapp.features.auth.view.viewmodels.register.RegisterEvent
import com.example.marketapp.features.auth.view.viewmodels.register.RegisterViewModel
import com.example.marketapp.features.auth.view.viewmodels.reset_password.ResetPasswordMethodsEvent
import com.example.marketapp.features.auth.view.viewmodels.reset_password.ResetPasswordViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import kotlinx.coroutines.launch

@Composable
fun Navigation(
    coreViewModel: CoreViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
    resetPasswordViewModel: ResetPasswordViewModel = hiltViewModel(),
    registerViewModel: RegisterViewModel = hiltViewModel()

) {
    val scope = rememberCoroutineScope()

    Box {
        DestinationsNavHost(NavGraphs.root) {

            composable(SplashScreenDestination) {
                SplashScreen(navigator = destinationsNavigator,
                    onScreenLaunch = { scope.launch { coreViewModel.onSplashScreenLaunch(it) } })
            }

            composable(OnBoardingScreenDestination) {
                OnBoardingScreen(
                    onSkipClick = { coreViewModel.onOnBoardingScreenSkipClick(it) },
                    onNextClick = { coreViewModel.onOnBoardingScreenNextClick(it) },
                    navigator = destinationsNavigator
                )
            }

            composable(LoginMethodsScreenDestination) {
                LoginMethodsScreen(
                    navigator = destinationsNavigator,
                    onLoginClick = { coreViewModel.onMethodsScreenLoginClick(it) },
                    onRegisterClick = { coreViewModel.onMethodsScreenRegisterClick(it) },
                    onLoginWithGoogleClick = { navigator,task-> coreViewModel.onMethodsScreenLoginWithGoogleClick(navigator,task) },
                    signInIntent = coreViewModel.provideSignInIntent()
                )
            }

            composable(LoginScreenDestination) {
                LoginScreen(
                    navigator = destinationsNavigator,
                    state = loginViewModel.state,
                    onChangeUsername = { loginViewModel.updateUsername(it) },
                    onChangePassword = { loginViewModel.updatePassword(it) },
                    onRememberMeClick = { loginViewModel.onEvent(LoginEvent.RememberMe) },
                    onLoginClick = { navigator, context ->
                        loginViewModel.onEvent(
                            LoginEvent.Login(
                                navigator,
                                context
                            )
                        )
                    },
                    onRegisterClick = { loginViewModel.onEvent(LoginEvent.Register(it)) },
                    onForgotPasswordClick = { loginViewModel.onEvent(LoginEvent.ForgotPassword(it)) },
                    onSecurePasswordClick = { loginViewModel.updatePasswordSecureState() },
                    onBackArrowClick = { loginViewModel.onEvent(LoginEvent.OnBackClick(it)) },
                )
            }
            composable(ResetPasswordMethodsScreenDestination) {
                ResetPasswordMethodsScreen(
                    navigator = destinationsNavigator,
                    onBackArrowClick = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnBackButtonClick(
                                it
                            )
                        )
                    },
                    onResetByEmailClick = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnResetWithEmailClick(
                                it
                            )
                        )
                    },
                    onResetByPhoneClick = { navigator ->
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnResetWithPhoneClick(
                                navigator,
                            )
                        )
                    },
                )
            }

            composable(ResetPasswordByEmailScreenDestination) {
                ResetPasswordByEmailScreen(
                    navigator = destinationsNavigator,
                    state = resetPasswordViewModel.state,
                    onBackArrowClick = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnBackButtonClick(
                                it
                            )
                        )
                    },
                    onEmailChangeClick = { resetPasswordViewModel.updateEmail(it) },
                    onNextClick = { navigator, context ->
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnSendCodeToEmailClick(
                                navigator, context
                            )
                        )
                    },
                )
            }

            composable(ResetPasswordByPhoneScreenDestination) {
                ResetPasswordByPhoneScreen(
                    navigator = destinationsNavigator,
                    state = resetPasswordViewModel.state,
                    onBackArrowClick = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnBackButtonClick(
                                it
                            )
                        )
                    },
                    onPhoneChange = { resetPasswordViewModel.updatePhoneNumber(it) },
                    onPhoneWithCountryCode = {
                        resetPasswordViewModel.updatePhoneNumberWithCountryCode(
                            it
                        )
                    },
                    onNextClick = { navigator, context ->
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnSendCodeToPhoneClick(
                                navigator, context
                            )
                        )
                    },
                )
            }


            composable(ResetPasswordPinScreenDestination) {
                ResetPasswordPinScreen(
                    navigator = destinationsNavigator,
                    state = resetPasswordViewModel.state,
                    onBackArrowClick = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnBackButtonClick(
                                it
                            )
                        )
                    },
                    onPinChangeClick = { resetPasswordViewModel.updatePin(it) },
                    onValidateClick = { navaigator, context ->
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnValidateClick(
                                navaigator, context
                            )
                        )
                    },
                    onComplete = { navaigator, context ->
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnValidateClick(
                                navaigator, context
                            )
                        )
                    },
                    onResendClick = { navigator, context ->
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnResendClickClick(navigator, context)
                        )
                    })
            }

            composable(ResetPasswordNewPasswordScreenDestination) {
                ResetPasswordNewPasswordScreen(
                    navigator = destinationsNavigator,
                    state = resetPasswordViewModel.state,
                    onBackArrowClick = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnBackButtonClick(
                                it
                            )
                        )
                    },
                    onDoneClick = { navigator, context ->
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnSettingNewPasswordClick(navigator, context)
                        )
                    },
                    onNewPasswordChange = { resetPasswordViewModel.updateNewPassword(it) },
                    onNewPasswordRpeatedChange = {
                        resetPasswordViewModel.updateNewPasswordRepeated(
                            it
                        )
                    }
                )

            }

            composable(DoneMessageScreenDestination) {
                DoneMessageScreen(
                    navigator = destinationsNavigator,
                    onButtonTap = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnDoneMessageScreenClick(
                                it
                            )
                        )
                    }
                )

            }

            composable(RegisterScreenDestination) {
                RegisterScreen(
                    navigator = destinationsNavigator,
                    state = registerViewModel.state,
                    onBackArrowClick = { registerViewModel.onEvent(RegisterEvent.OnBackClick(it)) },
                    onChangeUsername = { registerViewModel.updateUsername(it) },
                    onChangePhone = { registerViewModel.updatePhone(it) },
                    onChangeEmail = { navigator, context ->
                        registerViewModel.updateEmail(
                            navigator,
                            context
                        )
                    },
                    onChangePassword = { registerViewModel.updatePassword(it) },
                    onChangePasswordRenter = { registerViewModel.updatePasswordRenter(it) },
                    onSecurePasswordClick = { registerViewModel.updateIsPasswordSecure() },
                    onSecurePasswordRenterClick = { registerViewModel.updateIsPasswordRenterSecure() },
                    onLoginClick = { registerViewModel.onEvent(RegisterEvent.OnLoginClick(it)) },
                    onRegisterClick = { navigator, context ->
                        registerViewModel.onEvent(
                            RegisterEvent.Register(navigator, context)
                        )
                    },
                    onChangePhoneWithCountryCode = { navigator, context ->
                        registerViewModel.updatePhoneWithCountryCode(
                            navigator,
                            context
                        )
                    }
                )
            }

        }


        SnackbarHost(
            modifier = Modifier.align(Alignment.TopCenter),
            hostState = CoreViewModel.snackbarHostState,
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                color = if (isSystemInDarkTheme()) Neutral800 else Neutral200
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {

                    val messages = it.visuals.message.split(":")

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = messages[0],
                                style = TextStyle(
                                    fontFamily = Cairo,
                                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                                    fontSize = 20.sp
                                )
                            )

                            messages.forEachIndexed { index, text ->
                                if (index != 0) Text(
                                    text = text,
                                    style = TextStyle(
                                        fontFamily = Cairo,
                                        color = if (isSystemInDarkTheme()) Neutral400 else Neutral600,
                                        fontSize = 16.sp
                                    )
                                )
                            }

                        }
                        Icon(
                            modifier = Modifier.clickable {
                                CoreViewModel.snackbarHostState.currentSnackbarData?.dismiss()
                            },
                            painter = painterResource(id = R.drawable.close_circle),
                            contentDescription = null,
                            tint = if (isDebugInspectorInfoEnabled) Neutral300 else Neutral700
                        )
                    }

                }
            }
        }
    }

}
