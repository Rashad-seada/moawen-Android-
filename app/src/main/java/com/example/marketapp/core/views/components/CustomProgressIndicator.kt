package com.example.marketapp.core.views.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral900

@Composable
fun CustomProgressIndicator(
    modifier : Modifier = Modifier,
    color : Color = Neutral100
){
    CircularProgressIndicator(
        modifier = modifier,
        strokeWidth = 2.dp,
        color = color
    )
}