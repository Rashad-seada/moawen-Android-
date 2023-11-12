package com.example.marketapp.features.order.view.viewmodel.order

import android.net.Uri
import com.example.marketapp.features.order.data.entities.order_details.Orders
import com.example.marketapp.features.order.data.entities.orders.Order
import retrofit2.http.Part
import java.io.File

data class OrderState(

    var description : String = "",
    var isMakingOrderStep1 : Boolean = false,
    var makeOrdersError : String? = null,

    var isOrdersLoading : Boolean = false,
    var orders: List<Order> = emptyList(),
    var ordersError : String? = null,

    var images: List<Uri> = emptyList(),
    var records: List<File> = emptyList(),
    var files: List<Uri> = emptyList(),
)
