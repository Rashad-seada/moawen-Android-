package com.example.marketapp.features.profile.view.viewmodels.edit_profile

import android.net.Uri
import com.example.marketapp.core.viewmodel.CoreViewModel

data class EditProfileState(


    var profileImage : String = CoreViewModel.user?.image?: "",
    var username : String = CoreViewModel.user?.fullname?: "",
    var phone : String = (CoreViewModel.user?.country_code?: "") + " " + (CoreViewModel.user?.phone?: ""),

    var pickedProfileImage : Uri? = null,


    var usernameTextField : String = CoreViewModel.user?.fullname?: "",
    var phoneTextField : String = CoreViewModel.user?.phone?: "",

    var profileIsLoading : Boolean = false,
    var profileError : String? = null,
)
