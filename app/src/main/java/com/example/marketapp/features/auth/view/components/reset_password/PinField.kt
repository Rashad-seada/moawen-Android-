package com.example.marketapp.features.auth.view.components.reset_password

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral200
import com.example.marketapp.core.ui.theme.Neutral300
import com.example.marketapp.core.ui.theme.Neutral700
import com.example.marketapp.core.ui.theme.Neutral800
import com.example.marketapp.core.ui.theme.Neutral900


@Composable
fun PinField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    count: Int = 4,
    onComplete: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = value.length){
        // Check if PIN is complete and invoke onComplete
        if (value.length == count) {
            keyboardController?.hide()
            onComplete()
        }
    }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (index in 0 until count) {
                val char = when {
                    index < value.length -> value[index]
                    else -> ""
                }

                Surface(
                    modifier = Modifier
                        .size(60.dp)
                        .padding(4.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(
                        2.dp,
                        if (value.length == index) if (isSystemInDarkTheme()) Neutral100 else Neutral900 else if (isSystemInDarkTheme()) Neutral300 else Neutral700,
                    ),
                    color = if (isSystemInDarkTheme()) Neutral800 else Neutral200
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char.toString(),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSystemInDarkTheme()) Neutral100 else Neutral900
                        )
                    }
                }
            }
        }



        // Create a hidden BasicTextField for the actual input
        BasicTextField(
            value = value,
            onValueChange = {

                if(value.length < count){
                    onValueChange(it)
                }

                if(value.length > it.length){
                    onValueChange(it)
                }

            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            ),
            textStyle = TextStyle(
                color = Color.Transparent,
                fontSize = 20.sp
            ),
            modifier = Modifier
                .fillMaxSize()
                .alpha(0f)
        )
    }
}


@Preview
@Composable
fun PinFieldPreview() {
    PinField(
        value = "123"
    )
}
