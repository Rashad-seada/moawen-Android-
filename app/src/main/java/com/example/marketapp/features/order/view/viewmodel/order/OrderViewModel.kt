package com.example.marketapp.features.order.view.viewmodel.order

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketapp.R
import com.example.marketapp.core.viewmodel.CoreViewModel
import com.example.marketapp.destinations.SelectLocationScreenDestination
import com.example.marketapp.features.order.domain.usecases.order.MakeOrderStep1UseCase
import com.example.marketapp.features.order.domain.usecases.order.OrderUseCase
import com.example.marketapp.features.order.infrastructure.api.requests.MakeOrderStep1Input
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderStep1UseCase: MakeOrderStep1UseCase,
    private val orderUseCase: OrderUseCase,
    )  : ViewModel() {

    companion object {
        var orderId : Int? = null
    }

    var state by mutableStateOf(OrderState())
    private var job : Job? = null

    fun onUpdateDescription(description : String ){
        state = state.copy(
            description = description
        )
    }

    private fun onConfirmClick(navigator: DestinationsNavigator, context: Context){

        if(state.files.isEmpty() && state.records.isEmpty() && state.images.isEmpty() && state.description.isBlank()) {
            state = state.copy(makeOrdersError = context.getString(R.string.you_must_write_description))
            return
        }


        if(job == null){
            job = viewModelScope.launch(Dispatchers.IO) {

                state = state.copy(makeOrdersError = null)
                state = state.copy(isMakingOrderStep1 = true)

                val response = orderStep1UseCase(
                    (CoreViewModel.user?.token ?: ""),
                    MakeOrderStep1Input(
                        text = state.description,
                        imageParts = state.images,
                        fileParts = state.files,
                        recordParts = state.records,
                        lng = 0.0,
                        lat = 0.0
                    ),
                    context,
                )
                state = state.copy(isMakingOrderStep1 = false)

                if(response.failure != null) {
                    CoreViewModel.showSnackbar(("Error:" + response.failure.message))
                } else {

                    viewModelScope.launch(Dispatchers.Main) {
                        orderId = response.data!!.data.order.id
                        navigator.navigate(SelectLocationScreenDestination)
                    }
                }

            }
            job = null
        }

    }

    private fun onGetOrders(context: Context) {


        if(job == null){
            job = viewModelScope.launch(Dispatchers.IO) {
                state = state.copy(ordersError = null)

                state = state.copy(isOrdersLoading = true)

                val response = orderUseCase(
                    CoreViewModel.user!!.token,
                    context = context
                )

                state = state.copy(isOrdersLoading = false)

                if(response.failure != null) {
                    state = state.copy(ordersError = response.failure.message)
                }else {
                    state = state.copy(orders = response.data!!.data.orders)
                }


            }
            job = null
        }




    }

    fun onFileSelection(uri : Uri?){
        Log.v("onImageSelection",state.files.toString())

        if(uri != null){
            state = state.copy(
                files = state.files.plus(uri)
            )
        }
    }

    fun onImageSelection(uri : Uri?){

        Log.v("onImageSelection",state.images.toString())

        if(uri != null){
            state = state.copy(
                images = state.images.plus(uri)
            )
        }
    }

    fun onRecording(){

    }


    fun onEvent(event : OrderEvent) {
        when(event){
            is OrderEvent.OnConfirmClick -> {
                onConfirmClick(event.navigator,event.context)
            }
            is OrderEvent.OnGetOrders -> {
                onGetOrders(event.context)
            }
        }
    }



}