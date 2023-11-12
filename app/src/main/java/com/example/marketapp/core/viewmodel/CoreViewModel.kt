package com.example.marketapp.core.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.AndroidViewModel
import com.example.marketapp.destinations.*
import com.example.marketapp.features.auth.data.entities.login.User
import com.example.marketapp.features.auth.domain.usecases.GetUserInfoUseCase
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
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
    application: Application,
) : AndroidViewModel(application) {

    var splashScreenId = 0

    companion object {
        private var job: Job? = null
        val scope = CoroutineScope(Dispatchers.Default)


        var user : User? = null



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
            user = result.data
        }
    }

    suspend fun onSplashScreenLaunch(navigator: DestinationsNavigator?) {
        initApp()
        delay(1000)

        if(user != null) {
            navigator?.navigate(SelectLocationScreenDestination())

        } else {
            navigator?.navigate(OnBoardingScreenDestination())

        }



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

    sealed class CoreUiEvent

}