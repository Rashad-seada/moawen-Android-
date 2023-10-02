package com.example.marketapp.features.order.domain.usecases.places

import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.order.infrastructure.services.PlacesService
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetPlaceLatLongUseCase @Inject constructor(
    val service: PlacesService
) {

    suspend operator fun invoke(
        placeId: String,
    ): Flow<Resource<LatLng?>> {

        return service.getLatLngForPlace(placeId)

    }

}