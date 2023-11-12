package com.example.marketapp.features.profile.view.viewmodels.edit_profile

import android.content.Context
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class EditProfileEvent {

    class OnSave(val navigator: DestinationsNavigator,val context: Context) : EditProfileEvent()


}
