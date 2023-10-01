package com.example.marketapp.core.viewmodel

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.AndroidViewModel
import com.example.marketapp.destinations.LoginMethodsScreenDestination
import com.example.marketapp.destinations.LoginScreenDestination
import com.example.marketapp.destinations.OnBoardingScreenDestination
import com.example.marketapp.destinations.RegisterScreenDestination
import com.example.marketapp.features.auth.domain.usecases.GetUserInfoUseCase
import com.example.marketapp.features.auth.infrastructure.database.user_info_shared_pref.UserInfo
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    val getUserInfoUseCase: GetUserInfoUseCase,
    val signWithGoogleService : GoogleSignInClient,
    application: Application,
) : AndroidViewModel(application) {

    var splashScreenId = 0

    companion object {
        private var job: Job? = null

        val scope = CoroutineScope(Dispatchers.Default)

        var userInfo : UserInfo? = null

        val snackbarHostState = SnackbarHostState()
        fun showSnackbar(message : String) {
            job?.cancel()
            job = scope.launch {
                snackbarHostState
                    .showSnackbar(
                        message = message,
                        duration = SnackbarDuration.Short,
                        withDismissAction = true
                    )
            }
        }



    }


    private fun initApp() {
        getUserInfo()
    }

    fun getUserInfo(){
        val result = getUserInfoUseCase(getApplication<Application>().applicationContext,splashScreenId)
        if(result.data != null){
            userInfo = result.data
        }
    }

    suspend fun onSplashScreenLaunch(navigator: DestinationsNavigator?) {
        initApp()
        delay(1000)
//
//        if(userInfo != null){
//            navigator?.navigate(LoginScreenDestination())
//        }else {
//            navigator?.navigate(OnBoardingScreenDestination())
//        }

        navigator?.navigate(OnBoardingScreenDestination())

    }

    fun onOnBoardingScreenNextClick(navigator: DestinationsNavigator?) {
        navigator?.navigate(LoginMethodsScreenDestination())
    }

    fun onOnBoardingScreenSkipClick(navigator: DestinationsNavigator?) {
        navigator?.navigate(LoginMethodsScreenDestination())
    }

    fun onMethodsScreenLoginClick(navigator: DestinationsNavigator?) {
        navigator?.navigate(LoginScreenDestination())
    }

    fun onMethodsScreenRegisterClick(navigator: DestinationsNavigator?) {
        navigator?.navigate(RegisterScreenDestination())
    }

    fun onMethodsScreenLoginWithGoogleClick(navigator: DestinationsNavigator?,task : Task<GoogleSignInAccount>) {
        if(task.isSuccessful){
            Log.v("Google","is successful")
        }else {
            Log.v("Google","is not successful")
        }
        navigator?.navigate(RegisterScreenDestination)
    }

    fun provideSignInIntent() : Intent {
        return signWithGoogleService.signInIntent
    }

    sealed class CoreUiEvent

}