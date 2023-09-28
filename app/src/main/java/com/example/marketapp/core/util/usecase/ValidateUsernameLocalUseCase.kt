package com.example.marketapp.core.util.usecase

import android.content.Context
import com.example.marketapp.R
import com.example.marketapp.core.errors.Failure
import com.example.marketapp.core.util.Resource
import javax.inject.Inject

class ValidateUsernameLocalUseCase @Inject constructor()  {

    operator fun invoke(username: String, context: Context): Resource<Boolean?> {

        if (username.isBlank()) {
            return Resource.FailureData(
                Failure(
                    message = context.getString(R.string.string_is_blank),
                    screenIdInt = 0,
                    exceptionCode = 0,
                    customCode = 0
                )
            )
        }

        val regex = Regex("[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>?/~]")
        if (username.contains(regex)) {
            return Resource.FailureData(
                Failure(
                    message = context.getString(R.string.username_contain_special_char),
                    screenIdInt = 0,
                    exceptionCode = 0,
                    customCode = 0
                )
            )
        }

        if (username.trim().contains(" ")) {
            return Resource.FailureData(
                Failure(
                    message = context.getString(R.string.username_contain_white_space),
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