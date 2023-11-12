package com.example.marketapp.core.views

//import com.example.marketapp.destinations.MethodsScreenDestination
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
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
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.core.views.screens.DoneMessageScreen
import com.example.marketapp.core.views.screens.OnBoardingScreen
import com.example.marketapp.core.views.screens.SplashScreen
import com.example.marketapp.destinations.*
import com.example.marketapp.features.auth.view.screens.login.LoginScreen
import com.example.marketapp.features.auth.view.screens.methods.LoginMethodsScreen
import com.example.marketapp.features.auth.view.screens.register.ActivationPinScreen
import com.example.marketapp.features.auth.view.screens.register.RegisterScreen
import com.example.marketapp.features.auth.view.screens.reset_password.*
import com.example.marketapp.features.auth.view.viewmodels.login.LoginEvent
import com.example.marketapp.features.auth.view.viewmodels.login.LoginViewModel
import com.example.marketapp.features.auth.view.viewmodels.register.RegisterEvent
import com.example.marketapp.features.auth.view.viewmodels.register.RegisterViewModel
import com.example.marketapp.features.auth.view.viewmodels.reset_password.ResetPasswordMethodsEvent
import com.example.marketapp.features.auth.view.viewmodels.reset_password.ResetPasswordViewModel
import com.example.marketapp.features.home.view.screens.main.MainScreen
import com.example.marketapp.features.home.view.viewmodels.main.MainViewModel
import com.example.marketapp.features.order.view.screens.*
import com.example.marketapp.features.order.view.viewmodel.order.OrderEvent
import com.example.marketapp.features.order.view.viewmodel.order.OrderViewModel
import com.example.marketapp.features.order.view.viewmodel.select_location.SelectLocationEvent
import com.example.marketapp.features.order.view.viewmodel.select_location.SelectLocationViewModel
import com.example.marketapp.features.profile.view.screens.EditProfileScreen
import com.example.marketapp.features.profile.view.viewmodels.edit_profile.EditProfileEvent
import com.example.marketapp.features.profile.view.viewmodels.edit_profile.EditProfileViewModel
import com.example.marketapp.features.wallet.view.pages.ChargeBalanceScreen
import com.example.marketapp.features.wallet.view.screens.WalletMessageScreen
import com.example.marketapp.features.wallet.view.viewmodel.wallet.WalletEvent
import com.example.marketapp.features.wallet.view.viewmodel.wallet.WalletViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import kotlinx.coroutines.launch

@Composable
fun Navigation(
    coreViewModel: CoreViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
    resetPasswordViewModel: ResetPasswordViewModel = hiltViewModel(),
    registerViewModel: RegisterViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel(),
    orderViewModel : OrderViewModel = hiltViewModel(),
    locationViewModel : SelectLocationViewModel = hiltViewModel(),
    walletViewModel : WalletViewModel = hiltViewModel(),
    profileViewModel :EditProfileViewModel = hiltViewModel(),
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
                    onLoginWithGoogleClick = { navigator, task ->
                        coreViewModel.onMethodsScreenLoginWithGoogleClick(
                            navigator,
                            task
                        )
                    },
                )
            }

            composable(LoginScreenDestination) {
                LoginScreen(
                    navigator = destinationsNavigator,
                    state = loginViewModel.state,
                    onChangePhone = { loginViewModel.updatePhone(it) },
                    onChangePhoneWithCountryCode = { loginViewModel.updatePhoneWithCountryCode(it) },
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
                    onChangePhone = { navigator, context ->
                        registerViewModel.updatePhone(
                            navigator,
                            context
                        )
                    },
                    onChangePhoneWithCountryCode = { registerViewModel.updatePhoneWithCountryCode(it) },
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
                    onChangeFullName = {
                        registerViewModel.updateFullName(it)
                    },
                    onTermsClick = {
                        registerViewModel.updateTerms()
                    },


                )
            }

            composable(ActivationPinScreenDestination) {
                ActivationPinScreen(
                    navigator = destinationsNavigator,
                    state = registerViewModel.state,
                    onBackArrowClick = {
                        registerViewModel.onEvent(
                            RegisterEvent.OnBackClick(
                                it
                            )
                        )
                    },
                    onPinChangeClick = { registerViewModel.updatePin(it) },
                    onValidateClick = { navaigator, context ->
                        registerViewModel.onEvent(
                            RegisterEvent.OnValidateClick(
                                navaigator, context
                            )
                        )
                    },
                    onComplete = { navaigator, context ->
                        registerViewModel.onEvent(
                            RegisterEvent.OnValidateClick(
                                navaigator, context
                            )
                        )
                    },
                    onResendClick = { navigator, context ->
                        registerViewModel.onEvent(
                            RegisterEvent.OnResendClick(navigator, context)
                        )
                    })
            }

            composable(MainScreenDestination) {
                MainScreen(
                    state = mainViewModel.state,
                    navigator = destinationsNavigator,
                    onIndexChange = {
                        mainViewModel.updateCurrentIndex(it)
                    }
                )

            }

            composable(OrderScreenDestination) {
                OrderScreen(
                    state = orderViewModel.state,
                    navigator = destinationsNavigator,
                    onRecording = { orderViewModel.onRecording() },
                    onImageSelection = { orderViewModel.onImageSelection(it) },
                    onFileSelection = { orderViewModel.onFileSelection(it) },
                    onConfirmClick = {navigator,context->  orderViewModel.onEvent(OrderEvent.OnConfirmClick(navigator,context)) },
                    onDescriptionChange = { orderViewModel.onUpdateDescription(it) }
                )

            }

            composable(SelectLocationScreenDestination) {
                SelectLocationScreen(
                    state = locationViewModel.state,
                    navigator = destinationsNavigator,
                    onFromLocationClick = { locationViewModel.onEvent(SelectLocationEvent.OnFromLocationClick(it)) },
                    onToLocationClick = { locationViewModel.onEvent(SelectLocationEvent.OnToLocationClick(it)) },
                    onNextClick = {navigator,context ->  locationViewModel.onEvent(SelectLocationEvent.OnLocationSelected(navigator, context)) },
                    getDirections = { locationViewModel.onEvent(SelectLocationEvent.GetDirections) }
                )

            }


            composable(SearchLocationToScreenDestination) {
                SearchLocationToScreen(
                    state = locationViewModel.state,
                    navigator = destinationsNavigator,
                    onQueryChange = { locationViewModel.onSearchUpdate(it) },
                    onLocationSelect = {navigator,placeInfo->  locationViewModel.onEvent(SelectLocationEvent.OnFromLocationSelect(navigator,placeInfo)) },
                    onSearch = {  }
                )

            }

            composable(SearchLocationFromScreenDestination) {
                SearchLocationFromScreen(
                    state = locationViewModel.state,
                    navigator = destinationsNavigator,
                    onQueryChange = { locationViewModel.onSearchUpdate(it) },
                    onLocationSelect = {navigator,placeInfo ->  locationViewModel.onEvent(SelectLocationEvent.OnToLocationSelect(navigator,placeInfo)) },
                    onSearch = {  }
                )

            }

            composable(OrderMessageScreenDestination) {
                OrderMessageScreen(
                    navigator = destinationsNavigator,
                    onButtonTap = {  it.navigate(MainScreenDestination) }

                )

            }

            composable(ChargeBalanceScreenDestination) {
                ChargeBalanceScreen(
                    navigator = destinationsNavigator,
                    walletState = walletViewModel.state,
                    onImageSelection = { walletViewModel.updateImageState(it) },
                    onUploadImage = { navigator,context->  walletViewModel.onEvent(WalletEvent.OnBalanceRecharge(navigator,context)) },
                )

            }

            composable(WalletMessageScreenDestination) {
                WalletMessageScreen(
                    navigator = destinationsNavigator,
                    onButtonTap = { it.navigate(MainScreenDestination) }
                )

            }

            composable(EditProfileScreenDestination) {
                EditProfileScreen(
                    navigator = destinationsNavigator,
                    profileState = profileViewModel.state,
                    updatePhone = { profileViewModel.updatePhone(it) },
                    updateUsername = { profileViewModel.updateUsername(it) },
                    updateProfileImage = { profileViewModel.updateProfileImage(it) },
                    onSave = {navigator,context -> profileViewModel.onEvent(EditProfileEvent.OnSave(navigator, context))}
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
                                    fontFamily = Lato,
                                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                                    fontSize = 20.sp
                                )
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            messages.forEachIndexed { index, text ->
                                if (index != 0) Text(
                                    text = text,
                                    style = TextStyle(
                                        fontFamily = Lato,
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