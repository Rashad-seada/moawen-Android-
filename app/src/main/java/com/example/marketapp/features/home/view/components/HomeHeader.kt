package com.example.marketapp.features.home.view.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Lato
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Primary
import com.example.marketapp.core.ui.theme.Secondary
import com.example.marketapp.core.views.components.shimmerEffect

@Composable
fun HomeHeader(
    isLoading : Boolean = false,
    profileImage : String,
    username : String,
    wallet : String,
    currency : String
) {
    val context: Context = LocalContext.current

    Surface(
        Modifier
            .fillMaxWidth()
            .height(213.dp),
        shape = RoundedCornerShape(bottomStart = 74.dp, bottomEnd = 74.dp),
        color = Primary
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                if(isLoading){
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .shimmerEffect(),
                    )
                }else{
                    Image(
                        modifier = Modifier
                            .height(48.dp)
                            .width(48.dp)
                            .clip(CircleShape),
                        painter = rememberImagePainter(data = profileImage, builder = {
                            transformations(CircleCropTransformation()) // Apply transformations if needed
                            placeholder(R.drawable.photo) // Placeholder resource while loading
                            error(R.drawable.photo_error) // Error resource if loading fails
                        }),
                        contentDescription = null, //
                        contentScale = ContentScale.FillHeight
                    )
                }


                Spacer(modifier = Modifier.width(12.dp))

                if(isLoading){
                    Box(
                        modifier = Modifier
                            .height(20.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .shimmerEffect(),
                    )
                }else{
                    Text(
                        text = context.getString(R.string.welcome) + " ",
                        style = TextStyle(
                            fontFamily = Lato,
                            color = Neutral100,
                            fontSize = 18.sp
                        )
                    )

                    Text(
                        text = username ,
                        style = TextStyle(
                            fontFamily = Lato,
                            color = Neutral100,
                            fontSize = 18.sp
                        )
                    )
                }



            }




            Spacer(modifier = Modifier.height(30.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                if(isLoading){
                    Box(
                        modifier = Modifier
                            .height(25.dp)
                            .width(50.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .shimmerEffect(),
                    )
                }else{
                    Text(
                        text = wallet,
                        style = TextStyle(
                            fontFamily = Lato,
                            color = Secondary,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = currency,
                        style = TextStyle(
                            fontFamily = Lato,
                            color = Neutral100,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraLight

                        )
                    )
                }


            }



            Spacer(modifier = Modifier.height(if(isLoading) 16.dp else 8.dp))

            Text(
                text = context.getString(R.string.total_balance),
                style = TextStyle(
                    fontFamily = Lato,
                    color = Neutral100.copy(alpha = 0.5f),
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

        }

    }
}