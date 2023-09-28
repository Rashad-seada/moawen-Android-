package com.example.marketapp.features.auth.view.screens.reset_password


import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Cairo
import com.example.marketapp.core.ui.theme.MarketAppTheme
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral500
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.features.auth.view.components.reset_password.ResetPasswordMethodCard
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("ResourceType")
@Destination
@Composable
fun ResetPasswordMethodsScreen(
    navigator: DestinationsNavigator?,
    onBackArrowClick : (DestinationsNavigator)-> Unit = {},
    onResetByEmailClick : (DestinationsNavigator)-> Unit = {},
    onResetByPhoneClick : (DestinationsNavigator)-> Unit = { } ,

    ) {

    val context: Context = LocalContext.current

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
                modifier = Modifier.padding(start = 20.dp).clickable {
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


            Image(
                painter = painterResource(id = R.drawable.reset_password_methods), // Provide the resource ID
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth() // Adjust the size as needed
                    .height(200.dp)
                    .padding(horizontal = 20.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(start = 0.dp),
                text = context.getString(R.string.reset_password),
                style = TextStyle(
                    fontFamily = Cairo,
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                    fontSize = 32.sp
                )
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(end = 50.dp, start = 0.dp),
                text = context.getString(R.string.reset_password_sub_text),
                style = TextStyle(
                    fontFamily = Cairo,
                    color = Neutral500,
                    fontSize = 16.sp,

                    )
            )

            Spacer(modifier = Modifier.height(20.dp))

            ResetPasswordMethodCard(
                modifier = Modifier.clickable {
                    navigator?.let {
                        onResetByEmailClick(navigator)
                    }
                },
                image = R.drawable.email,
                label = context.getString(R.string.reset_password_by_email),
            )
            Spacer(modifier = Modifier.height(20.dp))

            ResetPasswordMethodCard(
                modifier = Modifier.clickable {
                    navigator?.let {
                        onResetByPhoneClick(navigator)
                    }
                },
                image = R.drawable.phone,
                label = context.getString(R.string.reset_password_by_phone),
            )


        }


    }

}

@Preview
@Composable
fun ResetPasswordMethodsScreenPreview() {
    MarketAppTheme {
        ResetPasswordMethodsScreen(
            navigator = null
        )
    }
}


