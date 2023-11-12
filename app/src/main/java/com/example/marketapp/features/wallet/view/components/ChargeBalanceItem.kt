package com.example.marketapp.features.wallet.view.components

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.Lato
import com.example.marketapp.core.ui.theme.Neutral100
import com.example.marketapp.core.ui.theme.Neutral900

@Composable
fun ChargeBalanceItem(
    selectedReceipt : Uri? = null,
    onClick : ()-> Unit = {}
) {

    val context: Context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .height(240.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(12.dp),
        color = if (isSystemInDarkTheme()) Neutral900 else Neutral100,
        border = BorderStroke(1.dp, if (isSystemInDarkTheme()) Neutral100 else Neutral900)

    ) {
        if(selectedReceipt != null){
            Image(
                modifier = Modifier
                    .fillMaxSize(),

                painter = rememberImagePainter(
                    data = selectedReceipt,
                    builder = {
                    placeholder(R.drawable.profile).size(50) // Placeholder resource while loading
                    error(R.drawable.profile).size(50) // Error resource if loading fails
                }),
                contentDescription = null, //
                contentScale = ContentScale.FillHeight
            )
        }else{
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    modifier = Modifier
                        .size(48.dp),
                    painter = painterResource(
                        id = R.drawable.camera
                    ),
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
                )
                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 40.dp),
                    text = context.getString(R.string.upload_receipt),
                    style = TextStyle(
                        fontFamily = Lato,
                        color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                )

            }
        }

    }

}