package com.example.marketapp.features.wallet.view.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Lato
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.core.views.components.shimmerEffect

@Composable
fun WalletItem(
    isLoading : Boolean = false,
    walletAmount: Double = 0.0,
    onClick: () -> Unit = {},
) {
    val context: Context = LocalContext.current

    val gradient = Brush.linearGradient(
        listOf(
            Color(0xff89bff9),
            Color(0xff2c89f3)
        ),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .height(180.dp)
            .background(
                shape = RoundedCornerShape(12.dp),
                brush = gradient
            ),

        ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = context.getString(R.string.current_balance),
                style = TextStyle(
                    fontFamily = Lato,
                    color = Neutral100,
                    fontSize = 12.sp
                )
            )

            Spacer(modifier = Modifier.height(13.dp))


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
            }else {
                Text(
                    text = walletAmount.toString(),
                    style = TextStyle(
                        fontFamily = Lato,
                        color = Neutral100,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }



            Spacer(modifier = Modifier.height(33.dp))


            Surface(
                modifier = Modifier
                    .width(187.dp)
                    .height(33.dp)
                    .clickable {
                        onClick()
                    },
                shape = RoundedCornerShape(1000.dp),
                color = Neutral100
            ) {

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = context.getString(R.string.charge_balance),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = Neutral900,
                            fontSize = 12.sp,
                        )
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Icon(
                        modifier = Modifier
                            .width(24.dp)
                            .height(18.dp),
                        painter = painterResource(
                            id = R.drawable.charge_balacne
                        ),
                        contentDescription = null,
                        tint = Neutral900
                    )


                }


            }


        }

    }

}