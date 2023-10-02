package com.example.marketapp.features.auth.view.screens.reset_password

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Lato
import com.example.marketapp.core.ui.theme.MarketAppTheme
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral500
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.core.ui.theme.Primary
import com.example.marketapp.core.views.components.CustomProgressIndicator
import com.example.marketapp.core.views.components.CustomTextField
import com.example.marketapp.core.views.components.MainButton
import com.example.marketapp.features.auth.view.viewmodels.reset_password.ResetPasswordState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch


@SuppressLint("UnrememberedMutableState")
@Destination
@Composable
fun ResetPasswordNewPasswordScreen(
    navigator: DestinationsNavigator?,
    state: ResetPasswordState = ResetPasswordState(),
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},

    onNewPasswordChange: (String) -> Unit = {},
    onNewPasswordRpeatedChange: (String) -> Unit = {},

    onDoneClick: (DestinationsNavigator, Context) -> Unit = { _, _ -> },

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


            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(start = 0.dp),
                text = context.getString(R.string.new_password),
                style = TextStyle(
                    fontFamily = Lato,
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                    fontSize = 28.sp
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(end = 50.dp, start = 0.dp),
                text = context.getString(R.string.new_password_sub_text),
                style = TextStyle(
                    fontFamily = Lato,
                    color = Neutral500,
                    fontSize = 16.sp,

                    )
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                value = state.newPassword,
                onValueChange = {
                    onNewPasswordChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = context.getString(R.string.new_password),
                placeHolder = context.getString(R.string.new_password_hint),
                leadingIcon = {
                    Icon(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.lock_inactive),
                        contentDescription = "",
                        tint = Neutral500
                    )
                },
                isError = state.newPasswordError != null,
                errorMessage = state.newPasswordError ?: ""

            )
            Spacer(modifier = Modifier.height(5.dp))

            CustomTextField(
                value = state.newPasswordRepeated,
                onValueChange = {
                    onNewPasswordRpeatedChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = context.getString(R.string.renter_password),
                placeHolder = context.getString(R.string.renter_password_hint),
                leadingIcon = {
                    Icon(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.lock_inactive),
                        contentDescription = "",
                        tint = Neutral500
                    )
                },
                isError = state.newPasswordRepeatedError != null,
                errorMessage = state.newPasswordRepeatedError ?: ""

            )

            Spacer(modifier = Modifier.height(350.dp))


            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(55.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        scope.launch {
                            navigator?.let {
                                onDoneClick(navigator, context)
                            }
                        }
                    },
                cardColor = Primary,
                borderColor = Color.Transparent
            ) {

                if (!state.isResettingPassword) {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = context.getString(R.string.next),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = Neutral100,
                            fontSize = 16.sp,
                        )
                    )
                } else {
                    CustomProgressIndicator(
                        modifier = Modifier.size(20.dp)
                    )
                }


            }

            Spacer(modifier = Modifier.height(30.dp))



        }


    }

}

@Preview
@Composable
fun ResetPasswordNewPasswordScreenPreview() {
    MarketAppTheme {
        ResetPasswordNewPasswordScreen(
            navigator = null
        )
    }
}
