package com.example.marketapp.core.views.screens

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Lato
import com.example.marketapp.core.ui.theme.MarketAppTheme
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral900
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@Destination(start = true)
@Composable
fun SplashScreen(
    onScreenLaunch : (DestinationsNavigator) -> Unit = {},
    navigator: DestinationsNavigator?
) {

    var visible by remember { mutableStateOf(false) }

    val density = LocalDensity.current

    LaunchedEffect(key1 =  true){
        delay(500)
        visible = true
        navigator?.let {
            onScreenLaunch(navigator)
        }
    }

    val context: Context = LocalContext.current

    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        it
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            AnimatedVisibility(
                visible = visible,
                enter = slideInHorizontally {
                    // Slide in from 40 dp from the top.
                    with(density) { 20.dp.roundToPx() }
                } + fadeIn(
                    // Fade in with the initial alpha of 0.3f.
                    initialAlpha = 0.1f
                ),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.logo2), // Provide the resource ID
                        contentDescription = "",
                        modifier = Modifier
                            .width(49.dp)
                            .height(45.41.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo), // Provide the resource ID
                            contentDescription = "",
                            modifier = Modifier
                                .width(200.dp) // Adjust the size as needed
                                .height(80.dp)
                        )


                    }

                    Text(
                        text = context.getString(R.string.to_deliver_order),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                            fontSize = 14.sp
                        )
                    )
                }
            }




        }

    }

}

@Preview
@Composable
fun DefoultSplashScreenPreview() {

    MarketAppTheme {
        SplashScreen(
            navigator = null
        )
    }
}