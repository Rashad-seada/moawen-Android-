package com.example.marketapp.features.profile.view.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
fun UsernameField(
    value : String = "Rashad Seada",
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

        BasicTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 0.dp),
            minLines = 1,
            maxLines = 1,
            textStyle = TextStyle(
                fontFamily = Lato,
                fontSize = 14.sp,
                color = if(isSystemInDarkTheme()) Neutral100 else Neutral900
            ),
            decorationBox = { innerTextField ->
                Box {

                    Box(
                        modifier = Modifier.padding(top = 19.dp),
                    ) {
                        innerTextField()
                    }



                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterEnd),
                        painter = painterResource(
                            id = R.drawable.edit_2
                        ),
                        contentDescription = null,
                        tint = if (isSystemInDarkTheme()) Neutral300 else Neutral700
                    )
                }
            },
            cursorBrush = SolidColor(if(isSystemInDarkTheme()) Neutral100 else Neutral900)
        )



    }

}