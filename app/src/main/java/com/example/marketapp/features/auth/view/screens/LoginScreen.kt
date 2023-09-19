package com.example.marketapp.features.auth.view.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Cairo
import com.example.marketapp.core.ui.theme.MarketAppTheme
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral300
import com.example.marketapp.core.ui.theme.Neutral400
import com.example.marketapp.core.ui.theme.Neutral500
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.core.ui.theme.Primary900
import com.example.marketapp.core.views.components.CustomCheckBox
import com.example.marketapp.core.views.components.CustomTextField
import com.example.marketapp.core.views.components.MainButton
import com.ramcosta.composedestinations.annotation.Destination

@SuppressLint("ResourceType")
@Destination
@Composable
fun LoginScreen(
    context: Context = LocalContext.current,
) {
    Scaffold(
        containerColor = if( isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        it

        Column (modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())){
            Spacer(modifier = Modifier.height(20.dp))



            Image(
                painter = painterResource(id = R.drawable.education), // Provide the resource ID
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth() // Adjust the size as needed
                    .height(180.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )

            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = context.getString(R.string.login),
                style = TextStyle(
                    fontFamily = Cairo,
                    color = if( isSystemInDarkTheme()) Neutral100 else Neutral900,
                    fontSize = 28.sp
                )
            )
            //Spacer(modifier = Modifier.height(5.dp))

            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = context.getString(R.string.login_sub_text),
                style = TextStyle(
                    fontFamily = Cairo,
                    color = Neutral500,
                    fontSize = 16.sp,

                    )
            )

            Spacer(modifier = Modifier.height(20.dp))


            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = context.getString(R.string.username),
                placeHolder = context.getString(R.string.username_hint),
                prefix = {
                    Image(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.profile_inactive),
                        contentDescription = ""
                    )
                },

                )
            Spacer(modifier = Modifier.height(5.dp))

            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = context.getString(R.string.password),
                placeHolder = context.getString(R.string.password_hint),
                prefix = {
                    Image(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.lock_inactive),
                        contentDescription = ""
                    )
                },

                )

            Spacer(modifier = Modifier.height(10.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ){
                CustomCheckBox(
                    title = context.getString(R.string.remember_me)
                )

                Text(

                    modifier = Modifier.wrapContentHeight(),
                    text = context.getString(R.string.forgot_password),
                    style = TextStyle(
                        fontFamily = Cairo,
                        color = Primary900,
                        fontSize = 16.sp,

                        ),
                    textAlign = TextAlign.End
                )
            }

            Spacer(modifier = Modifier.height(70.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.Center,

                ){
                Text(

                    modifier = Modifier.wrapContentSize(),
                    text = context.getString(R.string.dont_have_account),
                    style = TextStyle(
                        fontFamily = Cairo,
                        color = Neutral400,
                        fontSize = 16.sp,

                        ),
                    textAlign = TextAlign.End
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(

                    modifier = Modifier.wrapContentSize(),
                    text = context.getString(R.string.register),
                    style = TextStyle(
                        fontFamily = Cairo,
                        color = Primary900,
                        fontSize = 16.sp,

                        ),
                    textAlign = TextAlign.End
                )
            }

            Spacer(modifier = Modifier.height(15.dp))


            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(55.dp),
                cardColor = Primary900,
                borderColor = Color.Transparent
            ){
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = context.getString(R.string.login),
                    style = TextStyle(
                        fontFamily = Cairo,
                        color = Neutral100,
                        fontSize = 16.sp,
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))


            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){

                HorizontalDivider(
                    modifier = Modifier
                        .width(50.dp)
                        .height(1.7.dp),
                    color = Neutral300
                )

                Text(

                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 10.dp),
                    text = context.getString(R.string.or_login_with),
                    style = TextStyle(
                        fontFamily = Cairo,
                        color = Neutral400,
                        fontSize = 16.sp,
                        ),
                    textAlign = TextAlign.End
                )
                HorizontalDivider(
                    modifier = Modifier
                        .width(50.dp)
                        .height(1.5.dp),
                    color = Neutral300
                )


            }

            Spacer(modifier = Modifier.height(20.dp))


            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(55.dp),
                cardColor = Color.Transparent,
                borderColor = Neutral500
            ){
                Row (
                    verticalAlignment =  Alignment.CenterVertically,
                ){
                    Image(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = ""
                    )

                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = context.getString(R.string.google),
                        style = TextStyle(
                            fontFamily = Cairo,
                            color = if( isSystemInDarkTheme()) Neutral100 else Neutral900,
                            fontSize = 16.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))




        }


    }

}

@Preview
@Composable
fun DefaultPreview() {
    MarketAppTheme {
        LoginScreen()
    }
}


