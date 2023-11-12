package com.example.marketapp.features.profile.view.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.core.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination

@Composable
fun LanguageItem(
    label : String = "",
    languageTag : String = "",
    selected : Boolean = false,
    onClick : (String) -> Unit = {}
){

    Column {
        Spacer(modifier = Modifier.height(2.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = label,
                style = TextStyle(
                    fontFamily = Lato,
                    color = if (isSystemInDarkTheme()) Neutral200 else Neutral800,
                    fontSize = 16.sp
                )
            )


            RadioButton(
                selected = selected,
                onClick = {
                    onClick(languageTag)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Secondary,
                    unselectedColor = if(isSystemInDarkTheme()) Neutral800 else Neutral200,
                )
            )

        }
        Spacer(modifier = Modifier.height(2.dp))

    }

}

