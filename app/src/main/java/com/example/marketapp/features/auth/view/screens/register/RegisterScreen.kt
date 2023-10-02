package com.example.marketapp.features.auth.view.screens.register

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.core.views.components.*
import com.example.marketapp.features.auth.view.viewmodels.register.RegisterState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@SuppressLint("ResourceType", "UnrememberedMutableState")
@Destination
@Composable
fun RegisterScreen(
    navigator: DestinationsNavigator?,
    state: RegisterState = RegisterState(),
    onChangeFullName: (String) -> Unit = { },
    onChangePhone: (String, Context) -> Unit = { _, _->},
    onChangePhoneWithCountryCode : (PhoneNumber) -> Unit = {},
    onChangePassword: (String) -> Unit = {},
    onChangePasswordRenter: (String) -> Unit = {},
    onSecurePasswordClick: () -> Unit = {},
    onSecurePasswordRenterClick: () -> Unit = {},
    onLoginClick: (DestinationsNavigator) -> Unit = {},
    onRegisterClick: (DestinationsNavigator,Context) -> Unit = {_,_-> },
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
    onTermsClick : () -> Unit = {},
) {

    val context: Context = LocalContext.current
    val scope = rememberCoroutineScope()


    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) { padding ->
        padding

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Icon(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .clickable {
                        navigator?.let {
                            onBackArrowClick(navigator)
                        }
                    },
                painter = painterResource(
                    id = R.drawable.arrow_left
                ),
                contentDescription = null,
                tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
            )



            Spacer(modifier = Modifier.height(15.dp))


            Row(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo), // Provide the resource ID
                    contentDescription = "",
                    modifier = Modifier
                        .width(65.83.dp) // Adjust the size as needed
                        .height(51.19.dp)
                )

                Text(
                    text = context.getString(R.string.oawen),
                    style = TextStyle(
                        fontFamily = Lato,
                        color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                        fontSize = 50.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))


            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = context.getString(R.string.register),
                style = TextStyle(
                    fontFamily = Lato,
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                    fontSize = 32.sp
                )
            )

            Spacer(modifier = Modifier.height(10.dp))


            CustomTextField(
                value = state.fullName,
                onValueChange = {
                    onChangeFullName(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = context.getString(R.string.fullName),
                placeHolder = context.getString(R.string.fullName_hint),
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.profile_inactive),
                        contentDescription = ""
                    )
                },
                isError = state.fullNameError != null,
                errorMessage = state.fullNameError?: "",

                )

            Spacer(modifier = Modifier.height(5.dp))

            PhoneTextField(
                value = state.phone,
                onValueChange = {
                    onChangePhone(it,context)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = context.getString(R.string.phone),
                placeHolder = context.getString(R.string.phone_hint),
                isError = state.phoneError != null,
                errorMessage = state.phoneError ?: "",
                onPhoneChange = {
                    onChangePhoneWithCountryCode(it)
                }
            )

            Spacer(modifier = Modifier.height(5.dp))

            CustomTextField(
                isSecure = state.isPasswordSecure,
                value = state.password,
                onValueChange = {

                    onChangePassword(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = context.getString(R.string.password),
                placeHolder = context.getString(R.string.password_hint),
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.lock_inactive),
                        contentDescription = ""
                    )
                },
                trailingIcon = {
                    Image(
                        modifier = Modifier
                            .padding(end = 0.dp)
                            .clickable {
                                onSecurePasswordClick()
                            },
                        painter = painterResource(id = R.drawable.eye_slash),
                        contentDescription = ""
                    )
                },
                isError = state.passwordError != null,
                errorMessage = state.passwordError?: "",

            )

            Spacer(modifier = Modifier.height(5.dp))


            CustomTextField(
                isSecure = state.isPasswordRenterSecure,
                value = state.passwordRenter,
                onValueChange = {

                    onChangePasswordRenter(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = context.getString(R.string.renter_password),
                placeHolder = context.getString(R.string.renter_password_hint),
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.lock_inactive),
                        contentDescription = ""
                    )
                },
                trailingIcon = {
                    Image(
                        modifier = Modifier
                            .padding(end = 0.dp)
                            .clickable {
                                onSecurePasswordRenterClick()
                            },
                        painter = painterResource(id = R.drawable.eye_slash),
                        contentDescription = ""
                    )
                },
                isError = state.passwordRenterError != null,
                errorMessage = state.passwordRenterError?: "",

            )


            Spacer(modifier = Modifier.height(10.dp))

            CustomCheckBox(
                checked = state.terms,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .clickable {
                            onTermsClick()
                    },
                title = context.getString(R.string.accept_terms)
            )

            Spacer(modifier = Modifier.height(20.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.Center,

                ) {
                Text(

                    modifier = Modifier.wrapContentSize(),
                    text = context.getString(R.string.already_have_account),
                    style = TextStyle(
                        fontFamily = Lato,
                        color = Neutral400,
                        fontSize = 16.sp,

                        ),
                    textAlign = TextAlign.End
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(

                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            scope.launch {
                                navigator?.let {
                                    onLoginClick(navigator)
                                }
                            }
                        },
                    text = context.getString(R.string.login),
                    style = TextStyle(
                        fontFamily = Lato,
                        color = Secondary,
                        fontSize = 16.sp,

                        ),
                    textAlign = TextAlign.End
                )
            }

            Spacer(modifier = Modifier.height(15.dp))


            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(55.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                            navigator?.let {
                                onRegisterClick(navigator,context)
                            }
                    },
                cardColor = Primary,
                borderColor = Color.Transparent
            ) {



                if (state.isRegisterLoading) {
                    CustomProgressIndicator(
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = context.getString(R.string.register),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = Neutral100,
                            fontSize = 16.sp,
                        )
                    )
                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                HorizontalDivider(
                    modifier = Modifier
                        .width(50.dp)
                        .height(1.7.dp),
                    color = Neutral300
                )

                Text(

                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 10.dp),
                    text = context.getString(R.string.or_login_with),
                    style = TextStyle(
                        fontFamily = Lato,
                        color = Neutral400,
                        fontSize = 16.sp,
                    ),
                    textAlign = TextAlign.End
                )
                HorizontalDivider(
                    modifier = Modifier
                        .width(50.dp)
                        .height(1.5.dp),
                    color = Neutral300
                )


            }

            Spacer(modifier = Modifier.height(20.dp))


            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(55.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        navigator?.let {

                        }
                    },
                cardColor = Color.Transparent,
                borderColor = Neutral500
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = ""
                    )

                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = context.getString(R.string.google),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                            fontSize = 16.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(55.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        navigator?.let {

                        }
                    },
                cardColor = Color.Transparent,
                borderColor = Neutral500
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = ""
                    )

                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = context.getString(R.string.facebook),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                            fontSize = 16.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

        }


    }

}

@Preview
@Composable
fun DefaultPreview() {
    MarketAppTheme {
        RegisterScreen(
            navigator = null
        )
    }
}


