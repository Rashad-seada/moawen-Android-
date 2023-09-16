package com.example.marketapp.features.auth.view.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Cairo
import com.example.marketapp.core.ui.theme.Neutral500
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.core.views.CustomTextField

@SuppressLint("ResourceType")
@Composable
fun LoginScreen(
    context: Context = LocalContext.current,
) {
    Scaffold {
        it

        Column {
            Spacer(modifier = Modifier.height(30.dp))


            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo), // Provide the resource ID
                    contentDescription = "",
                    modifier = Modifier
                        .width(50.dp) // Adjust the size as needed
                        .height(50.dp)
                )



            }

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = context.getString(R.string.login),
                style = TextStyle(
                    //fontFamily = Cairo,
                    color = Neutral900,
                    fontSize = 28.sp
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = context.getString(R.string.login_sub_text),
                style = TextStyle(
                    //fontFamily = Cairo,
                    color = Neutral500,
                    fontSize = 16.sp,

                )
            )

            Spacer(modifier = Modifier.height(30.dp))


            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = context.getString(R.string.username),
                placeHolder = context.getString(R.string.username_hint),
            )
            Spacer(modifier = Modifier.height(5.dp))

            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = context.getString(R.string.password),
                placeHolder = context.getString(R.string.password_hint),
            )


        }


    }

}

@Preview
@Composable
fun DefaultPreview() {
    LoginScreen()
}


