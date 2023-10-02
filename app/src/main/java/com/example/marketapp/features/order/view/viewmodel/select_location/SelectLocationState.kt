package com.example.marketapp.features.order.view.viewmodel.select_location

import com.example.marketapp.features.order.infrastructure.services.utils.PlaceInfo

data class SelectLocationState(
    var locationQuery : String = "",
    var isSearchingPlaces : Boolean = false,

    var placesInfo : List<PlaceInfo> = emptyList(),
    var placesError : String? = null,

    var fromPlaceInfo : PlaceInfo? = null,
    var toPlaceInfo : PlaceInfo? = null,

    val isSelectingPlace: Boolean = false

) {
}
