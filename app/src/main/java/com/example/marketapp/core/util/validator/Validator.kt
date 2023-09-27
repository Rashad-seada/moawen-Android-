package com.example.marketapp.core.util.validator

import android.util.Patterns

class Validator() {

    fun isValidEmail( string: String) : Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(string).matches()
    }

    fun isValidPhone( string: String) : Boolean {
        return Patterns.PHONE.matcher(string).matches()
    }

    fun isValidUsername( string: String): Boolean{
        return true
    }

}