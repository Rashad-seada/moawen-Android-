package com.example.marketapp.features.profile.view.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
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
fun NotificatonSettingsItem(
    label : String = "",
    checked : Boolean = false,
    onCheckedChange : (Boolean) -> Unit = {}
){

    Column {
        Spacer(modifier = Modifier.height(5.dp))

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


            Switch(
                checked = checked,
                onCheckedChange = {
                    onCheckedChange(it)
                },
                colors = SwitchDefaults.colors(
                    checkedBorderColor = Color.Transparent,
                    checkedThumbColor = if(isSystemInDarkTheme()) Neutral900 else Neutral100,
                    checkedTrackColor = Secondary,

                    uncheckedBorderColor = Color.Transparent,
                    uncheckedThumbColor = if(isSystemInDarkTheme()) Neutral900 else Neutral100,
                    uncheckedTrackColor = if(isSystemInDarkTheme()) Neutral800 else Neutral200
                )
            )

        }
        Spacer(modifier = Modifier.height(5.dp))

    }

}

