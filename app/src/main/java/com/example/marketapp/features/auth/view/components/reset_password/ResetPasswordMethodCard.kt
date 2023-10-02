package com.example.marketapp.features.auth.view.components.reset_password

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Lato
import com.example.marketapp.core.ui.theme.Neutral400
import com.example.marketapp.core.ui.theme.Neutral500
import com.example.marketapp.core.ui.theme.Neutral600

@Composable
fun ResetPasswordMethodCard(
    modifier: Modifier = Modifier,
    image : Int,
    label : String,
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(80.dp)
            .then(modifier),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color = Neutral500),
        color = Color.Transparent
    ) {

        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(id =image), // Provide the resource ID
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp),
                tint = if(isSystemInDarkTheme()) Neutral400 else Neutral600
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(end = 50.dp, start = 0.dp),
                text = label,
                style = TextStyle(
                    fontFamily = Lato,
                    color = Neutral500,
                    fontSize = 16.sp,

                    )
            )

        }

    }
}

@Composable
@Preview
fun ResetPasswordMethodCardPreview(){
    ResetPasswordMethodCard(
        image = R.drawable.email,
        label = "Reset password with Email"
    )
}