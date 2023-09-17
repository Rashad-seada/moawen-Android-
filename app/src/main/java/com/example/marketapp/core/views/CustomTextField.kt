package com.example.marketapp.core.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Neutral300
import com.example.marketapp.core.ui.theme.Neutral400
import com.example.marketapp.core.ui.theme.Neutral500
import com.example.marketapp.core.ui.theme.Neutral900

@Composable
fun CustomTextField(
    value: String = "",
    label : String = "",
    placeHolder :String = "",
    onValueChange: (String) -> Unit = {},
    isError: Boolean = false,
    errorMessage: String = "",
    modifier: Modifier = Modifier,
    prefix: @Composable (() -> Unit) = {},
    suffix: @Composable (() -> Unit) = {},
) {

    val colors = TextFieldDefaults.colors(
        focusedIndicatorColor = Neutral500,
        unfocusedIndicatorColor = Neutral300,

        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,

        focusedLabelColor = Neutral500,
        focusedPlaceholderColor = Neutral400,

        unfocusedLabelColor = Neutral400,
        unfocusedPlaceholderColor = Neutral400

    )

    Column {

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            placeholder = { Text(text = placeHolder)},
            isError = isError,
            colors = colors,
            textStyle = TextStyle(
                color = Neutral900
            ),
            leadingIcon = prefix,
            trailingIcon = suffix,
            modifier = modifier,
            shape = RoundedCornerShape(8.dp)
        )


        Spacer(modifier = Modifier.height(2.dp))

        if (isError) {
            Text(text = errorMessage , color = Color.Red, modifier = Modifier.align(alignment = Alignment.End))
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
        prefix = { Image(painter = painterResource(id = R.drawable.profile_inactive), contentDescription = "") },
        //prefix = ,
    )
}
