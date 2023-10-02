package com.example.marketapp.core.views.screens

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.core.views.components.CustomPageIndicator
import com.example.marketapp.core.views.components.MainButton
import com.example.marketapp.core.views.pages.OnBoardingPage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Destination
@Composable
fun OnBoardingScreen(
    onSkipClick : (DestinationsNavigator) -> Unit = {},
    onNextClick : (DestinationsNavigator) -> Unit = {},
    navigator: DestinationsNavigator?
    ) {
    val context: Context = LocalContext.current

    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = if( isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        it
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
            ,
        ) {


            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),

                ) { page ->
                when (page) {
                    0 -> OnBoardingPage(
                        image = R.drawable.on_boarding1,
                        label = context.getString(R.string.on_boarding_title_1),
                        subText = context.getString(R.string.on_boarding_sub_text_1),
                    )

                    1 -> OnBoardingPage(
                        image = R.drawable.on_boarding2,
                        label = context.getString(R.string.on_boarding_title_2),
                        subText = context.getString(R.string.on_boarding_sub_text_2),
                    )

                    2 -> OnBoardingPage(
                        image = R.drawable.on_boarding3,
                        label = context.getString(R.string.on_boarding_title_3),
                        subText = context.getString(R.string.on_boarding_sub_text_3),
                    )

                    else -> throw IllegalStateException("image not provided for page $page")
                }
            }

            CustomPageIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 320.dp),
                totalPages = pagerState.pageCount,
                currentPage = pagerState.currentPage,
                indicatorSize = 7.dp,
                color = Secondary,
            )


            MainButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 100.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(55.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {

                        if(pagerState.currentPage != 2){
                            scope.launch {

                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }else {
                            navigator?.let {
                                onNextClick(navigator)
                            }
                        }



                    }
                    ,
                cardColor = Primary,
                borderColor = Color.Transparent
            ){
                Row (
                    verticalAlignment =  Alignment.CenterVertically,
                ){


                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = context.getString(R.string.next),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = Neutral100 ,
                            fontSize = 16.sp
                        )
                    )
                }
            }


            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 45.dp)
                    .wrapContentHeight()
                    .clickable {

                        navigator?.let {
                            onSkipClick(navigator)
                        }
                    },
                text = context.getString(R.string.skip),
                style = TextStyle(
                    fontFamily = Lato,
                    color = Neutral500,
                    fontSize = 18.sp,

                    )
            )
        }


    }

}

@Preview
@Composable
fun OnBoardingScreenPreview() {
    MarketAppTheme {
        OnBoardingScreen(
            navigator = null
        )

    }
}