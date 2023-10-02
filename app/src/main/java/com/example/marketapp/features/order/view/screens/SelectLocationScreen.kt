package com.example.marketapp.features.order.view.screens


import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.core.views.components.CustomProgressIndicator
import com.example.marketapp.core.views.components.MainButton
import com.example.marketapp.features.order.view.utils.calculateDistanceInKilometers
import com.example.marketapp.features.order.view.viewmodel.select_location.SelectLocationState
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Destination
@Composable
fun SelectLocationScreen(
    navigator: DestinationsNavigator?,
    state: SelectLocationState = SelectLocationState(),

    onFromLocationClick: (DestinationsNavigator,) -> Unit = {  },
    onToLocationClick: (DestinationsNavigator,) -> Unit = {  },

    onNextClick: (DestinationsNavigator,Context) -> Unit = { _ , _ -> },

    ) {

    val singapore = LatLng(  30.0444,   31.2357)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }

    val context: Context = LocalContext.current
    val sheetState = rememberModalBottomSheetState()


    LaunchedEffect(state.fromPlaceInfo){
        if(state.fromPlaceInfo != null && state.fromPlaceInfo!!.latLng != null){
            cameraPositionState.animate(
                update = CameraUpdateFactory.newCameraPosition(
                    CameraPosition(state.fromPlaceInfo!!.latLng!!, 10f, 0f, 0f)
                ),
                durationMs = 1000
            )
        }

    }

    LaunchedEffect(state.toPlaceInfo){
        if(state.toPlaceInfo != null && state.toPlaceInfo!!.latLng != null){
            cameraPositionState.animate(
                update = CameraUpdateFactory.newCameraPosition(
                    CameraPosition(state.toPlaceInfo!!.latLng!!, 10f, 0f, 0f)
                ),
                durationMs = 1000
            )
        }

    }


    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        it

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {

                if(state.fromPlaceInfo != null && state.fromPlaceInfo!!.latLng != null){
                    Marker(
                        state = MarkerState(
                            position = state.fromPlaceInfo!!.latLng!!
                        ),
                        title = state.fromPlaceInfo!!.name,
                        snippet = state.fromPlaceInfo!!.address
                    )
                }

                if(state.toPlaceInfo != null && state.toPlaceInfo!!.latLng != null){
                    Marker(
                        state = MarkerState(
                            position = state.toPlaceInfo!!.latLng!!
                        ),
                        title = state.toPlaceInfo!!.name,
                        snippet = state.toPlaceInfo!!.address
                    )
                }

                if(state.toPlaceInfo != null &&
                    state.toPlaceInfo!!.latLng != null &&
                    state.fromPlaceInfo != null &&
                    state.fromPlaceInfo!!.latLng != null
                ){
                    Polygon(points = listOf(
                        state.fromPlaceInfo!!.latLng!!,
                        state.toPlaceInfo!!.latLng!!
                    ),
                    fillColor = Error500Clr)
                }

            }

            Surface(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .shadow(
                        elevation = 10.dp,
                        shape = RectangleShape,
                        ambientColor = DefaultShadowColor,
                        spotColor = DefaultShadowColor,
                    ),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                color = if (isSystemInDarkTheme()) Neutral900 else Neutral100
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(35.dp))

                    if(state.toPlaceInfo != null &&
                        state.toPlaceInfo!!.latLng != null &&
                        state.fromPlaceInfo != null &&
                        state.fromPlaceInfo!!.latLng != null
                    ){

                        Row(
                            modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ){
                                Icon(
                                    modifier = Modifier
                                        .padding(13.dp),
                                    painter = painterResource(
                                        id = R.drawable.destance
                                    ),
                                    contentDescription = null,
                                    tint = Secondary
                                )

                                Text(
                                    modifier = Modifier,
                                    text = context.getString(R.string.destance),
                                    style = TextStyle(
                                        fontFamily = Lato,
                                        color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                                        fontSize = 14.sp
                                    )
                                )


                            }


                            Text(
                                text = String.format("%.2f", calculateDistanceInKilometers(state.fromPlaceInfo!!.latLng!!,state.toPlaceInfo!!.latLng!!)).toString() +" "+context.getString(R.string.km),
                                style = TextStyle(
                                    fontFamily = Lato,
                                    color = Secondary,
                                    fontSize = 14.sp
                                )
                            )
                        }

                    }else {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .padding(start = 0.dp),
                            text = context.getString(R.string.select_location),
                            style = TextStyle(
                                fontFamily = Lato,
                                color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                                fontSize = 16.sp
                            )
                        )
                    }


                    Spacer(modifier = Modifier.height(30.dp))

                    Surface(
                        modifier = Modifier
                            .height(50.dp)
                            .width(330.dp)
                            .clickable {
                                navigator?.let {
                                    onToLocationClick(navigator)
                                }
                            },
                        shape = RoundedCornerShape(100.dp),
                        color = if (isSystemInDarkTheme()) Neutral800 else Neutral200
                    ){
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp)
                                    .padding(start = 0.dp),
                                text = if(state.fromPlaceInfo != null && state.fromPlaceInfo!!.latLng != null)
                                    state.fromPlaceInfo!!.name else context.getString(R.string.from),
                                style = TextStyle(
                                    fontFamily = Lato,
                                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                                    fontSize = 14.sp
                                )
                            )

                            Icon(
                                modifier = Modifier
                                    .padding(13.dp),
                                painter = painterResource(
                                    id = R.drawable.from
                                ),
                                contentDescription = null,
                                tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))

                    Surface(
                        modifier = Modifier
                            .height(50.dp)
                            .width(330.dp)
                            .clickable {
                                navigator?.let {
                                    onFromLocationClick(navigator)
                                }
                            },
                        shape = RoundedCornerShape(100.dp),
                        color = if (isSystemInDarkTheme()) Neutral800 else Neutral200
                    ){
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp)
                                    .padding(start = 0.dp),
                                text = if(state.toPlaceInfo != null && state.toPlaceInfo!!.latLng != null)
                                    state.toPlaceInfo!!.name else context.getString(R.string.to),
                                style = TextStyle(
                                    fontFamily = Lato,
                                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                                    fontSize = 14.sp
                                )
                            )

                            Icon(
                                modifier = Modifier
                                    .padding(13.dp),
                                painter = painterResource(
                                    id = R.drawable.to
                                ),
                                contentDescription = null,
                                tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
                            )

                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))
                    MainButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .height(55.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .clickable {
                                navigator?.let {
                                    onNextClick(navigator, context)
                                }

                            },
                        cardColor = Primary,
                        borderColor = Color.Transparent
                    ) {

                        if (!state.isSelectingPlace) {
                            Text(
                                modifier = Modifier.padding(horizontal = 20.dp),
                                text = context.getString(R.string.next),
                                style = TextStyle(
                                    fontFamily = Lato,
                                    color = Neutral100,
                                    fontSize = 16.sp,
                                )
                            )
                        } else {
                            CustomProgressIndicator(
                                modifier = Modifier.size(20.dp)
                            )
                        }


                    }
                    Spacer(modifier = Modifier.height(50.dp))

                }



            }

            Surface(
                modifier = Modifier
                    .padding(20.dp)
                    .size(50.dp)
                    .clickable {
                        navigator?.let {
                            navigator.popBackStack()
                        }
                    },
                shape = CircleShape,
                color = if (isSystemInDarkTheme()) Neutral900 else Neutral100
            ){
                Icon(
                    modifier = Modifier
                        .padding(13.dp)
                        .align(Alignment.Center),
                    painter = painterResource(
                        id = R.drawable.arrow_left
                    ),
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
                )
            }


        }


    }

}

@Preview
@Composable
fun SelectLocationScreenScreenPreview() {
    MarketAppTheme {
        SelectLocationScreen(
            navigator = null
        )
    }
}
