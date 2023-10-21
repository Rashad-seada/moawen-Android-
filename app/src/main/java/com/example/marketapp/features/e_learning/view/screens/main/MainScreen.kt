package com.example.marketapp.features.e_learning.view.screens.main

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.marketapp.core.ui.theme.MarketAppTheme
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.features.e_learning.view.viewmodels.main.MainState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@SuppressLint("UnrememberedMutableState")
@Destination
@Composable
fun MainScreen(
    navigator: DestinationsNavigator?,
    state: MainState = MainState(),
    onIndexChange : (Int) -> Unit = {}
    ) {

    val context: Context = LocalContext.current


    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100,
        bottomBar = {
            NavigationBar {
                state.pages.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(painter = painterResource(id = item.icon), contentDescription = "") },
                        label = {
                            Text(context.getString(item.label))
                        },
                        selected = state.index == index,
                        onClick = {
                            onIndexChange(index)
                        }
                    )
                }
            }
        },

        ) {
        it

        AnimatedContent(
            modifier = Modifier.fillMaxSize(),
            targetState = state.index,
            label = ""
        ) {
            state.pages[it].page()
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
