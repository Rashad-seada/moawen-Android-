package com.example.marketapp.features.e_learning.view.viewmodels.main

import com.example.marketapp.features.e_learning.view.utils.BottomNavPage

data class MainState(
    var index: Int = 0,
    val pages: List<BottomNavPage> = listOf(
        BottomNavPage.HomeNavPage,
        BottomNavPage.HomeNavPage,
        BottomNavPage.HomeNavPage,
    )
)
