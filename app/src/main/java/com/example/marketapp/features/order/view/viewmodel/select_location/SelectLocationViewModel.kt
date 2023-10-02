package com.example.marketapp.features.order.view.viewmodel.select_location

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.destinations.OrderMessageScreenDestination
import com.example.marketapp.destinations.SearchLocationFromScreenDestination
import com.example.marketapp.destinations.SearchLocationToScreenDestination
import com.example.marketapp.features.order.domain.usecases.order.MakeOrderStep2UseCase
import com.example.marketapp.features.order.domain.usecases.places.GetPlaceLatLongUseCase
import com.example.marketapp.features.order.domain.usecases.places.SearchPlacesUseCase
import com.example.marketapp.features.order.infrastructure.api.requests.MakeOrderStep2Request
import com.example.marketapp.features.order.infrastructure.services.utils.PlaceInfo
import com.example.marketapp.features.order.view.viewmodel.order.OrderViewModel
import com.google.android.gms.maps.model.LatLng
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
    private val makeOrderStep2UseCase: MakeOrderStep2UseCase,
    ) : ViewModel() {

    var state by mutableStateOf(SelectLocationState())
    private var job: Job? = null

    fun onSearchUpdate(query: String) {
        state = state.copy(
            placesError = null,
            locationQuery = query
        )
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(isSearchingPlaces = true)
            val response = searchPlacesUseCase(state.locationQuery).collect {
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

    private fun onLocationSelected(navigator: DestinationsNavigator,context: Context) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(isSelectingPlace = true)
            val response = makeOrderStep2UseCase(
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI3IiwianRpIjoiNmRhYTVkZTI1MDhmYzkyYzkyODJiOGRhZDNlMjg0ZTAwNGEzZTE2M2E4YmViODFkMDAyNzU0ZDE0M2I2Y2NkOWMxZDQ2ODllYzczYTFlNjMiLCJpYXQiOjE2OTU0MzA4MzIuMzkwNjI1LCJuYmYiOjE2OTU0MzA4MzIuMzkwNjMwMDA2NzkwMTYxMTMyODEyNSwiZXhwIjoxNzI3MDUzMjMyLjM4NzQzMjA5ODM4ODY3MTg3NSwic3ViIjoiMzMiLCJzY29wZXMiOlsidXNlciJdfQ.HRWUo2lHBsPkpUzAUcSy-86EIDFdendDWDBKpzrgJrkB98e1mi14cjYw_hmwjsnxNhwMtLl9tR-a4seStBiOMWoD0Xb6y68KFRHVBHhMWx4WPqjDOqlIVcs2bfs0W3mNSiwKjTTYLTyB-YJQqwhqi18_vfjUzsCmkj8T1hS_GreHontZXOeZS0G-vQ7AwuHVlvJ9yxiQNjH9oJ_dxKNC-86Iuz4ml_teAJzjgZn1AsJ91OQcDYeNf_PdRF2a3a8GcrMediCFGLXYTpOFnbqohqSlOIJqyGPF0bg-T4CiGQmorvTSyvFfCt64sAsh_1kWGYoUQSH7_EshLOwPF3yKGg",
                MakeOrderStep2Request(
                    state.fromPlaceInfo!!.latLng!!.latitude,
                    state.fromPlaceInfo!!.latLng!!.longitude,
                    state.toPlaceInfo!!.latLng!!.latitude,
                    state.toPlaceInfo!!.latLng!!.longitude,
                    OrderViewModel.orderId!!
                    ),
                context
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
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(isSearchingPlaces = true)
            val response = getPlaceLatLongUseCase(placeInfo.placeId).collect {
                if (it.failure != null) {
                    CoreViewModel.showSnackbar(("Error:" + it.failure.message))

                } else {
                    it.data?.latitude
                    state = state.copy(
                        fromPlaceInfo = placeInfo.copy(
                            latLng = LatLng(
                                it.data?.latitude?: 0.0,
                                it.data?.longitude?: 0.0
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
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(isSearchingPlaces = true)
            val response = getPlaceLatLongUseCase(placeInfo.placeId).collect {
                if (it.failure != null) {
                    CoreViewModel.showSnackbar(("Error:" + it.failure.message))

                } else {
                    it.data?.latitude
                    state = state.copy(
                        toPlaceInfo = placeInfo.copy(
                            latLng = LatLng(
                                it.data?.latitude?: 0.0,
                                it.data?.longitude?: 0.0
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
                onLocationSelected(event.navigator,event.context)
            }
            is SelectLocationEvent.OnFromLocationClick -> {
                onFromLocationClick(event.navigator)
            }
            is SelectLocationEvent.OnToLocationClick -> {
                onToLocationClick(event.navigator)
            }
            is SelectLocationEvent.OnFromLocationSelect -> {
                onFromLocationSelect(event.navigator,event.placeInfo)
            }
            is SelectLocationEvent.OnToLocationSelect -> {
                onToLocationSelect(event.navigator,event.placeInfo)
            }

        }
    }


}