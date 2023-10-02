package com.example.marketapp.features.order.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.features.order.infrastructure.services.utils.PlaceInfo

@Composable
fun PlaceItem(placeInfo: PlaceInfo,onClick : (PlaceInfo)-> Unit = {} ){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clickable {
                onClick(placeInfo)
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .size(50.dp)
                .padding(13.dp),
            painter = painterResource(
                id = R.drawable.to
            ),
            contentDescription = null,
            tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {

            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(start = 0.dp),
                text = placeInfo.name,
                style = TextStyle(
                    fontFamily = Lato,
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                    fontSize = 18.sp
                )
            )
            
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(start = 0.dp),
                text = placeInfo.address,
                style = TextStyle(
                    fontFamily = Lato,
                    color = if (isSystemInDarkTheme()) Neutral400 else Neutral600,
                    fontSize = 14.sp
                )
            )

            Spacer(modifier = Modifier.height(10.dp))


        }
    }
}