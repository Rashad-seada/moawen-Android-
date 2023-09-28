package com.example.marketapp.features.auth.infrastructure.database.user_info_shared_pref

import android.content.Context
import com.example.marketapp.core.errors.LocalDataException
import com.google.gson.Gson

interface UserInfoSharedPref {

    fun getUserInfo(context: Context) : UserInfo?

    fun saveUserInfo(context: Context, userInfo: UserInfo)

    fun deleteUserInfo(context: Context)

}


class UserInfoSharedPrefImpl : UserInfoSharedPref {

    private val USER_INFO_KEY = "user_info_key"
    private val USER_INFO_PREF_NAME = "user_info_pref_name"

    private fun serializeObject(userInfo: UserInfo): String {
        val gson = Gson()
        return gson.toJson(userInfo)
    }


    override fun getUserInfo(context: Context): UserInfo? {
        try {
            val sharedPreferences = context.getSharedPreferences(USER_INFO_PREF_NAME, Context.MODE_PRIVATE)
            val serializedObject = sharedPreferences.getString(USER_INFO_KEY, null)

            if (serializedObject != null) {
                val gson = Gson()
                return gson.fromJson(serializedObject, UserInfo::class.java)
            }

            return null
        } catch (e : Exception) {
            throw LocalDataException(e.message.toString())
        }

    }
    override fun saveUserInfo(context: Context, userInfo: UserInfo) {
        try {
            val sharedPreferences = context.getSharedPreferences(USER_INFO_PREF_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val serializedObject = serializeObject(userInfo)
            editor.putString(USER_INFO_KEY, serializedObject)
            editor.apply()
        } catch (e : Exception) {
            throw LocalDataException(e.message.toString())
        }

    }

    override fun deleteUserInfo(context: Context) {
        try {
            val sharedPreferences = context.getSharedPreferences(USER_INFO_PREF_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove(USER_INFO_KEY)
            editor.apply()
        } catch (e : Exception) {
            throw LocalDataException(e.message.toString())
        }
    }


}