package com.example.marketapp.features.order.infrastructure.services.utils

import com.google.android.gms.maps.model.LatLng

data class PlaceInfo(
    val placeId: String,
    val name: String,
    val address: String,
    var latLng : LatLng? = null
)