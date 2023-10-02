package com.example.marketapp.features.home.view.viewmodels.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {


    var state by mutableStateOf(MainState())

    fun updateCurrentIndex(index: Int) {
        state = state.copy(
            index = index
        )
    }

}