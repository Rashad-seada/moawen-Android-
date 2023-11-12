package com.example.marketapp.features.order.view.components

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.features.order.data.entities.orders.Order


@Composable
fun OrderDetailsItem(
    order: Order
) {
    val context: Context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 8.dp)
            .wrapContentHeight()
            .shadow(
                elevation = if (isSystemInDarkTheme()) 10.dp else 10.dp,
                shape = RoundedCornerShape(24.dp),
                clip = false,
                ambientColor = DefaultShadowColor,
                spotColor = DefaultShadowColor,
            ),
        shape = RoundedCornerShape(24.dp),
        color = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp)
        ) {

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    modifier = Modifier
                        .height(48.dp)
                        .width(48.dp)
                        .clip(CircleShape)
                        .background(Neutral100, CircleShape)
                        .border(
                            BorderStroke(1.dp, Color(0xffCDCDCD)),
                            CircleShape
                        ),

                    painter = rememberImagePainter(data = order.driver_image, builder = {
                        transformations(CircleCropTransformation()) // Apply transformations if needed
                        placeholder(R.drawable.profile).size(50) // Placeholder resource while loading
                        error(R.drawable.profile).size(50) // Error resource if loading fails
                    }),
                    contentDescription = null, //
                    contentScale = ContentScale.FillHeight
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = order.drivaer_name,
                    style = TextStyle(
                        fontFamily = Lato,
                        color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                        fontSize = 16.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp),
                thickness = 1.dp,
                color = if (isSystemInDarkTheme()) Neutral700 else Neutral300
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.5f)
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp),

                        painter = painterResource(id = R.drawable.order_number),
                        contentDescription = null, //
                        tint = if (isSystemInDarkTheme()) Neutral600 else Neutral400,

                    )

                    Spacer(modifier = Modifier.width(7.dp))

                    Text(
                        text = order.order_number,
                        style = TextStyle(
                            fontFamily = Lato,
                            color = if (isSystemInDarkTheme()) Neutral200 else Neutral800,
                            fontSize = 14.sp
                        )
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.5f)
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp),

                        painter = painterResource(id = R.drawable.money),
                        contentDescription = null, //
                        tint = if (isSystemInDarkTheme()) Neutral600 else Neutral400,
                    )

                    Spacer(modifier = Modifier.width(7.dp))

                    Text(
                        text = (order.total?: "-- , --").toString(),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = if (isSystemInDarkTheme()) Neutral200 else Neutral800,
                            fontSize = 14.sp
                        )
                    )
                }


            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.5f)
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = context.getString(R.string.time),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = if (isSystemInDarkTheme()) Neutral600 else Neutral400,
                            fontSize = 14.sp
                        )
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = order.the_time_the_delivery_confirm_order?: "-- : --",
                        style = TextStyle(
                            fontFamily = Lato,
                            color = if (isSystemInDarkTheme()) Neutral200 else Neutral800,
                            fontSize = 14.sp
                        )
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.5f)
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = context.getString(R.string.date),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = if (isSystemInDarkTheme()) Neutral600 else Neutral400,
                            fontSize = 14.sp
                        )
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = order.created_at.replace('-','/'),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = if (isSystemInDarkTheme()) Neutral200 else Neutral800,
                            fontSize = 14.sp,
                        ),
                        maxLines = 2,
                        textAlign = TextAlign.Start

                    )
                }




            }

            Spacer(modifier = Modifier.height(15.dp))

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp),
                thickness = 1.dp,
                color = if (isSystemInDarkTheme()) Neutral700 else Neutral300
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Surface(
                    modifier = Modifier
                        .width(120.dp)
                        .height(32.dp),
                    shape = RoundedCornerShape(1000.dp),
                    color = Primary
                ) {

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = context.getString(R.string.view_order_details),
                            style = TextStyle(
                                fontFamily = Lato,
                                color = Neutral100,
                                fontSize = 10.sp,
                            )
                        )

                    }


                }
            }
            Spacer(modifier = Modifier.height(20.dp))


        }

    }

}