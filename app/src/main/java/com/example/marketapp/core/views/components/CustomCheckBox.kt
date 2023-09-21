package com.example.marketapp.core.views.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marketapp.core.ui.theme.Cairo
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral200
import com.example.marketapp.core.ui.theme.Neutral300
import com.example.marketapp.core.ui.theme.Neutral400
import com.example.marketapp.core.ui.theme.Neutral600
import com.example.marketapp.core.ui.theme.Neutral800
import com.example.marketapp.core.ui.theme.Neutral900

@Composable
fun CustomCheckBox(
    checked: Boolean=true,
    modifier: Modifier = Modifier,
    title:String = "",
    titleColor: Color = Neutral800
){




    val colors = CheckboxDefaults.colors(
        checkmarkColor = Color.White,
        checkedColor = Neutral900,
        uncheckedColor = Neutral400,
    )

    Row (
        verticalAlignment = CenterVertically
    ){

        Spacer(modifier = modifier)
        Card(
            modifier = Modifier
                .size(20.dp)
                .background(
                    if (isSystemInDarkTheme()) Neutral300 else Neutral600,
                    shape = RoundedCornerShape(6.dp)
                ),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(1.5.dp, color = titleColor),
            colors = CardDefaults.cardColors(
                containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100,
            )
        ) {
            if(checked)
                Icon(Icons.Default.Check, contentDescription = "", tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900 )
        }

        Text(
            modifier = Modifier
                .align(CenterVertically)
                .padding(start = 10.dp),
            text = title,
            color = if( isSystemInDarkTheme()) Neutral200 else Neutral800,
            fontFamily = Cairo,

            )
    }
}


@Preview
@Composable
fun CustomCheckBoxPreview(){
    CustomCheckBox(
        title = "Remember Me"
    )
}