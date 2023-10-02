package com.example.marketapp.features.home.view.screens.main

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.features.home.view.viewmodels.main.MainState
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
            NavigationBar(
                containerColor = Color.Transparent
            ) {

                state.pages.forEachIndexed { index, item ->
                    NavigationBarItem(
                        modifier = Modifier.clip(CircleShape),
                        icon = { Icon(painter = painterResource(id = item.icon), contentDescription = "") },
//                        label = {
//                            Text(context.getString(item.label))
//                        },
                        selected = state.index == index,
                        onClick = {
                            onIndexChange(index)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Neutral100,
                            unselectedIconColor = if(isSystemInDarkTheme()) Neutral600 else Neutral400,
                            indicatorColor = Primary
                        ),

                    )
                }
            }
        },

        ) {
        it

        AnimatedContent(
            modifier = Modifier.fillMaxSize(),
            targetState = state.index,
            label = "",
        ) {
            if (navigator != null) {
                state.pages[it].page(navigator)
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
