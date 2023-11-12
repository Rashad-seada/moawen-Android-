package com.example.marketapp.features.order.view.viewmodel.select_location

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketapp.R
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.destinations.OrderMessageScreenDestination
import com.example.marketapp.destinations.SearchLocationFromScreenDestination
import com.example.marketapp.destinations.SearchLocationToScreenDestination
import com.example.marketapp.features.order.domain.usecases.order.MakeOrderStep2UseCase
import com.example.marketapp.features.order.domain.usecases.places.GetDirectionsUseCase
import com.example.marketapp.features.order.domain.usecases.places.GetPlaceLatLongUseCase
import com.example.marketapp.features.order.domain.usecases.places.SearchPlacesUseCase
import com.example.marketapp.features.order.infrastructure.api.requests.MakeOrderStep2Request
import com.example.marketapp.features.order.infrastructure.services.utils.PlaceInfo
import com.example.marketapp.features.order.view.viewmodel.order.OrderViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.model.TravelMode
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectLocationViewModel @Inject constructor(
    private val getPlaceLatLongUseCase: GetPlaceLatLongUseCase,
    private val searchPlacesUseCase: SearchPlacesUseCase,
    private val getDirectionsUseCase: GetDirectionsUseCase,
    private val makeOrderStep2UseCase: MakeOrderStep2UseCase,
) : ViewModel() {

    var state by mutableStateOf(SelectLocationState())

    private var job: Job? = null

    fun getDirections() {

            viewModelScope.launch(Dispatchers.IO) {
                getDirectionsUseCase(
                    fromLat = state.fromPlaceInfo!!.latLng!!.latitude,
                    fromLong = state.fromPlaceInfo!!.latLng!!.longitude,
                    toLat = state.toPlaceInfo!!.latLng!!.latitude,
                    toLong = state.toPlaceInfo!!.latLng!!.longitude,
                    mode = TravelMode.DRIVING,

                ).collect {
                    if (it.failure != null) {
                        CoreViewModel.showSnackbar(("Error:" + it.failure.message))
                        state = state.copy(placesError = it.failure.message)
                    } else {
                        state = state.copy(route = it.data ?: emptyList())
                    }


                }
            }



    }

    fun onSearchUpdate(query: String) {

            state = state.copy(
                placesError = null,
                locationQuery = query
            )
            viewModelScope.launch(Dispatchers.IO) {

                state = state.copy(isSearchingPlaces = true)

                searchPlacesUseCase(state.locationQuery).collect {
                    if (it.failure != null) {
                        CoreViewModel.showSnackbar(("Error:" + it.failure.message))
                        state = state.copy(placesError = it.failure.message)
                    } else {
                        state = state.copy(placesInfo = it.data ?: emptyList())
                    }
                }

                state = state.copy(isSearchingPlaces = false)
            }


    }

    private fun onSearch() {}

    private fun onFromLocationClick(navigator: DestinationsNavigator) {
        navigator.navigate(SearchLocationFromScreenDestination())
    }

    private fun onToLocationClick(navigator: DestinationsNavigator) {
        navigator.navigate(SearchLocationToScreenDestination())
    }

    private fun onLocationSelected(navigator: DestinationsNavigator, context: Context) {

        if (state.fromPlaceInfo == null || state.toPlaceInfo == null) {
            state = state.copy(mapError = context.getString(R.string.select_from_to_distenations))
            return
        }
            viewModelScope.launch(Dispatchers.IO) {

                state = state.copy(
                    isSelectingPlace = true,
                    mapError = null
                )

                val response = makeOrderStep2UseCase(
                    token = CoreViewModel.user!!.token,
                    makeOrderStep2Request = MakeOrderStep2Request(
                        state.fromPlaceInfo!!.latLng!!.latitude,
                        state.fromPlaceInfo!!.latLng!!.longitude,
                        state.toPlaceInfo!!.latLng!!.latitude,
                        state.toPlaceInfo!!.latLng!!.longitude,
                        OrderViewModel.orderId!!
                    ),
                    context = context
                )
                if (response.failure != null) {
                    CoreViewModel.showSnackbar(("Error:" + response.failure.message))

                } else {
                    viewModelScope.launch(Dispatchers.Main) {
                        navigator.navigate(OrderMessageScreenDestination)
                    }
                }

                state = state.copy(isSelectingPlace = false)
            }

    }

    private fun onFromLocationSelect(navigator: DestinationsNavigator, placeInfo: PlaceInfo) {
            viewModelScope.launch(Dispatchers.IO) {

                state = state.copy(isSearchingPlaces = true)
                val response = getPlaceLatLongUseCase(placeInfo.placeId).collect {
                    if (it.failure != null) {
                        CoreViewModel.showSnackbar(("Error:" + it.failure.message))

                    } else {
                        it.data?.latitude
                        state = state.copy(
                            fromPlaceInfo = placeInfo.copy(
                                latLng = LatLng(
                                    it.data?.latitude ?: 0.0,
                                    it.data?.longitude ?: 0.0
                                )
                            )
                        )

                    }
                }
                state = state.copy(isSearchingPlaces = false)
            }
            navigator.popBackStack()


    }

    private fun onToLocationSelect(navigator: DestinationsNavigator, placeInfo: PlaceInfo) {
            viewModelScope.launch(Dispatchers.IO) {

                state = state.copy(isSearchingPlaces = true)
                val response = getPlaceLatLongUseCase(placeInfo.placeId).collect {
                    if (it.failure != null) {
                        CoreViewModel.showSnackbar(("Error:" + it.failure.message))

                    } else {
                        it.data?.latitude
                        state = state.copy(
                            toPlaceInfo = placeInfo.copy(
                                latLng = LatLng(
                                    it.data?.latitude ?: 0.0,
                                    it.data?.longitude ?: 0.0
                                )
                            )
                        )

                    }
                }
                state = state.copy(isSearchingPlaces = false)
            }
            navigator.popBackStack()

    }


    fun onEvent(event: SelectLocationEvent) {
        when (event) {
            is SelectLocationEvent.OnSearch -> {
                onSearch()
            }
            is SelectLocationEvent.OnLocationSelected -> {
                onLocationSelected(event.navigator, event.context)
            }
            is SelectLocationEvent.OnFromLocationClick -> {
                onFromLocationClick(event.navigator)
            }
            is SelectLocationEvent.OnToLocationClick -> {
                onToLocationClick(event.navigator)
            }
            is SelectLocationEvent.OnFromLocationSelect -> {
                onFromLocationSelect(event.navigator, event.placeInfo)
            }
            is SelectLocationEvent.OnToLocationSelect -> {
                onToLocationSelect(event.navigator, event.placeInfo)
            }
            is SelectLocationEvent.GetDirections -> {
                getDirections()
            }

        }
    }


}