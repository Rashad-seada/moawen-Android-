package com.example.marketapp.features.order.view.screens


import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Lato
import com.example.marketapp.core.ui.theme.MarketAppTheme
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral900
import com.example.marketapp.core.views.components.CustomProgressIndicator
import com.example.marketapp.features.order.infrastructure.services.utils.PlaceInfo
import com.example.marketapp.features.order.view.components.CustomSearchBar
import com.example.marketapp.features.order.view.components.PlaceItem
import com.example.marketapp.features.order.view.utils.Location
import com.example.marketapp.features.order.view.viewmodel.select_location.SelectLocationState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun SearchLocationToScreen(
    navigator: DestinationsNavigator?,
    state: SelectLocationState = SelectLocationState(),
    onQueryChange: (String) -> Unit = {},
    onLocationSelect : (DestinationsNavigator,PlaceInfo) -> Unit = {_,_->  },
    onSearch: (String) -> Unit = {},
) {

    val context: Context = LocalContext.current


    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        it

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            item {
                Spacer(modifier = Modifier.height(20.dp))

            }


            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {


                    CustomSearchBar(
                        modifier = Modifier.width(300.dp),
                        value = state.locationQuery,
                        onValueChange = { onQueryChange(it) },

                        )

                    Icon(
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                navigator?.let {
                                    navigator.popBackStack()
                                }
                            },
                        painter = painterResource(
                            id = R.drawable.cancel
                        ),
                        contentDescription = null,
                        tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
                    )


                }
            }



            item {
                Spacer(modifier = Modifier.height(20.dp))

            }

            item {
                if (state.isSearchingPlaces) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CustomProgressIndicator(
                            modifier = Modifier.size(20.dp).padding(bottom = 20.dp)
                        )
                    }

                }
            }



            item {

                if (state.placesInfo.isEmpty() && !state.isSearchingPlaces) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            modifier = Modifier
                                .size(50.dp),
                            painter = painterResource(
                                id = R.drawable.search2
                            ),
                            contentDescription = null,
                            tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .padding(start = 0.dp),
                            text = context.getString(R.string.empty_search),
                            style = TextStyle(
                                fontFamily = Lato,
                                color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                                fontSize = 18.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                    }
                }


            }

            items(state.placesInfo.size) {index ->
                PlaceItem(state.placesInfo[index]){
                    if (navigator != null) {
                        onLocationSelect(navigator,state.placesInfo[index])
                    }
                }

            }


        }


    }

}

@Preview
@Composable
fun SearchLocationToScreenPreview() {
    MarketAppTheme {
        SearchLocationToScreen(
            navigator = null
        )
    }
}
