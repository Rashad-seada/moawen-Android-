package com.example.marketapp.features.auth.domain.repo

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.LoginEntity

interface AuthRepo {
    suspend fun login(email : String,password : String,context : Context,screenId :Int) : Resource<LoginEntity>

}