package com.example.marketapp.features.home.view.viewmodels.home

import com.example.marketapp.features.home.view.utils.HomeFeatures

data class HomeState(

    var profileImage : String? = null,
    var username : String? = null,
    var wallet : Double? = null,
    var currency : String? = null,

    var assistantImage : String? = null,
    var assistantName : String? = null,

    var isHomePageLoading : Boolean = false,
    var error : String? = null,

    )
