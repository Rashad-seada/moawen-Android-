package com.example.marketapp.core.mappers.base64_converters

import java.util.Base64

fun String.toBase64(): String {
    val encodedBytes: ByteArray = Base64.getEncoder().encode(this.toByteArray())
    return String(encodedBytes)
}