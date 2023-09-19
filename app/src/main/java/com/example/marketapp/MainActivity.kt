package com.example.marketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.marketapp.core.ui.theme.MarketAppTheme
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navigator : DestinationsNavigator


        setContent {
            MarketAppTheme {
                //DestinationsNavHost(navGraph = )
            }
        }
    }
}
