package com.example.marketapp.features.notification.views.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.features.notification.data.entities.get_all_notification.Notification

@Composable
fun NotificationItem(
    notification: Notification
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .wrapContentHeight()
            .shadow(
                elevation = if (isSystemInDarkTheme()) 10.dp else 10.dp,
                shape = RoundedCornerShape(24.dp),
                clip = false,
                ambientColor = DefaultShadowColor,
                spotColor = DefaultShadowColor,
            ),
        shape = RoundedCornerShape(24.dp),
        color = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ){
        Row(
            modifier = Modifier
                .padding(horizontal = 13.dp, vertical = 11.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                modifier = Modifier
                    .size(48.dp),
                shape = RoundedCornerShape(48.dp),
                color = Secondary.copy(alpha = 0.1f)
            ){
                Box(modifier = Modifier.fillMaxSize()){

                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(24.dp),
                        painter = painterResource(id = R.drawable.notification),
                        contentDescription = null,
                        tint = Secondary
                    )

                }
            }

            Spacer(modifier = Modifier.width(13.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = notification.title,
                    style = TextStyle(
                        fontFamily = Lato,
                        color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = notification.data,
                    style = TextStyle(
                        fontFamily = Lato,
                        color = if (isSystemInDarkTheme()) Neutral500 else Neutral500,
                        fontSize = 12.sp
                    )
                )

            }
        }

    }


}