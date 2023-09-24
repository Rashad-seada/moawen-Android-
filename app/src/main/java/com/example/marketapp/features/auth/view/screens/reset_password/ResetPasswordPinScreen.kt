package com.example.marketapp.features.auth.view.screens.reset_password

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
import com.example.marketapp.core.ui.theme.Cairo
import com.example.marketapp.core.ui.theme.MarketAppTheme
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral500
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.core.ui.theme.Primary900
import com.example.marketapp.core.views.components.CustomProgressIndicator
import com.example.marketapp.core.views.components.MainButton
import com.example.marketapp.features.auth.view.components.reset_password.PinField
import com.example.marketapp.features.auth.view.viewmodels.reset_password.ResetPasswordState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch


@SuppressLint("UnrememberedMutableState")
@Destination
@Composable
fun ResetPasswordPinScreen(
    navigator: DestinationsNavigator?,
    state: ResetPasswordState = ResetPasswordState(),
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
    onPinChangeClick: (String) -> Unit = {},
    onValidateClick: (DestinationsNavigator) -> Unit = {},
    onResendClick: () -> Unit = {},
    onComplete: (DestinationsNavigator) -> Unit = {},
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


//            Image(
//                painter = painterResource(id = R.drawable.reset_password_methods), // Provide the resource ID
//                contentDescription = "",
//                modifier = Modifier
//                    .fillMaxWidth() // Adjust the size as needed
//                    .height(200.dp)
//                    .padding(horizontal = 20.dp)
//                    .align(alignment = Alignment.CenterHorizontally)
//            )

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
                        fontFamily = Cairo,
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
                        fontFamily = Cairo,
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
                        onComplete(navigator)
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                horizontalArrangement = Arrangement.End
            ) {


                Text(
                    modifier = Modifier
                        .padding(end = 5.dp),
                    text = context.getString(R.string.didnt_recive_code),
                    style = TextStyle(
                        fontFamily = Cairo,
                        color = Neutral500,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center

                    )
                )

                if (state.isSendingPinCode) {
                    CustomProgressIndicator()
                } else {
                    Text(
                        modifier = Modifier.clickable {
                            onResendClick()
                        },
                        text = context.getString(R.string.resend_new_code),
                        style = TextStyle(
                            fontFamily = Cairo,
                            color = Primary900,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center

                        )
                    )
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
                                onValidateClick(navigator)
                            }
                        }
                    },
                cardColor = Primary900,
                borderColor = Color.Transparent
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = context.getString(R.string.validate),
                    style = TextStyle(
                        fontFamily = Cairo,
                        color = Neutral100,
                        fontSize = 16.sp,
                    )
                )
            }


        }


    }

}

@Preview
@Composable
fun ResetPasswordPinScreenPreview() {
    MarketAppTheme {
        ResetPasswordPinScreen(
            navigator = null
        )
    }
}
