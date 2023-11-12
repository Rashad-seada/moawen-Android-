package com.example.marketapp.features.order.view.viewmodel.select_location

import android.content.Context
import com.example.marketapp.features.order.infrastructure.services.utils.PlaceInfo
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class SelectLocationEvent {

    data class OnSearch(var query: String, val navigator: DestinationsNavigator) : SelectLocationEvent()
    data class OnFromLocationClick(val navigator: DestinationsNavigator) : SelectLocationEvent()

    data class OnToLocationClick(val navigator: DestinationsNavigator) : SelectLocationEvent()
    data class OnLocationSelected(val navigator: DestinationsNavigator, val context: Context) : SelectLocationEvent()


    data class OnFromLocationSelect(val navigator: DestinationsNavigator,val placeInfo: PlaceInfo) : SelectLocationEvent()
    data class OnToLocationSelect(val navigator: DestinationsNavigator,val placeInfo: PlaceInfo) : SelectLocationEvent()

    object GetDirections : SelectLocationEvent()

}