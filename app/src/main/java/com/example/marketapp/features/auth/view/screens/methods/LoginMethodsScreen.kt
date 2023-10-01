package com.example.marketapp.features.auth.view.screens.methods


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.marketapp.core.ui.theme.Neutral300
import com.example.marketapp.core.ui.theme.Neutral400
import com.example.marketapp.core.ui.theme.Neutral500
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.core.ui.theme.Primary900
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.core.views.components.MainButton
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState

@SuppressLint("ResourceType")
@Destination
@Composable
fun LoginMethodsScreen(
    navigator: DestinationsNavigator?,
    onLoginClick: (DestinationsNavigator) -> Unit = {},
    onRegisterClick: (DestinationsNavigator) -> Unit = {},
    onLoginWithGoogleClick: (DestinationsNavigator, Task<GoogleSignInAccount>) -> Unit = { _, _ -> },
    signInIntent: Intent = Intent()

) {
    val context: Context = LocalContext.current


//    val startForResult =
//        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result: ActivityResult ->
//            Log.v("login with google result", "${result.resultCode}")
//            if (result.resultCode == Activity.RESULT_OK) {
//                val intent = result.data
//                if (result.data != null) {
//                    val task: Task<GoogleSignInAccount> =
//                        GoogleSignIn.getSignedInAccountFromIntent(intent)
//
//                    navigator?.let {
//                        onLoginWithGoogleClick(navigator, task)
//                    }
//
//                }
//            }else {
//                CoreViewModel.showSnackbar("Error:"+context.getString(R.string.google_sign_in_faild))
//            }
//        }
    val state = rememberOneTapSignInState()
    OneTapSignInWithGoogle(
        state = state,
        clientId = "1088814076574-jhr0evbjd2ord9o8eulqiatfek9lnbri.apps.googleusercontent.com",
        onTokenIdReceived = { tokenId ->
            Log.d("LOG12", tokenId)
            CoreViewModel.showSnackbar("Success:$tokenId")
        },
        onDialogDismissed = { message ->
            Log.d("LOG12", message)
            CoreViewModel.showSnackbar("Error:$message")
        }
    )


    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        it


        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Image(
                painter = painterResource(id = R.drawable.welcome4), // Provide the resource ID
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth() // Adjust the size as needed
                    .height(220.dp)
                    .padding(horizontal = 20.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(start = 10.dp),
                text = context.getString(R.string.welocme),
                style = TextStyle(
                    fontFamily = Cairo,
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                    fontSize = 32.sp
                )
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(end = 50.dp, start = 10.dp),
                text = context.getString(R.string.welocme_sub_text),
                style = TextStyle(
                    fontFamily = Cairo,
                    color = Neutral500,
                    fontSize = 16.sp,

                    )
            )

            Spacer(modifier = Modifier.height(30.dp))

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(55.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        navigator?.let {
                            onLoginClick(navigator)
                        }
                    },
                cardColor = Primary900,
                borderColor = Color.Transparent
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = context.getString(R.string.login),
                    style = TextStyle(
                        fontFamily = Cairo,
                        color = Neutral100,
                        fontSize = 16.sp,
                    )
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
                            onRegisterClick(navigator)
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
                        fontFamily = Cairo,
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
                            state.open()
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
                            fontFamily = Cairo,
                            color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                            fontSize = 16.sp
                        )
                    )
                }
            }

            //Spacer(modifier = Modifier.height(30.dp))


        }


    }

}

@Preview
@Composable
fun MethodsScreenPreview() {
    MarketAppTheme {
        LoginMethodsScreen(
            navigator = null
        )
    }
}


