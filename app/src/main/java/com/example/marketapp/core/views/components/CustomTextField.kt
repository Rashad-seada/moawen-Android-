package com.example.marketapp.core.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Cairo
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral300
import com.example.marketapp.core.ui.theme.Neutral400
import com.example.marketapp.core.ui.theme.Neutral500
import com.example.marketapp.core.ui.theme.Neutral600
import com.example.marketapp.core.ui.theme.Neutral900

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    placeHolder: String = "",
    onValueChange: (String) -> Unit = {},
    isError: Boolean = false,
    errorMessage: String = "",
    leadingIcon: @Composable (() -> Unit) = {},
    trailingIcon: @Composable (() -> Unit) = {},
    isSecure: Boolean = false
) {

    val colors = TextFieldDefaults.colors(
        focusedIndicatorColor = Neutral500,
        unfocusedIndicatorColor = if (isSystemInDarkTheme()) Neutral600 else Neutral300,

        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,

        focusedLabelColor = if (isSystemInDarkTheme()) Neutral300 else Neutral600,
        focusedPlaceholderColor = if (isSystemInDarkTheme()) Neutral300 else Neutral600,

        unfocusedLabelColor = Neutral400,
        unfocusedPlaceholderColor = Neutral400

    )

    Column {

        OutlinedTextField(
            value = if (isSecure) "●".repeat(value.length) else value,
            onValueChange = { newValue ->

                if(isSecure){
                    if (newValue.length <= value.length) {
                        // Handle backspace or character removal
                        onValueChange(value.dropLast(1))
                    } else {
                        // Handle character addition

                        val addedChar = newValue.last()
                        onValueChange(value + addedChar)
                    }
                }else {
                    onValueChange(newValue)
                }

            },
            maxLines = 1,
            label = {
                Text(
                    text = label,
                    style = TextStyle(
                        fontFamily = Cairo,
                        fontSize = 14.sp
                    )
                )
            },
            placeholder = {
                Text(
                    text = placeHolder,
                    style = TextStyle(
                        fontFamily = Cairo,
                        fontSize = 14.sp
                    )
                )
            },
            isError = isError,
            colors = colors,
            textStyle = TextStyle(
                fontFamily = Cairo,
                color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                fontSize = 14.sp
            ),
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            modifier = modifier,
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = if(isSecure) KeyboardType.Password else KeyboardType.Text // This disables suggestions
            )
        )



        Spacer(modifier = Modifier.height(2.dp))

        if (isError) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.align(alignment = Alignment.End)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustomTextField(
        label = "Email",
        placeHolder = "Please enter your email",
        value = "rashadatef2@gmail.com",
        isError = false,
        errorMessage = "Invalid input",
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.profile_inactive),
                contentDescription = ""
            )
        },
        //prefix = ,
    )
}
