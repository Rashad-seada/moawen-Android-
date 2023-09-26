package com.example.marketapp.features.auth.view.screens.register

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.marketapp.core.ui.theme.Cairo
import com.example.marketapp.core.ui.theme.MarketAppTheme
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral400
import com.example.marketapp.core.ui.theme.Neutral500
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.core.ui.theme.Primary900
import com.example.marketapp.core.views.components.CustomTextField
import com.example.marketapp.core.views.components.MainButton
import com.example.marketapp.core.views.components.PhoneTextField
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
    onChangeUsername: (String) -> Unit = {},
    onChangePhone: (String) -> Unit = {},
    onChangeEmail: (String) -> Unit = {},
    onChangePassword: (String) -> Unit = {},
    onChangePasswordRenter: (String) -> Unit = {},
    onSecurePasswordClick: () -> Unit = {},
    onSecurePasswordRenterClick: () -> Unit = {},
    onLoginClick: (DestinationsNavigator) -> Unit = {},
    onRegisterClick: (DestinationsNavigator) -> Unit = {},
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
) {

    val context: Context = LocalContext.current
    val scope = rememberCoroutineScope()


    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        it

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



            Spacer(modifier = Modifier.height(60.dp))


            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = context.getString(R.string.register),
                style = TextStyle(
                    fontFamily = Cairo,
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                    fontSize = 32.sp
                )
            )
            //Spacer(modifier = Modifier.height(5.dp))

            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = context.getString(R.string.register_sub_text),
                style = TextStyle(
                    fontFamily = Cairo,
                    color = Neutral500,
                    fontSize = 16.sp,

                    )
            )

            Spacer(modifier = Modifier.height(20.dp))


            CustomTextField(
                value = state.username,
                onValueChange = {
                    onChangeUsername(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = context.getString(R.string.username),
                placeHolder = context.getString(R.string.username_hint),
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.profile_inactive),
                        contentDescription = ""
                    )
                },

                )
            Spacer(modifier = Modifier.height(5.dp))

            CustomTextField(
                value = state.email,
                onValueChange = {
                    onChangeEmail(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = context.getString(R.string.email),
                placeHolder = context.getString(R.string.email_hint),
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.email),
                        contentDescription = ""
                    )
                },

                )
            Spacer(modifier = Modifier.height(5.dp))

            PhoneTextField(
                value = state.phone,
                onValueChange = {
                    onChangePhone(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = context.getString(R.string.phone),
                placeHolder = context.getString(R.string.phone_hint),

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
                }

            )

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
                }

            )




            Spacer(modifier = Modifier.height(80.dp))


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
                        fontFamily = Cairo,
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
                        fontFamily = Cairo,
                        color = Primary900,
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
                        scope.launch {
                            navigator?.let {
                                onRegisterClick(navigator)
                            }
                        }
                    },
                cardColor = Primary900,
                borderColor = Color.Transparent
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = context.getString(R.string.register),
                    style = TextStyle(
                        fontFamily = Cairo,
                        color = Neutral100,
                        fontSize = 16.sp,
                    )
                )
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


