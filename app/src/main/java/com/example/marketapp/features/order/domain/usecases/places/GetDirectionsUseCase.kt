package com.example.marketapp.features.order.domain.usecases.places

import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.order.infrastructure.services.PlacesService
import com.google.android.gms.maps.model.LatLng
import com.google.maps.model.DirectionsResult
import com.google.maps.model.TravelMode
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDirectionsUseCase @Inject constructor(
    val service: PlacesService
){

    operator fun invoke(

        fromLat : Double,
        fromLong : Double,
        toLat : Double,
        toLong : Double,
        mode: TravelMode = TravelMode.DRIVING,

    ): Flow<Resource<List<LatLng>>> {

        return service.getDirectionsFlow(
            origin = "$fromLat,$fromLong",
            destination = "$toLat,$toLong",
            mode = mode
        )

    }

}