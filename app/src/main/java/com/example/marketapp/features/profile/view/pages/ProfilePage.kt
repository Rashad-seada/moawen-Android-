package com.example.marketapp.features.profile.view.pages

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marketapp.features.home.view.components.profile.ProfileHeader
import com.example.marketapp.features.profile.view.components.ProfileItem
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.destinations.EditProfileScreenDestination
import com.example.marketapp.destinations.LanguageScreenDestination
import com.example.marketapp.destinations.NotificationSettingsScreenDestination
import com.example.marketapp.features.profile.view.viewmodels.edit_profile.EditProfileState

@Composable
fun ProfilePage(
    profileState: EditProfileState = EditProfileState(),

    navigator: DestinationsNavigator?,
){
    val context: Context = LocalContext.current

    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            ProfileHeader(
                onProfileImageEditClick = {  },
                imageUrl = profileState.profileImage,
                username = profileState.username,
                phoneNumber = profileState.phone
            )

            Spacer(modifier = Modifier.height(26.dp))

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                thickness = 1.5.dp,
                color = if(isSystemInDarkTheme()) Neutral700 else Neutral300
            )

            Spacer(modifier = Modifier.height(10.dp))

            ProfileItem(
                icon = R.drawable.profile,
                leadingLabel = context.getString(R.string.edit_profile),
                onClick = {
                    navigator?.let {
                        navigator.navigate(EditProfileScreenDestination)
                    }
                },
            )


            ProfileItem(
                icon = R.drawable.notification,
                leadingLabel = context.getString(R.string.notification),
                onClick = {
                    navigator?.let {
                        navigator.navigate(NotificationSettingsScreenDestination)
                    }
                },
            )



            ProfileItem(
                icon = R.drawable.language,
                leadingLabel = context.getString(R.string.language),
                onClick = {
                    navigator?.let {
                        navigator.navigate(LanguageScreenDestination)
                    }
                },
            )



        }
    }



}



@Preview
@Composable
fun ProfilePagePreview(){
    ProfilePage(navigator = null)
}