package com.example.marketapp.features.profile.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
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

@Composable
fun ProfileItem(

    icon : Int = R.drawable.profile,
    leadingLabel : String = "",
    trailingLabel : String = "",
    onClick : ()-> Unit = {}

) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick()
        }
    ){

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) Neutral200 else Neutral800
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = leadingLabel,
                    style = TextStyle(
                        fontFamily = Lato,
                        color = if (isSystemInDarkTheme()) Neutral200 else Neutral800,
                        fontSize = 16.sp
                    )
                )
            }

            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {


                Text(
                    text = trailingLabel,
                    style = TextStyle(
                        fontFamily = Lato,
                        color = if (isSystemInDarkTheme()) Neutral700 else Neutral300,
                        fontSize = 16.sp
                    )
                )

                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) Neutral600 else Neutral400
                )
            }


        }
        Spacer(modifier = Modifier.height(20.dp))

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            thickness = 1.5.dp,
            color = if(isSystemInDarkTheme()) Neutral800 else Neutral200
        )
    }

}