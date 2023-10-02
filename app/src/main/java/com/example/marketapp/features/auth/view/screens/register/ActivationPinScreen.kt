package com.example.marketapp.features.auth.view.screens.register

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.marketapp.core.ui.theme.Lato
import com.example.marketapp.core.ui.theme.MarketAppTheme
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral500
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.core.ui.theme.Primary
import com.example.marketapp.core.views.components.CustomProgressIndicator
import com.example.marketapp.core.views.components.MainButton
import com.example.marketapp.features.auth.view.components.reset_password.PinField
import com.example.marketapp.features.auth.view.viewmodels.register.RegisterState
import com.example.marketapp.features.auth.view.viewmodels.reset_password.ResetPasswordState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch


@SuppressLint("UnrememberedMutableState")
@Destination
@Composable
fun ActivationPinScreen(
    navigator: DestinationsNavigator?,
    state: RegisterState = RegisterState(),
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
    onPinChangeClick: (String) -> Unit = {},
    onValidateClick: (DestinationsNavigator, Context) -> Unit = { _, _ -> },
    onResendClick: (DestinationsNavigator, Context) -> Unit = { _, _ -> },
    onComplete: (DestinationsNavigator, Context) -> Unit = { _, _ -> },
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


            Spacer(modifier = Modifier.height(30.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    text = context.getString(R.string.verify_pin_code),
                    style = TextStyle(
                        fontFamily = Lato,
                        color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center
                    )
                )

                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    text = context.getString(R.string.verify_pin_code_sub_text),
                    style = TextStyle(
                        fontFamily = Lato,
                        color = Neutral500,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center

                    )
                )
            }



            Spacer(modifier = Modifier.height(40.dp))

            PinField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                value = state.pinCode,
                onValueChange = {
                    onPinChangeClick(it)
                },
                onComplete = {
                    navigator?.let {
                        onComplete(navigator, context)
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Text(
                    modifier = Modifier
                        .padding(end = 5.dp),
                    text = context.getString(R.string.didnt_recive_code),
                    style = TextStyle(
                        fontFamily = Lato,
                        color = Neutral500,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center

                    )
                )

                if (!state.isResendingPinCode) {
                    Text(
                        modifier = Modifier.clickable {
                            navigator?.let {
                                onResendClick(navigator,context)
                            }
                        },
                        text = context.getString(R.string.resend_new_code),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = Primary,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center

                        )
                    )
                } else {
                    CustomProgressIndicator( modifier = Modifier.size(10.dp))
                }
            }




            Spacer(modifier = Modifier.height(400.dp))



            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(55.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        scope.launch {
                            navigator?.let {
                                onValidateClick(navigator, context)
                            }
                        }
                    },
                cardColor = Primary,
                borderColor = Color.Transparent
            ) {

                if (!state.isValidatingPinCode) {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = context.getString(R.string.validate),
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
fun ActivationPinScreenreview() {
    MarketAppTheme {
        ActivationPinScreen(
            navigator = null
        )
    }
}
