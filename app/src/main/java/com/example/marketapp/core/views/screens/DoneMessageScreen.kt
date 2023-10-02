package com.example.marketapp.core.views.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.marketapp.core.views.components.LottieAnimation
import com.example.marketapp.core.views.components.MainButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DoneMessageScreen(
    onButtonTap : (DestinationsNavigator) -> Unit = {},
    navigator: DestinationsNavigator?
) {
    val context: Context = LocalContext.current


    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        it
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            LottieAnimation(
                modifier = Modifier.width(250.dp).height(220.dp),
                isPlaying = true,
                restartOnPlay = true,
                lottieAnimationRes = R.raw.done_animation
            )


            Text(
                text = context.getString(R.string.all_done),
                style = TextStyle(
                    fontFamily = Lato,
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                    fontSize = 28.sp
                )
            )

            Text(
                text = context.getString(R.string.all_done_sub_text),
                style = TextStyle(
                    fontFamily = Lato,
                    color = Neutral500,
                    fontSize = 16.sp,

                    )
            )

            Spacer(modifier = Modifier.height(200.dp))


            MainButton(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(55.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        navigator?.let {
                            onButtonTap(navigator)
                        }
                    }
                ,
                cardColor = Primary,
                borderColor = Color.Transparent
            ){
                Row (
                    verticalAlignment =  Alignment.CenterVertically,
                ){


                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = context.getString(R.string.done),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = if( isSystemInDarkTheme()) Neutral100 else Neutral900,
                            fontSize = 16.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))




        }

    }

}

@Preview
@Composable
fun DefoultMessageScreenPreview() {

    MarketAppTheme {
        DoneMessageScreen(
            navigator = null,
        )
    }
}