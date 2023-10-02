package com.example.marketapp.features.order.domain.usecases.places

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.check_code_sent.CheckCodeSentResponse
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import com.example.marketapp.features.auth.infrastructure.api.request.CheckCodeSentRequest
import com.example.marketapp.features.order.data.entities.cancel_order.CancelOrderResponse
import com.example.marketapp.features.order.data.entities.make_order_step_1.MakeOrderStep1Response
import com.example.marketapp.features.order.data.repo.OrderRepoImpl
import com.example.marketapp.features.order.infrastructure.api.requests.CancelOrderRequest
import com.example.marketapp.features.order.infrastructure.api.requests.MakeOrderStep1Input
import com.example.marketapp.features.order.infrastructure.services.PlacesService
import com.example.marketapp.features.order.infrastructure.services.utils.PlaceInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SearchPlacesUseCase @Inject constructor(
    val service: PlacesService
) {

    suspend operator fun invoke(
        query: String,
    ): Flow<Resource<List<PlaceInfo>>> {

        return service.searchPlaces(query)

    }

}