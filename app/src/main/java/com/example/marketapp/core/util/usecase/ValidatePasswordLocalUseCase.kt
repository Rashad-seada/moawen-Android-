package com.example.marketapp.core.util.usecase

import android.content.Context
import com.example.marketapp.R
import com.example.marketapp.core.errors.Failure
import com.example.marketapp.core.util.Resource
import javax.inject.Inject

class ValidatePasswordLocalUseCase @Inject constructor() {

    operator fun invoke(password: String, context: Context): Resource<Boolean?> {

        if (password.isBlank()) {
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
        if (!password.contains(regex)) {
            return Resource.FailureData(
                Failure(
                    message = context.getString(R.string.password_contain_special_char),
                    screenIdInt = 0,
                    exceptionCode = 0,
                    customCode = 0
                )
            )
        }

        if (password == password.lowercase()) {
            return Resource.FailureData(
                Failure(
                    message = context.getString(R.string.password_contain_lower_and_upper_case),
                    screenIdInt = 0,
                    exceptionCode = 0,
                    customCode = 0
                )
            )
        }

        if (password.length < 6) {
            return Resource.FailureData(
                Failure(
                    message = context.getString(R.string.password_is_not_eight),
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