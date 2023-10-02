package com.example.marketapp.features.order.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*

@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    placeHolder: String = "",
    onValueChange: (String) -> Unit = {},
    isError: Boolean = false,
    errorMessage: String = "",
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
        unfocusedPlaceholderColor = Neutral400,

        errorContainerColor = Color.Transparent,
        errorLabelColor = if (isSystemInDarkTheme()) Error400Clr else Error500Clr,
        errorIndicatorColor = if (isSystemInDarkTheme()) Error400Clr else Error500Clr

    )

    Column {

        OutlinedTextField(
            modifier = modifier,
            value = if (isSecure) "●".repeat(value.length) else value,
            onValueChange = { newValue ->

                if (isSecure) {
                    if (newValue.length <= value.length) {
                        // Handle backspace or character removal
                        onValueChange(value.dropLast(1))
                    } else {
                        // Handle character addition

                        val addedChar = newValue.last()
                        onValueChange(value + addedChar)
                    }
                } else {
                    onValueChange(newValue)
                }

            },
            maxLines = 1,
            label = {
                Text(
                    text = label,
                    style = TextStyle(
                        fontFamily = Lato,
                        fontSize = 14.sp
                    )
                )
            },
            placeholder = {
                Text(
                    text = placeHolder,
                    style = TextStyle(
                        fontFamily = Lato,
                        fontSize = 14.sp
                    )
                )
            },
            isError = isError,
            colors = colors,
            textStyle = TextStyle(
                fontFamily = Lato,
                color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                fontSize = 14.sp
            ),
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    painter = painterResource(
                        id = R.drawable.search
                    ),
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
                )
            },
            trailingIcon = trailingIcon,
            shape = RoundedCornerShape(100.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isSecure) KeyboardType.Password else KeyboardType.Text // This disables suggestions
            )
        )



        Spacer(modifier = Modifier.height(2.dp))

        if (isError) {
            Text(
                text = errorMessage,
                fontFamily = Lato,
                color = if (isSystemInDarkTheme()) Error400Clr else Error500Clr,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .fillMaxWidth()
                    .then(modifier),
                textAlign = TextAlign.End
            )
        }
    }

}

@Preview
@Composable
fun CustomSearchBarPreview(){
    CustomSearchBar()
}