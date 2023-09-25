package com.example.marketapp.core.views.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Cairo
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral200
import com.example.marketapp.core.ui.theme.Neutral300
import com.example.marketapp.core.ui.theme.Neutral400
import com.example.marketapp.core.ui.theme.Neutral500
import com.example.marketapp.core.ui.theme.Neutral600
import com.example.marketapp.core.ui.theme.Neutral800
import com.example.marketapp.core.ui.theme.Neutral900
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    placeHolder: String = "",
    onValueChange: (String) -> Unit = {},
    isError: Boolean = false,
    errorMessage: String = "",
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    var selectedCountry by remember {
        mutableStateOf(Country(R.drawable.eg, "EG", "Egypt", "+20"))
    }


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

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {

        Surface(
            modifier = Modifier
                .height(60.dp)
                .width(100.dp)
                .clickable {
                    showBottomSheet = true
                    scope.launch {
                        sheetState.show()
                    }
                },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(
                1.dp,
                if (isSystemInDarkTheme()) Neutral600 else Neutral300,
            ),
            color = Color.Transparent
        ) {

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                Image(
                    modifier = Modifier
                        .height(20.dp)
                        .width(30.dp),
                    painter = painterResource(id = selectedCountry.flag),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight
                )
                Spacer(modifier = Modifier.width(10.dp))

                Icon(
                    modifier = Modifier
                        .size(10.dp),
                    painter = painterResource(id = R.drawable.arrow_down),
                    contentDescription = "",
                    tint = if (isSystemInDarkTheme()) Neutral200 else Neutral800
                )
            }


        }

        Spacer(modifier = Modifier.width(10.dp))


        Column {

            OutlinedTextField(
                value = value,
                onValueChange = { newValue ->
                    onValueChange(newValue)
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
                //modifier = modifier,
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number // This disables suggestions
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.phone),
                        contentDescription = "",
                        tint = if (isSystemInDarkTheme()) Neutral200 else Neutral800

                    )
                }
            )




            if (isError) {
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.align(alignment = Alignment.End)
                )
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.height(500.dp),
            onDismissRequest = {

                scope.launch {
                    sheetState.hide()
                    showBottomSheet = false
                }

            },
            sheetState = sheetState,
            containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            repeat(countries.size){ index->
                Row (
                    modifier = modifier.clickable {
                        selectedCountry = countries[index]
                        scope.launch {
                            sheetState.hide()
                            showBottomSheet = false
                        }

                    }.padding(bottom = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        modifier = Modifier
                            .height(30.dp)
                            .width(50.dp),
                        painter = painterResource(id = countries[index].flag ),
                        contentDescription = "",
                        contentScale = ContentScale.FillHeight
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = countries[index].phoneCode ,
                        style = TextStyle(
                            fontFamily = Cairo,
                            color = if (isSystemInDarkTheme()) Neutral400 else Neutral600,
                            fontSize = 16.sp,

                            )
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(
                        text = countries[index].countryName ,
                        style = TextStyle(
                            fontFamily = Cairo,
                            color = Neutral500,
                            fontSize = 16.sp,

                            )
                    )

                }
            }
            // Sheet content
        }
    }

}

data class Country(
    val flag: Int,
    val countryCode: String,
    val countryName: String,
    val phoneCode: String
)

val countries = listOf(
    Country(R.drawable.eg, "EG", "Egypt", "+20"),
    Country(R.drawable.sa, "SA", "Saudi Arabia", "+966"),
    Country(R.drawable.ae, "AE", "United Arab Emirates", "+971")
)


@Preview(showBackground = true)
@Composable
fun PhoneTextFieldPreview() {
    PhoneTextField(
        label = "Phone",
        placeHolder = "Please enter your Phone",
        value = "01050687752",
        isError = false,
        errorMessage = "Invalid input",
        //prefix = ,
    )
}
