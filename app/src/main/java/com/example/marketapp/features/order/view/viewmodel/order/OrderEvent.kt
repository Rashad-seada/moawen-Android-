package com.example.marketapp.features.order.view.viewmodel.order

import android.content.Context
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class OrderEvent {
    data class OnConfirmClick(val navigator: DestinationsNavigator, val context: Context) : OrderEvent()
}