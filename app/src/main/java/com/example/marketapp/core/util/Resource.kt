package com.example.marketapp.core.util

import com.example.marketapp.core.errors.Failure

sealed class Resource<T>(
    val data: T? = null,
    val failure: Failure? = null,
) {
    class SuccessData<T>(data: T) : Resource<T>(data = data)
    class FailureData<T>(failure: Failure?) : Resource<T>(failure = failure)

}
