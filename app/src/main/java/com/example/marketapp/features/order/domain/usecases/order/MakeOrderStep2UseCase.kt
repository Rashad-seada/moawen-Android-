package com.example.marketapp.features.order.domain.usecases.order

import android.content.Context
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.auth.data.entities.check_code_sent.CheckCodeSentResponse
import com.example.marketapp.features.auth.data.repo.AuthRepoImpl
import com.example.marketapp.features.auth.infrastructure.api.request.CheckCodeSentRequest
import com.example.marketapp.features.order.data.entities.make_order_step_1.MakeOrderStep1Response
import com.example.marketapp.features.order.data.entities.make_order_step_2.MakeOrderStep2Response
import com.example.marketapp.features.order.data.repo.OrderRepoImpl
import com.example.marketapp.features.order.infrastructure.api.requests.MakeOrderStep1Input
import com.example.marketapp.features.order.infrastructure.api.requests.MakeOrderStep2Request
import javax.inject.Inject


class MakeOrderStep2UseCase @Inject constructor(
    val repo: OrderRepoImpl
) {

    suspend operator fun invoke(
        token: String,
        makeOrderStep2Request: MakeOrderStep2Request,
        context: Context
    ): Resource<MakeOrderStep2Response> {

        return repo.makeOrderStep2(token, makeOrderStep2Request, context)

    }

}