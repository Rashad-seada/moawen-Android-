package com.example.marketapp.features.home.domain.repo

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.home.data.entities.home.HomeResponse

interface HomeRepo {

    suspend fun home(token : String,context: Context): Resource<HomeResponse>

}