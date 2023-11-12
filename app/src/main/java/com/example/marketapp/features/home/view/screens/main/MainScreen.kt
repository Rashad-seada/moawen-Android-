package com.example.marketapp.features.home.view.screens.main

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.features.home.view.components.HomeNavigationBar
import com.example.marketapp.features.home.view.viewmodels.main.MainState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@SuppressLint("UnrememberedMutableState")
@Destination
@Composable
fun MainScreen(
    navigator: DestinationsNavigator?,
    state: MainState = MainState(),
    onIndexChange: (Int) -> Unit = {}
) {

    val context: Context = LocalContext.current


    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100,
        bottomBar = {
            HomeNavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(if(isSystemInDarkTheme()) Neutral900 else Neutral100)
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                index = state.index,
                pages = state.pages,
                onChange = { onIndexChange(it) }
            )

        },

        ) {
        it

        AnimatedContent(
            modifier = Modifier.fillMaxSize(),
            targetState = state.index,
            label = "",
        ) {
            if (navigator != null) {
                state.pages[it].page(navigator,context)
            }
        }


    }

}

@Preview
@Composable
fun MainScreenPreview() {
    MarketAppTheme {
        MainScreen(
            navigator = null
        )
    }
}
