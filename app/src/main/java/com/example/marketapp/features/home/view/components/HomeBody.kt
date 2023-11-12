package com.example.marketapp.features.home.view.components

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Lato
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.core.ui.theme.Primary
import com.example.marketapp.core.views.components.MainButton
import com.example.marketapp.core.views.components.shimmerEffect

@Composable
fun HomeBody(
    isLoading : Boolean = false,
    assistantImage: String,
    assistantName: String,
    onSendOrderClick: () -> Unit = {}
) {
    val context: Context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = context.getString(R.string.today_assistant_is),
            style = TextStyle(
                fontFamily = Lato,
                color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(18.dp))

        if(isLoading){
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .width(90.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .shadow(
                        elevation = if (isSystemInDarkTheme()) 10.dp else 10.dp,
                        shape = RoundedCornerShape(24.dp),
                        clip = false,
                        ambientColor = DefaultShadowColor,
                        spotColor = DefaultShadowColor,
                    )
                    .shimmerEffect(),
            )
        }else{
            Text(
                text = assistantName,
                style = TextStyle(
                    fontFamily = Lato,
                    color = if (isSystemInDarkTheme()) Neutral100 else Primary,
                    fontSize = 32.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(if(isLoading) 20.dp else 12.dp))




        if(isLoading){
            Box(
                modifier = Modifier
                    .height(240.dp)
                    .width(240.dp)
                    .clip(CircleShape)
                    .shadow(
                        elevation = if (isSystemInDarkTheme()) 10.dp else 10.dp,
                        shape = CircleShape,
                        clip = false,
                        ambientColor = DefaultShadowColor,
                        spotColor = DefaultShadowColor,
                    )
                    .shimmerEffect(),
            )
        }else{
            Image(
                modifier = Modifier
                    .height(240.dp)
                    .width(240.dp)
                    .clip(CircleShape)
                    .border(
                        BorderStroke(3.dp, Color(0xffCDCDCD)),
                        CircleShape
                    ),
                painter = rememberImagePainter(data = assistantImage, builder = {
                    transformations(CircleCropTransformation()) // Apply transformations if needed
                    placeholder(R.drawable.photo) // Placeholder resource while loading
                    error(R.drawable.photo_error) // Error resource if loading fails
                }),
                contentDescription = null, //
                contentScale = ContentScale.FillHeight
            )
        }




        Spacer(modifier = Modifier.height(48.dp))

        if(isLoading) {

        }else{
            Text(
                text = context.getString(R.string.start_your_order_now),
                style = TextStyle(
                    fontFamily = Lato,
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                    fontSize = 18.sp
                )
            )

            Spacer(modifier = Modifier.height(38.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                MainButton(
                    modifier = Modifier
                        .width(136.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .clickable {
                            onSendOrderClick()
                        },
                    cardColor = Primary,
                    borderColor = Color.Transparent
                ) {


                    Text(
                        text = context.getString(R.string.make_order),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = Neutral100,
                            fontSize = 16.sp
                        )
                    )

                }
            }
        }





    }
}