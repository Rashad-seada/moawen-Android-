package com.example.marketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.marketapp.core.ui.theme.MarketAppTheme
import com.example.marketapp.core.views.CustomTextField

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarketAppTheme {
                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    Greeting("Android")
//                }

                CustomTextField(
                    label = "Email",
                    placeHolder = "Please enter your email",
                    value = "rashadatef2@gmail.com",
                    isError = false,
                    errorMessage = "Invalid input"
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MarketAppTheme {
        Greeting("Android")
    }
}