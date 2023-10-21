package com.example.marketapp.features.e_learning.view.components.home

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.core.ui.theme.Cairo
import com.example.marketapp.core.ui.theme.MarketAppTheme
import com.example.marketapp.core.ui.theme.Neutral300
import com.example.marketapp.core.ui.theme.Neutral500
import com.example.marketapp.core.ui.theme.Neutral700
import com.example.marketapp.features.e_learning.view.utils.HomeFeatures


@Composable
fun HomeFeatureCard(
    feature: HomeFeatures
) {

    val context: Context = LocalContext.current

    Card(
        modifier = Modifier.height(210.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if(isSystemInDarkTheme()) Color.Transparent else Color.Transparent
        ),
        border = BorderStroke(1.dp, if(isSystemInDarkTheme()) Neutral700 else Neutral300),

    ) {

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp).padding(top = 20.dp),
            horizontalAlignment = Alignment.Start,
        ) {

//            Surface (
//                modifier = Modifier
//                    .size(48.dp),
//                shape = CircleShape,
//                border = BorderStroke(1.dp, if(isSystemInDarkTheme()) Neutral700 else Neutral300),
//                color = if(isSystemInDarkTheme()) Color.Transparent else Color.Transparent
//            ) {
//
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Image(
//                        modifier = Modifier.size(48.dp),
//                        painter = painterResource(id = feature.icon),
//                        contentDescription = ""
//                    )
//                }
//
//            }
            Image(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = feature.icon),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = context.getString(feature.label),
                color = if(isSystemInDarkTheme()) Neutral300 else Neutral700,
                fontFamily = Cairo,
                fontSize = 26.sp
            )


            Text(
                text = context.getString(feature.subText),
                color = if(isSystemInDarkTheme()) Neutral500 else Neutral500,
                fontFamily = Cairo,
                fontSize = 12.sp
            )

        }


    }

}

@Preview(showBackground = true)
@Composable
fun HomeFeatureCardPreview() {
    MarketAppTheme {
        HomeFeatureCard(HomeFeatures.Study)
    }
}