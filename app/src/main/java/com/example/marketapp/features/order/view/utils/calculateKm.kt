package com.example.marketapp.features.order.view.utils

import com.google.android.gms.maps.model.LatLng
import java.lang.Math.*
import kotlin.math.pow

fun calculateDistanceInKilometers(point1: LatLng, point2: LatLng): Double {
    val earthRadiusKm = 6371.0 // Earth radius in kilometers

    val lat1Rad = toRadians(point1.latitude)
    val lon1Rad = toRadians(point1.longitude)
    val lat2Rad = toRadians(point2.latitude)
    val lon2Rad = toRadians(point2.longitude)

    val dLat = lat2Rad - lat1Rad
    val dLon = lon2Rad - lon1Rad

    val a = sin(dLat / 2).pow(2) + cos(lat1Rad) * cos(lat2Rad) * sin(dLon / 2).pow(2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    return earthRadiusKm * c
}