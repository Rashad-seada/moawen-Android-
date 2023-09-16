package com.example.marketapp.core.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marketapp.core.ui.theme.Neutral400
import com.example.marketapp.core.ui.theme.Neutral500
import com.example.marketapp.core.ui.theme.Neutral700
import com.example.marketapp.core.ui.theme.Neutral900

@Composable
fun CustomTextField(
    value: String = "",
    label : String = "",
    placeHolder :String = "",
    onValueChange: (String) -> Unit = {},
    isError: Boolean = false,
    errorMessage: String = "",
    modifier: Modifier = Modifier
) {
    Column {

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            placeholder = { Text(text = placeHolder)},
            isError = isError,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Neutral500,
                unfocusedIndicatorColor = Neutral400,

                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,

                focusedLabelColor = Neutral500,
                focusedPlaceholderColor = Neutral400,

                unfocusedLabelColor = Neutral500,
                unfocusedPlaceholderColor = Neutral400

            ),
            textStyle = TextStyle(
                color = Neutral900
            ),

            modifier = modifier,

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
        errorMessage = "Invalid input"
    )
}
