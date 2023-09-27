package com.example.marketapp.core.viewmodel

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import com.example.marketapp.destinations.LoginMethodsScreenDestination
import com.example.marketapp.destinations.LoginScreenDestination
import com.example.marketapp.destinations.OnBoardingScreenDestination
import com.example.marketapp.destinations.RegisterScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor() : ViewModel() {

    companion object {
        private var job: Job? = null
        val scope = CoroutineScope(Dispatchers.Default)


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

    }

    suspend fun onSplashScreenLaunch(navigator: DestinationsNavigator?) {
        initApp()
        delay(1000)
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

    fun onMethodsScreenLoginWithGoogleClick(navigator: DestinationsNavigator?) {
        //navigator?.navigate(MethodsScreenDestination())
    }

    sealed class CoreUiEvent

}