package com.example.marketapp.core.errors

open class Failure(
    val message: String,
    private val screenIdInt: Int,
    val exceptionCode: Int,
    val customCode: Int,
) {
    val screenId: String
        get() = screenIdInt.toString()

    override fun toString(): String = "$screenId$exceptionCode$customCode"

    fun code(): Int = "$screenId$exceptionCode$customCode".toInt()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Failure

        if (message != other.message) return false
        if (screenId != other.screenId) return false
        if (exceptionCode != other.exceptionCode) return false
        if (customCode != other.customCode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = message.hashCode()
        result = 31 * result + screenId.hashCode()
        result = 31 * result + exceptionCode
        result = 31 * result + customCode
        return result
    }
}

class InternalFailure(
    message: String,
    screenId: Int,
    customCode: Int
) : Failure(message, screenId, 1, customCode)

class RemoteDataFailure(
    message: String,
    screenId: Int,
    customCode: Int
) : Failure(message, screenId, 2, customCode)

class LocalDataFailure(
    message: String,
    screenId: Int,
    customCode: Int
) : Failure(message, screenId, 3, customCode)

class ServiceFailure(
    message: String,
    screenId: Int,
    customCode: Int
) : Failure(message, screenId, 4, customCode)
