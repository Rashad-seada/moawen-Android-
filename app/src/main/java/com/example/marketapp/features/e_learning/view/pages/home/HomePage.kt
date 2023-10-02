package com.example.marketapp.features.e_learning.view.pages.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Cairo
import com.example.marketapp.core.ui.theme.MarketAppTheme
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.core.ui.theme.Primary900
import com.example.marketapp.core.views.utils.gridItems
import com.example.marketapp.features.e_learning.view.components.home.HomeFeatureCard
import com.example.marketapp.features.e_learning.view.viewmodels.home.HomeState


@SuppressLint("UnrememberedMutableState")
@Composable
fun HomePage(
    state : HomeState = HomeState()
) {

    val context: Context = LocalContext.current
    val lazyGridState = rememberLazyGridState()

    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        it
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()

        ) {
            item{
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Primary900,
                                    Color.Transparent
                                )
                            )
                        )
//                        .shadow(
//                            elevation = 20.dp,
//                            spotColor = Primary900.also { Color.Black }
//                        )
                        ,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.height(30.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(start = 20.dp)
                                .clickable {

                                }
                                .size(30.dp),
                            painter = painterResource(
                                id = R.drawable.menu
                            ),
                            contentDescription = null,
                            tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
                        )

                        Surface (
                            modifier = Modifier
                                .padding(end = 20.dp)
                                .size(48.dp)
                            ,
                            shape = CircleShape,
                            border = BorderStroke(1.dp, if (isSystemInDarkTheme()) Neutral100 else Neutral900),
                            color = if(isSystemInDarkTheme()) Color.Transparent else Color.Transparent
                        ) {

                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier.size(25.dp),
                                    painter = painterResource(id = R.drawable.notification),
                                    contentDescription = "",
                                    tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
                                )
                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.width(250.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(start = 20.dp),
                                text = context.getString(R.string.welcome),
                                style = TextStyle(
                                    fontFamily = Cairo,
                                    color = if(isSystemInDarkTheme()) Neutral100 else Neutral900,
                                    fontSize = 32.sp
                                )
                            )

                            Text(
                                modifier = Modifier
                                    .padding(start = 20.dp),
                                text = context.getString(R.string.home_sub_text),
                                style = TextStyle(
                                    fontFamily = Cairo,
                                    color = if(isSystemInDarkTheme()) Neutral100 else Neutral900,
                                    fontSize = 16.sp,

                                    )
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))

                        Image(
                            modifier = Modifier
                                .padding(end = 20.dp)
                                .fillMaxWidth()
                                .height(100.dp)
                                .width(200.dp),

                            painter = painterResource(id = R.drawable.welcome3),
                            contentDescription = null
                        )
                    }






                    Spacer(modifier = Modifier.height(30.dp))


                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))

            }

            gridItems(
                data = state.features,
                columnCount = 2,
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalSpacing = 15.dp,
                horizontalSpacing = 15.dp

            ){
                HomeFeatureCard(it)
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))

            }


        }


    }

}

@Preview
@Composable
fun HomePagePreview() {
    MarketAppTheme {
        HomePage()
    }
}
