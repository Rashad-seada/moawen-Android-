package com.example.marketapp.features.profile.view.screens

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.features.profile.view.components.Header
import com.example.marketapp.features.profile.view.components.NotificatonSettingsItem
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun NotificationSettingsScreen(
    navigator: DestinationsNavigator?,
){
    val context: Context = LocalContext.current

    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState())
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))


            Header(
                label = context.getString(R.string.notification),
                onClick = {}
            )

            Spacer(modifier = Modifier.height(45.dp))

            NotificatonSettingsItem(
                label = context.getString(R.string.get_all_notifications),
                checked = false,
                onCheckedChange = { }
            )

            NotificatonSettingsItem(
                label = context.getString(R.string.do_not_disturb_mode),
                checked = true,
                onCheckedChange = { }
            )

            NotificatonSettingsItem(
                label = context.getString(R.string.silent_mode),
                checked = false,
                onCheckedChange = { }
            )


        }
    }



}



@Preview
@Composable
fun NotificationSettingsScreenPreview(){
    NotificationSettingsScreen(navigator = null)
}