package com.example.marketapp.features.order.view.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.core.views.components.MainButton
import com.example.marketapp.core.views.components.shimmerEffect
import com.example.marketapp.features.order.data.entities.orders.DefaultCurrency
import com.example.marketapp.features.order.data.entities.orders.Order
import com.example.marketapp.features.order.data.entities.orders.Translation
import com.example.marketapp.features.order.view.components.OrderDetailsItem
import com.example.marketapp.features.order.view.viewmodel.order.OrderState
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun OrdersDetailsPage(
    navigator: DestinationsNavigator?,
    getOrders : ()-> Unit = {},
    orderState: OrderState = OrderState()
){
    val context: Context = LocalContext.current

    LaunchedEffect(key1 = true){
        getOrders()
    }

    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(30.dp))

            }


            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                ) {

                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = context.getString(R.string.orders),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                            fontSize = 18.sp
                        )
                    )
                }
            }


            item {
                Spacer(modifier = Modifier.height(25.dp))

            }

            if(orderState.isOrdersLoading){

                items(3) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp)
                            .padding(horizontal = 30.dp, vertical = 8.dp)
                            .shadow(
                                elevation = if (isSystemInDarkTheme()) 10.dp else 10.dp,
                                shape = RoundedCornerShape(24.dp),
                                clip = false,
                                ambientColor = DefaultShadowColor,
                                spotColor = DefaultShadowColor,
                            )
                            .clip(RoundedCornerShape(24.dp))
                            .shimmerEffect(),
                    ) {

                    }
                }

            }else{
                when {
                    orderState.ordersError != null -> {

                        item {

                            Spacer(modifier = Modifier.height(140.dp))

                            Icon(
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(150.dp),
                                painter = painterResource(id = R.drawable.warning),
                                contentDescription = null,
                                tint = if(isSystemInDarkTheme()) Neutral300 else Neutral700
                            )

                            Spacer(modifier = Modifier.height(30.dp))

                            Text(
                                modifier = Modifier.padding(horizontal = 50.dp),
                                text = orderState.ordersError!!.toString(),
                                style = TextStyle(
                                    fontFamily = Lato,
                                    color = if (isSystemInDarkTheme()) Neutral200 else Neutral800,
                                    fontSize = 18.sp,
                                ),
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(40.dp))

                            MainButton(
                                modifier = Modifier
                                    .width(136.dp)
                                    .height(48.dp)
                                    .clip(RoundedCornerShape(100.dp))
                                    .clickable {
                                        getOrders()
                                    },
                                cardColor = Primary,
                                borderColor = Color.Transparent
                            ) {


                                Text(
                                    text = context.getString(R.string.try_again),
                                    style = TextStyle(
                                        fontFamily = Lato,
                                        color = Neutral100,
                                        fontSize = 16.sp
                                    )
                                )

                            }



                        }

                    }
                    orderState.orders.isEmpty() -> {

                        item {

                            Spacer(modifier = Modifier.height(110.dp))

                            Image(
                                modifier = Modifier
                                    .width(258.32.dp)
                                    .height(245.73.dp),
                                painter = painterResource(id = R.drawable.empty_orders),
                                contentDescription = null,
                            )

                            Spacer(modifier = Modifier.height(40.dp))

                            Text(
                                text = context.getString(R.string.no_orders),
                                style = TextStyle(
                                    fontFamily = Lato,
                                    color = if (isSystemInDarkTheme()) Neutral200 else Neutral800,
                                    fontSize = 18.sp
                                )
                            )



                        }

                    }
                    else -> {
                        items(orderState.orders){
                            OrderDetailsItem(it)
                        }
                    }

                }

            }





            item {
                Spacer(modifier = Modifier.height(35.dp))

            }

        }
    }



}



@Preview
@Composable
fun OrdersDetailsScreenPreview(){
    OrdersDetailsPage(navigator = null)
}