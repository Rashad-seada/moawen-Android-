package com.example.marketapp.features.home.view.components.profile

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*

@Composable
fun ProfileHeader(

    onProfileImageEditClick : () -> Unit = {},
    imageUrl : String = "",
    username : String = "",
    phoneNumber : String = "",
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Surface(
            Modifier
                .fillMaxWidth()
                .height(145.dp),
            shape = RoundedCornerShape(bottomStart = 74.dp, bottomEnd = 74.dp),
            color = Primary
        ) {
            val context: Context = LocalContext.current

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = context.getString(R.string.profile),
                    style = TextStyle(
                        fontFamily = Lato,
                        color = Neutral100,
                        fontSize = 18.sp
                    )
                )
            }

        }


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(105.dp))

            Box(
                modifier = Modifier.height(140.dp)

            ) {
                Image(
                    modifier = Modifier
                        .height(125.dp)
                        .width(125.dp)
                        .clip(CircleShape)
                        .background(Neutral100, CircleShape)
                        .border(
                            BorderStroke(1.dp, Color(0xffCDCDCD)),
                            CircleShape
                        ),

                    painter = rememberImagePainter(data = imageUrl, builder = {
                        transformations(CircleCropTransformation()) // Apply transformations if needed
                        placeholder(R.drawable.profile).size(50) // Placeholder resource while loading
                        error(R.drawable.profile).size(50) // Error resource if loading fails
                    }),
                    contentDescription = null, //
                    contentScale = ContentScale.FillHeight
                )

                Surface(
                    Modifier
                        .height(37.dp)
                        .width(53.dp)
                        .align(Alignment.BottomCenter),
                    shape = RoundedCornerShape(100.dp),
                    color = Secondary
                ){
                    
                    Box(Modifier.fillMaxSize()) {
                        Icon(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(18.dp)
                                .clickable {
                                    onProfileImageEditClick()
                                },
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = null,
                            tint = Neutral100
                        )
                    }

                }

            }
            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = username,
                style = TextStyle(
                    fontFamily = Lato,
                    color = if(isSystemInDarkTheme()) Neutral100 else Neutral900,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = phoneNumber,
                style = TextStyle(
                    fontFamily = Lato,
                    color = if(isSystemInDarkTheme()) Neutral600 else Neutral400,
                    fontSize = 16.sp
                )
            )

        }

    }
}