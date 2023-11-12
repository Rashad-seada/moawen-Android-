package com.example.marketapp.features.home.view.components

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral500
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.core.ui.theme.Primary
import com.example.marketapp.features.home.view.utils.BottomNavPage

@Composable
fun HomeNavigationBar(
    modifier: Modifier = Modifier,
    pages : List<BottomNavPage> = emptyList(),
    index : Int = 0,
    onChange : (Int) -> Unit = {}
){


    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        pages.forEachIndexed { thisIndex, item ->

            val color = remember { Animatable(if(thisIndex == index) Primary else Color.Transparent) }

            LaunchedEffect(index) {
                if(thisIndex == index){
                    color.animateTo(Primary, animationSpec = tween(500))
                }else {
                    color.animateTo(Color.Transparent, animationSpec = tween(500))

                }
            }

            Surface(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(1000.dp))
                    .clickable {
                        onChange(thisIndex)
                    },
                shape = RoundedCornerShape(1000.dp),
                color = color.value
            ) {

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            ,
                        painter = painterResource(
                            id = item.icon
                        ),
                        contentDescription = null,
                        tint = if(thisIndex == index) Neutral100 else Neutral500
                    )


                }


            }

        }
    }



}