package com.example.marketapp.features.home.view.pages.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.marketapp.core.views.components.CustomProgressIndicator
import com.example.marketapp.core.views.components.MainButton
import com.example.marketapp.features.home.view.components.HomeBody
import com.example.marketapp.features.home.view.components.HomeHeader
import com.example.marketapp.features.home.view.viewmodels.home.HomeState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@SuppressLint("UnrememberedMutableState")
@Destination
@Composable
fun HomePage(
    navigator: DestinationsNavigator?,
    state: HomeState = HomeState(),
    init: (Context) -> Unit = {},
    onSendOrderClick: (DestinationsNavigator, Context) -> Unit = { _, _ -> },

    ) {

    val context: Context = LocalContext.current

    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {

        LaunchedEffect(true) {
            init(context)
        }

        it
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            if (state.error != null) {


            } else {
                HomeHeader(
                    isLoading = state.isHomePageLoading,
                    profileImage = state.profileImage ?: "---",
                    username = state.username ?: "---",
                    wallet = state.wallet.toString(),
                    currency = state.currency ?: "---"
                )
            }



            Spacer(modifier = Modifier.height(28.dp))

            if (state.error != null) {

                    Spacer(modifier = Modifier.height(185.dp))

                    Icon(
                        modifier = Modifier
                            .width(150.dp)
                            .height(150.dp),
                        painter = painterResource(id = R.drawable.warning),
                        contentDescription = null,
                        tint = if(isSystemInDarkTheme()) Neutral300 else Neutral700
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        modifier = Modifier.padding(horizontal = 50.dp),
                        text = state.error!!.toString(),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = if (isSystemInDarkTheme()) Neutral200 else Neutral800,
                            fontSize = 18.sp,
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    MainButton(
                        modifier = Modifier
                            .width(136.dp)
                            .height(48.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .clickable {
                                init(context)
                            },
                        cardColor = Primary,
                        borderColor = Color.Transparent
                    ) {


                        Text(
                            text = context.getString(R.string.try_again),
                            style = TextStyle(
                                fontFamily = Lato,
                                color = Neutral100,
                                fontSize = 16.sp
                            )
                        )

                    }





            } else {
                HomeBody(
                    isLoading = state.isHomePageLoading,
                    assistantImage = state.assistantImage ?: "---",
                    assistantName = state.assistantName ?: "",
                    onSendOrderClick = {
                        if (navigator != null) {
                            onSendOrderClick(navigator, context)
                        }
                    }
                )
            }


        }


    }


}



@Preview
@Composable
fun HomePagePreview() {
    MarketAppTheme {
        HomePage(
            navigator = null
        )
    }
}
