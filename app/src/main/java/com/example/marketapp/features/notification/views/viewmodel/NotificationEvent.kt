package com.example.marketapp.features.notification.views.viewmodel

import android.content.Context
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class NotificationEvent{

    data class OnGetNotification(val context: Context) : NotificationEvent()

}
