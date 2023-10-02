package com.example.marketapp.features.e_learning.view.viewmodels.home

import com.example.marketapp.features.e_learning.view.utils.HomeFeatures

data class HomeState(
    val features: List<HomeFeatures> = listOf(
        HomeFeatures.Study,
        HomeFeatures.HomeWork,
        HomeFeatures.Books,
        HomeFeatures.Exams,
        HomeFeatures.Tests,
        HomeFeatures.OpenBook,
        HomeFeatures.LanguageArt,
        HomeFeatures.PersonalInfo,
        HomeFeatures.Propose,
        HomeFeatures.Read,
        HomeFeatures.Resume,
    )
)
