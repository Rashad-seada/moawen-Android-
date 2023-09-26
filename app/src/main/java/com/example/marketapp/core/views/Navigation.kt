package com.example.marketapp.core.views

//import com.example.marketapp.destinations.MethodsScreenDestination
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marketapp.NavGraphs
import com.example.marketapp.core.ui.theme.Cairo
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral200
import com.example.marketapp.core.ui.theme.Neutral400
import com.example.marketapp.core.ui.theme.Neutral600
import com.example.marketapp.core.ui.theme.Neutral800
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.core.views.screens.OnBoardingScreen
import com.example.marketapp.core.views.screens.SplashScreen
import com.example.marketapp.destinations.LoginMethodsScreenDestination
import com.example.marketapp.destinations.LoginScreenDestination
import com.example.marketapp.destinations.OnBoardingScreenDestination
import com.example.marketapp.destinations.RegisterScreenDestination
import com.example.marketapp.destinations.ResetPasswordByEmailScreenDestination
import com.example.marketapp.destinations.ResetPasswordByPhoneScreenDestination
import com.example.marketapp.destinations.ResetPasswordMethodsScreenDestination
import com.example.marketapp.destinations.ResetPasswordPinScreenDestination
import com.example.marketapp.destinations.SplashScreenDestination
import com.example.marketapp.features.auth.view.screens.login.LoginScreen
import com.example.marketapp.features.auth.view.screens.methods.LoginMethodsScreen
import com.example.marketapp.features.auth.view.screens.register.RegisterScreen
import com.example.marketapp.features.auth.view.screens.reset_password.ResetPasswordByEmailScreen
import com.example.marketapp.features.auth.view.screens.reset_password.ResetPasswordByPhoneScreen
import com.example.marketapp.features.auth.view.screens.reset_password.ResetPasswordMethodsScreen
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
            composable(OnBoardingScreenDestination) {
                OnBoardingScreen(
                    onSkipClick = { coreViewModel.onOnBoardingScreenSkipClick(it) },
                    onNextClick = { coreViewModel.onOnBoardingScreenNextClick(it) },
                    navigator = destinationsNavigator
                )
            }
            composable(SplashScreenDestination) {
                SplashScreen(navigator = destinationsNavigator,
                    onScreenLaunch = { scope.launch { coreViewModel.onSplashScreenLaunch(it) } })
            }
            composable(LoginMethodsScreenDestination) {
                LoginMethodsScreen(
                    navigator = destinationsNavigator,
                    onLoginClick = { coreViewModel.onMethodsScreenLoginClick(it) },
                    onRegisterClick = { coreViewModel.onMethodsScreenRegisterClick(it) },
                    onLoginWithGoogleClick = { coreViewModel.onMethodsScreenLoginWithGoogleClick(it) },
                )
            }
            composable(LoginScreenDestination) {
                LoginScreen(
                    navigator = destinationsNavigator,
                    state = loginViewModel.state.value,
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
                    onLoginWithGoogleClick = { loginViewModel.onEvent(LoginEvent.LoginWithGoogle) },
                    onForgotPasswordClick = { loginViewModel.onEvent(LoginEvent.ForgotPassword(it)) },
                    onSecurePasswordClick = { loginViewModel.updatePasswordSecureState() },
                    onBackArrowClick = { loginViewModel.onEvent(LoginEvent.OnBackClick(it)) }
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
                    onResetByPhoneClick = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnResetWithPhoneClick(
                                it
                            )
                        )
                    },
                )
            }

            composable(ResetPasswordByEmailScreenDestination) {
                ResetPasswordByEmailScreen(
                    navigator = destinationsNavigator,
                    state = resetPasswordViewModel.state.value,
                    onBackArrowClick = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnBackButtonClick(
                                it
                            )
                        )
                    },
                    onEmailChangeClick = { resetPasswordViewModel.updateEmail(it) },
                    onNextClick = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnSendCodeToEmailClick(
                                it
                            )
                        )
                    },
                )
            }

            composable(ResetPasswordByPhoneScreenDestination) {
                ResetPasswordByPhoneScreen(
                    navigator = destinationsNavigator,
                    state = resetPasswordViewModel.state.value,
                    onBackArrowClick = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnBackButtonClick(
                                it
                            )
                        )
                    },
                    onPhoneChangeClick = { resetPasswordViewModel.updatePhoneNumber(it) },
                    onNextClick = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnSendCodeToPhoneClick(
                                it
                            )
                        )
                    },
                )
            }


            composable(ResetPasswordPinScreenDestination) {
                ResetPasswordPinScreen(navigator = destinationsNavigator,
                    state = resetPasswordViewModel.state.value,
                    onBackArrowClick = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnBackButtonClick(
                                it
                            )
                        )
                    },
                    onPinChangeClick = { resetPasswordViewModel.updatePin(it) },
                    onValidateClick = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnValidateClick(
                                it
                            )
                        )
                    },
                    onComplete = {
                        resetPasswordViewModel.onEvent(
                            ResetPasswordMethodsEvent.OnValidateClick(
                                it
                            )
                        )
                    },
                    onResendClick = { resetPasswordViewModel.onEvent(ResetPasswordMethodsEvent.OnResendClickClick) })
            }

            composable(RegisterScreenDestination) {
                RegisterScreen(
                    navigator = destinationsNavigator,
                    state = registerViewModel.state.value,
                    onBackArrowClick = { registerViewModel.onEvent(RegisterEvent.OnBackClick(it)) },
                    onChangeUsername = { registerViewModel.updateUsername(it) },
                    onChangePhone = { registerViewModel.updatePhone(it) },
                    onChangeEmail = { registerViewModel.updateEmail(it) },
                    onChangePassword = { registerViewModel.updatePassword(it) },
                    onChangePasswordRenter = { registerViewModel.updatePasswordRenter(it) },
                    onSecurePasswordClick = { registerViewModel.updateIsPasswordSecure() },
                    onSecurePasswordRenterClick = { registerViewModel.updateIsPasswordRenterSecure() },
                    onLoginClick = { registerViewModel.onEvent(RegisterEvent.OnLoginClick(it)) },
                    onRegisterClick = { registerViewModel.onEvent(RegisterEvent.Register(it)) },
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
                ){

                    val messages = it.visuals.message.split(":")

                    Column {
                        Text(
                            text = messages[0],
                            style = TextStyle(
                                fontFamily = Cairo,
                                color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                                fontSize = 20.sp
                            )
                        )

                        messages.forEachIndexed { index, s -> if(index != 0) Text(
                            text = s,
                            style = TextStyle(
                                fontFamily = Cairo,
                                color = if (isSystemInDarkTheme()) Neutral400 else Neutral600,
                                fontSize = 16.sp
                            )
                        )

                        }



                    }

                }
            }
        }
    }

}
