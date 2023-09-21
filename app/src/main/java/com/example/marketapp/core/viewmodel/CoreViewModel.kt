package com.example.marketapp.core.viewmodel

import androidx.lifecycle.ViewModel
import com.example.marketapp.destinations.LoginScreenDestination
import com.example.marketapp.destinations.MethodsScreenDestination
import com.example.marketapp.destinations.OnBoardingScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor() : ViewModel() {

    private fun initApp() {

    }

    suspend fun onSplashScreenLaunch(navigator : DestinationsNavigator?){
        initApp()
        delay(1000)
        navigator?.navigate(OnBoardingScreenDestination())
    }

    fun onOnBoardingScreenNextClick(navigator : DestinationsNavigator?){
        navigator?.navigate(MethodsScreenDestination())
    }

    fun onOnBoardingScreenSkipClick(navigator : DestinationsNavigator?){
        navigator?.navigate(MethodsScreenDestination())
    }

    fun onMethodsScreenLoginClick(navigator : DestinationsNavigator?){
        navigator?.navigate(LoginScreenDestination())
    }

    fun onMethodsScreenRegisterClick(navigator : DestinationsNavigator?){
        //navigator?.navigate(MethodsScreenDestination())
    }

    fun onMethodsScreenLoginWithGoogleClick(navigator : DestinationsNavigator?){
        //navigator?.navigate(MethodsScreenDestination())
    }

    sealed class CoreUiEvent

}