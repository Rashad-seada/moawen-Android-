package com.example.marketapp.core.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marketapp.core.ui.theme.Neutral400
import com.example.marketapp.core.ui.theme.Neutral800
import com.example.marketapp.core.ui.theme.Neutral900

@Composable
fun CustomCheckBox(
    checked: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit) = {},
    modifier : Modifier = Modifier,
    title:String = "",
    titleColor : Color = Neutral800
){

    val colors = CheckboxDefaults.colors(
        checkmarkColor = Color.White,
        checkedColor = Neutral900,
        uncheckedColor = Neutral400,
    )

    Row {

        Spacer(modifier = modifier)
        Card(
            modifier = Modifier.background(Color.White),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(1.5.dp, color = titleColor)
        ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(if (checked) titleColor else Color.White)
                    .clickable {
                        onCheckedChange
                    },
            ) {
                if(checked)
                    Icon(Icons.Default.Check, contentDescription = "", tint = Color.White)
            }
        }

        Text(
            modifier = Modifier
                .align(CenterVertically)
                .padding(start = 10.dp),
            text = title,
        )
    }
}


@Preview
@Composable
fun CustomCheckBoxPreview(){
    CustomCheckBox(
        checked = false,
        title = "Remember Me"
    )
}