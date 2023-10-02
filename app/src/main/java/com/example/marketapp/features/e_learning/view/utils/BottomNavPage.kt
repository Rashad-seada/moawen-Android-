package com.example.marketapp.features.e_learning.view.utils

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marketapp.R
import com.example.marketapp.features.e_learning.view.pages.home.HomePage
import com.example.marketapp.features.e_learning.view.viewmodels.home.HomeViewModel

sealed class BottomNavPage(
    val page: @Composable () -> Unit,
    val icon: Int,
    val label: Int
) {
    @OptIn(ExperimentalMaterial3Api::class)
    object HomeNavPage : BottomNavPage(
        page = {
            val viewModel : HomeViewModel = hiltViewModel()
            HomePage()
        },
        icon = R.drawable.home,
        label = R.string.home
    )

}

