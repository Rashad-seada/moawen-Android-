package com.example.marketapp.features.order.view.viewmodel.select_location

import com.example.marketapp.features.order.infrastructure.services.utils.PlaceInfo
import com.google.android.gms.maps.model.LatLng

data class SelectLocationState(
    var locationQuery : String = "",
    var isSearchingPlaces : Boolean = false,

    var placesInfo : List<PlaceInfo> = emptyList(),
    var placesError : String? = null,
    var mapError : String? = null,

    var route : List<LatLng> = emptyList(),


    var fromPlaceInfo : PlaceInfo? = null,
    var toPlaceInfo : PlaceInfo? = null,
    val isSelectingPlace: Boolean = false
)
