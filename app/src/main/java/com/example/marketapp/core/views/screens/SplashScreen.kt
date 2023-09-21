package com.example.marketapp.core.views.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.MarketAppTheme
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@Destination(start = true)
@Composable
fun SplashScreen(
    viewModel: CoreViewModel = hiltViewModel(),
    navigator: DestinationsNavigator?
) {

    var visible by remember { mutableStateOf(false) }

    val density = LocalDensity.current

    LaunchedEffect(key1 =  true){
        delay(500)
        visible = true
        viewModel.onSplashScreenLaunch(navigator)
    }

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
                Image(
                    painter = painterResource(id = R.drawable.splash), // Provide the resource ID
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth() // Adjust the size as needed
                        .height(120.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                )
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