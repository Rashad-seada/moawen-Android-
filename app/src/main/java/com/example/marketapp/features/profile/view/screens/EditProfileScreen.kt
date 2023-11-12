package com.example.marketapp.features.profile.view.screens

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.marketapp.features.home.view.components.profile.ProfileHeader
import com.example.marketapp.features.profile.view.components.ProfileItem
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.core.views.components.CustomProgressIndicator
import com.example.marketapp.core.views.components.MainButton
import com.example.marketapp.features.profile.view.components.Header
import com.example.marketapp.features.profile.view.components.PhoneNumberField
import com.example.marketapp.features.profile.view.components.UsernameField
import com.example.marketapp.features.profile.view.viewmodels.edit_profile.EditProfileState
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun EditProfileScreen(
    navigator: DestinationsNavigator?,
    profileState: EditProfileState = EditProfileState(),
    updateUsername : (String)-> Unit = {},
    updatePhone : (String)-> Unit = {},
    updateProfileImage : (Uri?)-> Unit = {},
    onSave : (DestinationsNavigator,Context)-> Unit = {_,_-> },

    ){

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            updateProfileImage(it)
        }
    )

    val context: Context = LocalContext.current

    LaunchedEffect(true ) {
        updateUsername(CoreViewModel.user!!.fullname)
        updatePhone(CoreViewModel.user!!.phone)
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


            Header(
                label = context.getString(R.string.edit_profile),
                onClick = {
                    navigator?.let {
                        navigator.popBackStack()
                    }
                }
            )

            Spacer(modifier = Modifier.height(45.dp))

            Box(
                modifier = Modifier.height(146.dp)

            ) {

                Image(
                    modifier = Modifier
                        .height(135.dp)
                        .width(135.dp)
                        .clip(CircleShape)
                        .background(Neutral100, CircleShape)
                        .border(
                            BorderStroke(1.dp, Color(0xffCDCDCD)),
                            CircleShape
                        ),

                    painter = rememberImagePainter(
                        data = profileState.pickedProfileImage?: profileState.profileImage,
                        builder = {
                        transformations(CircleCropTransformation()) // Apply transformations if needed
                        placeholder(R.drawable.profile).size(50) // Placeholder resource while loading
                        error(R.drawable.profile).size(50) // Error resource if loading fails
                    }),
                    contentDescription = null, //
                    contentScale = ContentScale.FillHeight
                )

                Surface(
                    Modifier
                        .height(37.dp)
                        .width(53.dp)
                        .align(Alignment.BottomCenter),
                    shape = RoundedCornerShape(100.dp),
                    color = Secondary
                ){

                    Box(Modifier.fillMaxSize()) {
                        Icon(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(18.dp)
                                .clickable {

                                    singlePhotoPicker.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )

                                },
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = null,
                            tint = Neutral100
                        )
                    }

                }

            }
            Spacer(modifier = Modifier.height(50.dp))

            UsernameField(
                value = profileState.usernameTextField,
                onValueChange = {
                    updateUsername(it)
                }
            )
            Spacer(modifier = Modifier.height(30.dp))

            PhoneNumberField(
                value = profileState.phoneTextField,
                onValueChange = {
                    updatePhone(it)
                }
            )
            Spacer(modifier = Modifier.height(90.dp))

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .height(48.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        navigator?.let {
                            onSave(navigator, context)
                        }
                    },
                cardColor = Primary,
                borderColor = Color.Transparent
            ) {

                if (profileState.profileIsLoading) {
                    CustomProgressIndicator(
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = context.getString(R.string.save),
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
fun EditProfileScreenPreview(){
    EditProfileScreen(navigator = null)
}