package com.example.marketapp.core.views.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.example.marketapp.core.ui.theme.*

fun Modifier.shimmerEffect() : Modifier = composed {

    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    val transition = rememberInfiniteTransition()

    val startOffsetX by transition.animateFloat(
        initialValue = (-2 * size.width).toFloat(),
        targetValue = (2 * size.width).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000
            )
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                if (isSystemInDarkTheme()) Neutral800 else Neutral200,
                if (isSystemInDarkTheme()) Neutral700 else Neutral300,

                if (isSystemInDarkTheme()) Neutral600 else Neutral400,

                if (isSystemInDarkTheme()) Neutral700 else Neutral300,
                if (isSystemInDarkTheme()) Neutral800 else Neutral200
            ),
            start = Offset(startOffsetX,0f),
            end = Offset(startOffsetX + size.width.toFloat() , size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}