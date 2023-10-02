package com.example.marketapp.features.home.view.utils

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marketapp.R
import com.example.marketapp.features.home.view.pages.home.HomePage
import com.example.marketapp.features.home.view.viewmodels.home.HomeEvent
import com.example.marketapp.features.home.view.viewmodels.home.HomeViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class BottomNavPage(
    val page: @Composable (DestinationsNavigator) -> Unit,
    val icon: Int,
    val label: Int
) {
    object HomeNavPage : BottomNavPage(
        page = {
            val viewModel : HomeViewModel = hiltViewModel()
            HomePage(
                navigator = it,
                state = viewModel.state,
                init = {context -> viewModel.onEvent(HomeEvent.OnHomePageInit(context))  },
                onContactAssistantClick = { navigator,context -> viewModel.onEvent(HomeEvent.OnContactClick(navigator,context)) },
                onSendOrderClick = { navigator,context ->  viewModel.onEvent(HomeEvent.OnMakeOrderClick(navigator,context))},
            )
        },
        icon = R.drawable.home,
        label = R.string.home
    )

}

