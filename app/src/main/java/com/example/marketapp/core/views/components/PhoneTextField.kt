package com.example.marketapp.core.views.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    placeHolder: String = "",
    onValueChange: (String) -> Unit = {},
    onPhoneChange: (PhoneNumber) -> Unit = {},
    isError: Boolean = false,
    errorMessage: String = "",
    trailingIcon: @Composable (() -> Unit) = {},
    countries: List<Country> = defaultCountries
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    var selectedCountry by remember {
        mutableStateOf(countries[0])
    }


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

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
    ) {

        Surface(
            modifier = Modifier
                .height(60.dp)
                .width(100.dp)
                .padding(top = 8.dp)
                .clickable {
                    showBottomSheet = true
                    scope.launch {
                        sheetState.show()
                    }
                },
            shape = RoundedCornerShape(100.dp), border = BorderStroke(
                1.dp,
                if (isSystemInDarkTheme()) Neutral600 else Neutral300,
            ), color = Color.Transparent
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
                    painter = rememberImagePainter(data = selectedCountry.flag, builder = {
                        transformations(CircleCropTransformation()) // Apply transformations if needed
                        placeholder(R.drawable.photo) // Placeholder resource while loading
                        error(R.drawable.photo_error) // Error resource if loading fails
                    }),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight
                )
                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = selectedCountry.countryCode,
                    fontFamily = Lato,
                    color = Neutral500,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End
                )
            }


        }

        Spacer(modifier = Modifier.width(10.dp))


        Column(
            verticalArrangement = Arrangement.Top
        ) {

            OutlinedTextField(value = value, onValueChange = { newValue ->
                onValueChange(newValue)
                onPhoneChange(PhoneNumber(selectedCountry.countryCode,newValue))
            }, maxLines = 1, label = {
                Text(
                    text = label, style = TextStyle(
                        fontFamily = Lato, fontSize = 14.sp
                    )
                )
            }, placeholder = {
                Text(
                    text = placeHolder, style = TextStyle(
                        fontFamily = Lato, fontSize = 14.sp
                    )
                )
            },

                isError = isError, colors = colors, textStyle = TextStyle(
                    fontFamily = Lato,
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                    fontSize = 14.sp
                ),
                //modifier = modifier,
                shape = RoundedCornerShape(100.dp), keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number // This disables suggestions
                ), leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.phone),
                        contentDescription = "",
                        tint = if (isSystemInDarkTheme()) Neutral200 else Neutral800

                    )
                }, trailingIcon = trailingIcon
            )




            if (isError) {
                Spacer(modifier = Modifier.height(2.dp))

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

            repeat(countries.size) { index ->
                Row(modifier = modifier
                    .clickable {
                        selectedCountry = countries[index]
                        scope.launch {
                            sheetState.hide()
                            showBottomSheet = false

                        }

                    }
                    .padding(bottom = 20.dp), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier
                            .height(30.dp)
                            .width(50.dp),
                        painter = rememberImagePainter(data = countries[index].flag, builder = {
                            transformations(CircleCropTransformation()) // Apply transformations if needed
                            placeholder(R.drawable.photo) // Placeholder resource while loading
                            error(R.drawable.photo_error) // Error resource if loading fails
                        }),
                        contentDescription = null, //
                        contentScale = ContentScale.FillHeight
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = countries[index].countryCode, style = TextStyle(
                            fontFamily = Lato,
                            color = if (isSystemInDarkTheme()) Neutral400 else Neutral600,
                            fontSize = 16.sp,

                            )
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(
                        text = countries[index].countryName, style = TextStyle(
                            fontFamily = Lato,
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
    val flag: String,
    val countryCode: String,
    val countryName: String,
)

data class PhoneNumber(
    val countryCode: String,
    val phoneNumber: String
)

val defaultCountries = listOf(
    Country(
        "https://moawen.safeeralemarat.com/storage/images/countryFlag/pngtree-egypt-flag-icon-png-image_6873090.png",
        "+20",
        "Egypt",
    ), Country(
        "https://moawen.safeeralemarat.com/storage/images/countryFlag/download.png",
        "+971",
        "United Arab Emirates"
    )
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
