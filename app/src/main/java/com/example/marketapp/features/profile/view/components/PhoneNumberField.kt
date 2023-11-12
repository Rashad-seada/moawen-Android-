package com.example.marketapp.features.profile.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*

@Composable
fun PhoneNumberField(
    value : String = "",
    onValueChange : (String)-> Unit = {}
){

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 30.dp),
        shape = RoundedCornerShape(1000.dp),
        color = if(isSystemInDarkTheme()) Neutral800 else Neutral200
    ) {

        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){

            Image(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(
                    id = R.drawable.ae
                ),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = "+971",
                style = TextStyle(
                    fontFamily = Lato,
                    color = if (isSystemInDarkTheme()) Neutral200 else Neutral800,
                    fontSize = 14.sp
                )
            )
            Spacer(modifier = Modifier.width(10.dp))

            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight(),
                thickness = 1.5.dp,
                color = if(isSystemInDarkTheme()) Neutral700 else Neutral300
            )

            Spacer(modifier = Modifier.width(15.dp))

            BasicTextField(
                value = value,
                onValueChange = { onValueChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                minLines = 1,
                maxLines = 1,
                textStyle = TextStyle(
                    fontFamily = Lato,
                    fontSize = 14.sp,
                    color = if(isSystemInDarkTheme()) Neutral100 else Neutral900
                ),
                cursorBrush = SolidColor(if(isSystemInDarkTheme()) Neutral100 else Neutral900)
            )



        }



    }

}