package com.example.marketapp.features.wallet.view.pages

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketapp.features.home.view.components.profile.ProfileHeader
import com.example.marketapp.features.profile.view.components.ProfileItem
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.core.views.components.MainButton
import com.example.marketapp.destinations.ChargeBalanceScreenDestination
import com.example.marketapp.features.profile.view.components.Header
import com.example.marketapp.features.profile.view.components.LanguageItem
import com.example.marketapp.features.wallet.view.components.WalletItem
import com.example.marketapp.features.wallet.view.viewmodel.wallet.WalletState
import com.ramcosta.composedestinations.annotation.Destination
import org.intellij.lang.annotations.Language

@Composable
fun WalletPage(
    navigator: DestinationsNavigator?,
    state : WalletState = WalletState(),
    getBalacne : (Context)-> Unit = {}
){
    val context: Context = LocalContext.current

    LaunchedEffect(true ){
        getBalacne(context)
    }

    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState())
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Spacer(modifier = Modifier.height(30.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {

                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = context.getString(R.string.wallet),
                    style = TextStyle(
                        fontFamily = Lato,
                        color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                        fontSize = 18.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(35.dp))


            if(state.balanceError != null){

                Spacer(modifier = Modifier.height(185.dp))

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
                    text = state.balanceError!!.toString(),
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
                            getBalacne(context)
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



            }else {
                WalletItem(
                    isLoading = state.balanceIsLoading,
                    walletAmount = state.balance,
                    onClick = {
                        navigator?.navigate(ChargeBalanceScreenDestination)
                    }
                )
            }



        }
    }



}



@Preview
@Composable
fun WalletPagePreview(){
    WalletPage(navigator = null)
}