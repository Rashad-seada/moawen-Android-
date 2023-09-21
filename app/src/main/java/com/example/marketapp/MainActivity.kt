package com.example.marketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.marketapp.core.ui.theme.MarketAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarketAppTheme {
                DestinationsNavHost(NavGraphs.root)
//                {
//                    composable(LoginScreenDestination) { //this: DestinationScope<SomeScreenDestination.NavArgs>
//                        LoginScreen(
//
//                        )
//                    }
//
//                    //All screens that don't need the scaffoldState don't need to be specified here
//                }
            }
        }
    }
}
