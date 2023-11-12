package com.example.marketapp.core.views.sketch

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
import com.example.marketapp.features.profile.view.components.Header
import com.example.marketapp.features.profile.view.components.NotificatonSettingsItem
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SketchHomeScreen(
    navigator: DestinationsNavigator?,
){
    val context: Context = LocalContext.current

    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {

        val spanCount = 2
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth(),
            columns = GridCells.Fixed(spanCount),
            state = rememberLazyGridState(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item (
                span = {
                    GridItemSpan(2)
                }
            ){
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(30.dp))


                    Header(
                        label = "الرئيسيه",
                        onClick = {}
                    )

                    Spacer(modifier = Modifier.height(30.dp))


                    MyHeader()

                    Spacer(modifier = Modifier.height(30.dp))

                    MyAdBanner()

                    Spacer(modifier = Modifier.height(30.dp))

                    MyCategory()

                    Spacer(modifier = Modifier.height(40.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        text = "خصيصا من اجلك",
                        style = TextStyle(
                            fontFamily = Lato,
                            color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                            fontSize = 14.sp,

                            ),
                        textAlign = TextAlign.End
                    )
                }
            }

            items(4){
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .padding(
                            start = if (it % 2 != 0) 0.dp else 30.dp,
                            end = if (it % 2 != 0) 30.dp else 0.dp
                        ),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, Neutral500),
                    color = Neutral900
                ) {

                    Box(modifier = Modifier.fillMaxSize()){

                        Surface(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(30.dp)
                                .fillMaxWidth()
                                .size(40.dp),
                            shape = androidx.compose.foundation.shape.CircleShape,
                            border = BorderStroke(1.dp, Neutral500),
                            color = Neutral900
                        ){}

                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            painter = painterResource(
                                id = R.drawable.plant_item
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )


                    }

                }
            }

        }

    }



}

@Composable
fun MyHeader(){
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        text = "استمتع باحدث العروض والمنتجات الحصريه",
        style = TextStyle(
            fontFamily = Lato,
            color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
            fontSize = 14.sp,

            ),
        textAlign = TextAlign.End
    )

    Spacer(modifier = Modifier.height(24.dp))

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 30.dp),
        shape = RoundedCornerShape(1000.dp),
        border = BorderStroke(1.dp, Neutral500),
        color = Neutral800
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End

        ) {
            Text(
                modifier = Modifier,
                text = "عن ماذا تبحث؟",
                style = TextStyle(
                    fontFamily = Lato,
                    color = if (isSystemInDarkTheme()) Neutral400 else Neutral900,
                    fontSize = 12.sp,

                    ),
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                modifier = Modifier
                    .padding(1.dp),
                painter = painterResource(
                    id = R.drawable.search
                ),
                contentDescription = null,
                tint = Neutral200
            )


        }
    }
}

@Composable
fun MyAdBanner(){


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 30.dp),
        shape = RoundedCornerShape(15.dp),
        //border = BorderStroke(1.dp, Neutral500),
        color = Neutral800
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(
                id = R.drawable.plant_ad
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun MyCategory(){


    LazyHorizontalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(horizontal = 30.dp),
        rows = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(30.dp)
    ){
        items(10){

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )  {
                Surface(
                    modifier = Modifier
                        .size(48.dp),
                    shape = RoundedCornerShape(20.dp),
                    color = Neutral800
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .size(25.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            painter = painterResource(
                                id = R.drawable.plant_category
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )

                    }

                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier,
                    text = "الاعلاف",
                    style = TextStyle(
                        fontFamily = Lato,
                        color = if (isSystemInDarkTheme()) Neutral400 else Neutral900,
                        fontSize = 12.sp,

                        ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun MyItems(){
}


@Preview
@Composable
fun SketchHomeScreenPreview(){
    SketchHomeScreen(navigator = null)
}

class Category(
    val name: String,
    val image : Int
)

val categories = listOf(
    Category(
        name = "الاعلاف",
        image = R.drawable.plant_item,
    ),
    Category(
        name = "الدواجن",
        image = R.drawable.dawajen,
    ),
    Category(
        name = "اللحوم",
        image = R.drawable.lohom,
    ),
    Category(
        name = "الاسمده",
        image = R.drawable.asmeda,
    ),
    Category(
        name = "الادويه",
        image = R.drawable.dawa,
    ),
)