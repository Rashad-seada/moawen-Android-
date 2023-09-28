package com.example.marketapp.features.auth.infrastructure.database.user_info_shared_pref

import java.io.Serializable

data class UserInfo(val id: Int, val password: String) : Serializable
