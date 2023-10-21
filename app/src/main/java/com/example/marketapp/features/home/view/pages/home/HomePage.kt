package com.example.marketapp.features.home.view.pages.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.core.views.components.CustomProgressIndicator
import com.example.marketapp.core.views.components.MainButton
import com.example.marketapp.features.home.view.viewmodels.home.HomeState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@SuppressLint("UnrememberedMutableState")
@Destination
@Composable
fun HomePage(
    navigator: DestinationsNavigator?,
    state : HomeState = HomeState(),

    init : (Context) -> Unit = {},
    onContactAssistantClick : (DestinationsNavigator,Context) -> Unit = {_,_-> },
    onSendOrderClick : (DestinationsNavigator,Context) -> Unit = {_,_-> },

    ) {

    val context: Context = LocalContext.current

    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {

        LaunchedEffect(true ){
            init(context)
        }

        it
        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Surface(
                Modifier
                    .fillMaxWidth()
                    .height(180.dp),
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

                    if(state.isHomePageLoading){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){
                            CustomProgressIndicator()
                        }
                    }else if(state.error != null){

                    }else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {



                            Image(
                                modifier = Modifier
                                    .height(48.dp)
                                    .width(48.dp)
                                    .clip(CircleShape),
                                painter = rememberImagePainter(data = state.profileImage?: "", builder = {
                                    transformations(CircleCropTransformation()) // Apply transformations if needed
                                    placeholder(R.drawable.photo) // Placeholder resource while loading
                                    error(R.drawable.photo_error) // Error resource if loading fails
                                }),
                                contentDescription = null, //
                                contentScale = ContentScale.FillHeight
                            )

                            Spacer(modifier = Modifier.width(20.dp))

                            Text(
                                text = context.getString(R.string.welcome)+" ",
                                style = TextStyle(
                                    fontFamily = Lato,
                                    color = Neutral100,
                                    fontSize = 18.sp
                                )
                            )

                            Text(
                                text = state.username?: "",
                                style = TextStyle(
                                    fontFamily = Lato,
                                    color = Neutral100,
                                    fontSize = 18.sp
                                )
                            )

                        }
                    }



                    Spacer(modifier = Modifier.height(30.dp))


                    if(state.isHomePageLoading){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){
                            CustomProgressIndicator()
                        }
                    }else if(state.error != null){

                    }else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Text(
                                text = state.wallet.toString(),
                                style = TextStyle(
                                    fontFamily = Lato,
                                    color = Secondary,
                                    fontSize = 32.sp
                                )
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = state.currency?: "",
                                style = TextStyle(
                                    fontFamily = Lato,
                                    color = Neutral100,
                                    fontSize = 18.sp
                                )
                            )

                        }
                    }


                    Spacer(modifier = Modifier.height(5.dp))

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

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = context.getString(R.string.today_assistant_is),
                style = TextStyle(
                    fontFamily = Lato,
                    color = if(isSystemInDarkTheme()) Neutral100 else Neutral900,
                    fontSize = 16.sp
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            if(state.isHomePageLoading){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    CustomProgressIndicator()
                }
            }else if(state.error != null){

            }else {
                Text(
                    text = state.assistantName?: "",
                    style = TextStyle(
                        fontFamily = Lato,
                        color = if(isSystemInDarkTheme()) Neutral100 else Primary,
                        fontSize = 32.sp
                    )
                )
            }



            Spacer(modifier = Modifier.height(30.dp))



            if(state.isHomePageLoading){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    CustomProgressIndicator()
                }
            }else if(state.error != null){

            }else {
                Image(
                    modifier = Modifier
                        .height(240.dp)
                        .width(240.dp)
                        .clip(CircleShape)
                        .border(
                            BorderStroke(5.dp,Color(0xffCDCDCD)),
                            CircleShape
                        ),
                    painter = rememberImagePainter(data = state.assistantImage?: "", builder = {
                        transformations(CircleCropTransformation()) // Apply transformations if needed
                        placeholder(R.drawable.photo) // Placeholder resource while loading
                        error(R.drawable.photo_error) // Error resource if loading fails
                    }),
                    contentDescription = null, //
                    contentScale = ContentScale.FillHeight
                )
            }


            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = context.getString(R.string.start_your_order_now),
                style = TextStyle(
                    fontFamily = Lato,
                    color = if(isSystemInDarkTheme()) Neutral100 else Neutral900,
                    fontSize = 16.sp
                )
            )

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                MainButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(55.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .clickable {
                            navigator?.let {
                                onSendOrderClick(navigator, context)
                            }
                        },
                    cardColor = Primary,
                    borderColor = Color.Transparent
                ) {


                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            text = context.getString(R.string.make_order),
                            style = TextStyle(
                                fontFamily = Lato,
                                color = Neutral100 ,
                                fontSize = 16.sp
                            )
                        )

                }


            }


        }



    }

}

@Preview
@Composable
fun HomePagePreview() {
    MarketAppTheme {
        HomePage(
            navigator = null
        )
    }
}
