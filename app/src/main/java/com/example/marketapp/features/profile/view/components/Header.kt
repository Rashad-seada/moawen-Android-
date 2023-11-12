package com.example.marketapp.features.profile.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Lato
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral900
import com.ramcosta.composedestinations.annotation.Destination

@Composable
fun Header(
    label : String = "",
    onClick : ()-> Unit = {}
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = label,
            style = TextStyle(
                fontFamily = Lato,
                color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                fontSize = 18.sp
            )
        )

        Surface(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(46.dp)
                .shadow(
                    elevation = if (isSystemInDarkTheme()) 10.dp else 10.dp,
                    shape = CircleShape,
                    clip = false,
                    ambientColor = DefaultShadowColor,
                    spotColor = DefaultShadowColor,
                )
                .clip(CircleShape)
                .clickable {
                    onClick()
                },
            shape = CircleShape,
            color = if (isSystemInDarkTheme()) Neutral900 else Neutral100
        ){
            Icon(
                modifier = Modifier
                    .padding(13.dp)
                    .align(Alignment.Center),
                painter = painterResource(
                    id = R.drawable.arrow_left
                ),
                contentDescription = null,
                tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
            )
        }


    }

}