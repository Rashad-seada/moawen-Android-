package com.example.marketapp.features.auth.domain.repo

import android.content.Context
import com.example.marketapp.features.auth.infrastructure.Api.LoginResposne

interface AuthRepo {
    suspend fun login(email : String,password : String,context : Context,screenId :Int) : LoginResposne

}