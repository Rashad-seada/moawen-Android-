package com.example.marketapp.features.profile.view.viewmodels.edit_profile

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.features.auth.domain.usecases.GetUserInfoUseCase
import com.example.marketapp.features.auth.domain.usecases.SaveUserInfoUseCase
import com.example.marketapp.features.profile.domain.usecase.UpdatePhoneNumberStep2Usecase
import com.example.marketapp.features.profile.domain.usecase.UpdatePhoneNumberUsecase
import com.example.marketapp.features.profile.domain.usecase.UpdateProfileNameAndImageUsecase
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val updatePhoneNumberUsecase: UpdatePhoneNumberUsecase,
    private val updatePhoneNumberStep2Usecase: UpdatePhoneNumberStep2Usecase,

    private val updateProfileNameAndImageUsecase: UpdateProfileNameAndImageUsecase,
    private val saveUserInfoUseCase: SaveUserInfoUseCase,

) : ViewModel() {

    var state : EditProfileState by mutableStateOf(EditProfileState())
    private var job: Job? = null

    fun updateUsername(username : String){
        state = state.copy(
            usernameTextField = username
        )
    }

    fun updatePhone(phone : String){
        state = state.copy(
            phoneTextField = phone
        )
    }

    fun updateProfileImage(image: Uri?){

            state = state.copy(
                pickedProfileImage = image
            )


    }


    private fun onSave(navigator: DestinationsNavigator, context: Context) {
        if(job == null){
            job = viewModelScope.launch(Dispatchers.IO) {

                state = state.copy(
                    profileError = null,
                    profileIsLoading = true
                )

                val response = if(state.usernameTextField.isBlank()){

                    updateProfileNameAndImageUsecase(
                        CoreViewModel.user!!.token,
                        CoreViewModel.user!!.fullname,
                        state.pickedProfileImage,
                        context
                    )

                }else{

                    updateProfileNameAndImageUsecase(
                        CoreViewModel.user!!.token,
                        state.usernameTextField,
                        state.pickedProfileImage,
                        context
                    )
                }

                if (response.failure != null) {
                    state = state.copy(
                        profileError = response.failure.message
                    )
                    CoreViewModel.showSnackbar(("Error:" + response.failure.message))

                    Log.v("CoreViewModel",response.failure.message)

                } else {

                    saveUserInfoUseCase(
                        CoreViewModel.user!!.copy(
                            image = response.data!!.data.user.image,
                            fullname = response.data.data.user.fullname,

                        ),
                        context,
                        0
                    )

                    CoreViewModel.user = CoreViewModel.user!!.copy(
                        image = response.data.data.user.image,
                        fullname = response.data.data.user.fullname,
                    )

                    state = state.copy(
                        profileImage = response.data.data.user.image,
                        username = response.data.data.user.fullname,
                    )

                    CoreViewModel.showSnackbar(("Success:" + response.data.message))

                }


                state = state.copy(
                    profileIsLoading = false
                )


            }

            Log.v("username",CoreViewModel.user.toString())

            job = null
        }
    }

    fun onEvent(event: EditProfileEvent){
        when(event){
            is EditProfileEvent.OnSave -> {
                onSave(event.navigator,event.context)
            }
        }
    }


}