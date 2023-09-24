package com.example.marketapp.core.views.pages

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Cairo
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral500
import com.example.marketapp.core.ui.theme.Neutral900
import kotlinx.coroutines.delay

@Composable
fun OnBoardingPage(
    label: String = "",
    subText: String = "",
    image: Int = 0,
) {
    var visible1 by remember { mutableStateOf(false) }
    var visible2 by remember { mutableStateOf(false) }

    val density = LocalDensity.current

    LaunchedEffect(key1 =  true){
        delay(100)
        visible1 = true
        delay(100)
        visible2 = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp),

        ) {

        Spacer(modifier = Modifier.height(70.dp))


        Image(
            painter = painterResource(id = image), // Provide the resource ID
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth() // Adjust the size as needed
                .height(300.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))



        AnimatedVisibility(
            visible = visible1,
            enter = slideInHorizontally {
                // Slide in from 40 dp from the top.
                with(density) { 20.dp.roundToPx() }
            } + fadeIn(
                // Fade in with the initial alpha of 0.3f.
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            Text(
                text = label,
                style = TextStyle(
                    fontFamily = Cairo,
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                    fontSize = 32.sp
                )
            )
        }


        Spacer(modifier = Modifier.height(5.dp))

        AnimatedVisibility(
            visible = visible2,
            enter = slideInHorizontally {
                // Slide in from 40 dp from the top.
                with(density) { 40.dp.roundToPx() }
            } + fadeIn(
                // Fade in with the initial alpha of 0.3f.
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            Text(
                text = subText,
                style = TextStyle(
                    fontFamily = Cairo,
                    color = Neutral500,
                    fontSize = 16.sp,

                    )
            )
        }



        Spacer(modifier = Modifier.height(20.dp))


    }
}


@Preview
@Composable
fun OnBoardingPagePreview(
    context: Context = LocalContext.current,
) {
    OnBoardingPage(
        image = R.drawable.pin,
        label = context.getString(R.string.on_boarding_title_3),
        subText = context.getString(R.string.on_boarding_sub_text_3),
    )
}