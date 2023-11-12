package com.example.marketapp.features.home.view.utils

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marketapp.R
import com.example.marketapp.features.home.view.pages.home.HomePage
import com.example.marketapp.features.home.view.viewmodels.home.HomeEvent
import com.example.marketapp.features.home.view.viewmodels.home.HomeViewModel
import com.example.marketapp.features.notification.views.pages.NotificationsPage
import com.example.marketapp.features.notification.views.viewmodel.NotificationEvent
import com.example.marketapp.features.notification.views.viewmodel.NotificationViewModel
import com.example.marketapp.features.order.view.screens.OrdersDetailsPage
import com.example.marketapp.features.order.view.viewmodel.order.OrderEvent
import com.example.marketapp.features.order.view.viewmodel.order.OrderViewModel
import com.example.marketapp.features.profile.view.pages.ProfilePage
import com.example.marketapp.features.wallet.view.pages.WalletPage
import com.example.marketapp.features.wallet.view.viewmodel.wallet.WalletEvent
import com.example.marketapp.features.wallet.view.viewmodel.wallet.WalletViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class BottomNavPage(
    val page: @Composable (DestinationsNavigator,Context) -> Unit,
    val icon: Int,
    val label: Int
) {
    object HomeNavPage : BottomNavPage(
        page = { navigator , context ->
            val viewModel : HomeViewModel = hiltViewModel()
            HomePage(
                navigator = navigator,
                state = viewModel.state,
                init = {context -> viewModel.onEvent(HomeEvent.OnHomePageInit(context))  },
                onSendOrderClick = { navigator,context ->  viewModel.onEvent(HomeEvent.OnMakeOrderClick(navigator,context))},
            )
        },
        icon = R.drawable.home,
        label = R.string.home
    )

    object OrderNavPage : BottomNavPage(
        page = {navigator , context ->
            val viewModel : OrderViewModel = hiltViewModel()

            OrdersDetailsPage(
                navigator = navigator,
                orderState = viewModel.state,
                getOrders = { viewModel.onEvent(OrderEvent.OnGetOrders(context)) }
            )
        },
        icon = R.drawable.order,
        label = R.string.orders
    )

    object NotificationNavPage : BottomNavPage(
        page = {navigator , context ->
            val viewModel : NotificationViewModel = hiltViewModel()

            NotificationsPage(
                navigator = navigator,
                notificationState = viewModel.state,
                getNotification = { viewModel.onEvent(NotificationEvent.OnGetNotification(context))}
            )
        },
        icon = R.drawable.notification,
        label = R.string.notification
    )

    object WalletNavPage : BottomNavPage(
        page = {navigator , context ->
            val viewModel : WalletViewModel = hiltViewModel()

            WalletPage(
                navigator = navigator,
                state = viewModel.state,
                getBalacne = { viewModel.onEvent(WalletEvent.OnGetBalance(context)) }
            )
        },
        icon = R.drawable.wallet,
        label = R.string.wallet
    )

    object ProfileNavPage : BottomNavPage(
        page = {navigator , context ->
            //val viewModel : OrderViewModel = hiltViewModel()

            ProfilePage(
                navigator = navigator,
            )
        },
        icon = R.drawable.profile,
        label = R.string.profile
    )

}

