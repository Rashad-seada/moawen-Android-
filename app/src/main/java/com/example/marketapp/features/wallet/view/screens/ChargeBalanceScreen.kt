package com.example.marketapp.features.wallet.view.pages

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.core.views.components.CustomProgressIndicator
import com.example.marketapp.core.views.components.MainButton
import com.example.marketapp.features.profile.view.components.Header
import com.example.marketapp.features.profile.view.components.NotificatonSettingsItem
import com.example.marketapp.features.wallet.view.components.ChargeBalanceItem
import com.example.marketapp.features.wallet.view.viewmodel.wallet.WalletState
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ChargeBalanceScreen(
    navigator: DestinationsNavigator?,
    walletState: WalletState = WalletState(),
    onImageSelection : (Uri?) -> Unit = {},
    onUploadImage : (DestinationsNavigator,Context) -> Unit = {_,_-> },

    ){
    val context: Context = LocalContext.current

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            onImageSelection(it)
        }
    )

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


            Header(
                label = context.getString(R.string.charge_balance),
                onClick = {navigator?.popBackStack()}
            )

            Spacer(modifier = Modifier.height(45.dp))

            ChargeBalanceItem(
                selectedReceipt = walletState.moneyTransferPhoto,
                onClick = {
                    singlePhotoPicker.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            )

            if(walletState.chargeBalanceError != null) {
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    text = walletState.chargeBalanceError!!,
                    style = TextStyle(
                        fontFamily = Lato,
                        color = Error400Clr,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start
                    )
                )
            }


            Spacer(modifier = Modifier.height(90.dp))

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .height(48.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        navigator?.let {
                            onUploadImage(navigator, context)
                        }
                    },
                cardColor = Primary,
                borderColor = Color.Transparent
            ) {

                if (walletState.chargeBalanceIsLoading) {
                    CustomProgressIndicator(
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = context.getString(R.string.upload),
                    style = TextStyle(
                        fontFamily = Lato,
                        color = Neutral100,
                        fontSize = 16.sp,
                    )
                )
                }

            }


        }
    }



}



@Preview
@Composable
fun ChargeBalanceScreenPreview(){
    ChargeBalanceScreen(navigator = null)
}