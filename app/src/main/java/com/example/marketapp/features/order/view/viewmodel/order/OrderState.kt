package com.example.marketapp.features.order.view.viewmodel.order

import android.net.Uri
import retrofit2.http.Part
import java.io.File

data class OrderState(
    var description : String = "",
    var isMakingOrderStep1 : Boolean = false,

    var images: List<Uri> = emptyList(),
    var records: List<File> = emptyList(),
    var files: List<Uri> = emptyList(),
)
