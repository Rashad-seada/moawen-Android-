package com.example.marketapp.core.util.usecase

import android.content.Context
import com.example.marketapp.R
import com.example.marketapp.core.errors.Failure
import com.example.marketapp.core.util.Resource
import javax.inject.Inject

class ValidatePasswordRepeatedLocalUseCase @Inject constructor()  {

    operator fun invoke(repeatedPassword: String,password: String, context: Context): Resource<Boolean?> {

        if (repeatedPassword.isBlank()) {
            return Resource.FailureData(
                Failure(
                    message = context.getString(R.string.string_is_blank),
                    screenIdInt = 0,
                    exceptionCode = 0,
                    customCode = 0
                )
            )
        }

        if (repeatedPassword != password) {
            return Resource.FailureData(
                Failure(
                    message = context.getString(R.string.password_must_match),
                    screenIdInt = 0,
                    exceptionCode = 0,
                    customCode = 0
                )
            )
        }



        return Resource.SuccessData(
            true
        )


    }
}