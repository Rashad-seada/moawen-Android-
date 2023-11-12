package com.example.marketapp.features.order.infrastructure.services

import android.content.Context
import com.example.marketapp.R
import com.example.marketapp.core.errors.Failure
import com.example.marketapp.core.util.Resource
import com.example.marketapp.features.order.infrastructure.services.utils.PlaceInfo
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.maps.DirectionsApi
import com.google.maps.GeoApiContext
import com.google.maps.model.TravelMode
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onCompletion


class PlacesService(
    private val placesClient: PlacesClient,
    private val directionsClient: GeoApiContext,
    private val context: Context
) {

    fun getDirectionsFlow(
        origin: String, // "latitude,longitude"
        destination: String, // "latitude,longitude"
        mode: TravelMode = TravelMode.DRIVING
    ): Flow<Resource<List<LatLng>>> = callbackFlow {
        try {
            val result = DirectionsApi.newRequest(directionsClient)
                .mode(mode)
                .origin(origin)
                .destination(destination)
                .await()

            val coordinatesList: List<LatLng> =
                result.routes.first().legs.flatMap { leg -> leg.steps.flatMap { step -> step.polyline.decodePath() } }
                    .map { latLng -> LatLng(latLng.lat, latLng.lng) }


            trySend(Resource.SuccessData(coordinatesList))
        } catch (e: Exception) {

            trySend(
                Resource.FailureData(

                    Failure(
                        message = e.message.toString(),
                        screenIdInt = 0,
                        exceptionCode = 0,
                        customCode = 0
                    )

                )
            )

        }
        close() // Close the channel when the task is completed

        awaitClose {
            return@awaitClose
        }


    }

    fun searchPlaces(query: String): Flow<Resource<List<PlaceInfo>>> = callbackFlow {
        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .build()

        val listener = placesClient.findAutocompletePredictions(request)

        listener.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val response = task.result

                val placeInfoList = response.autocompletePredictions.map {
                    PlaceInfo(
                        it.placeId,
                        it.getPrimaryText(null).toString(),
                        it.getSecondaryText(null).toString()
                    )
                }

                // Emit a success resource
                trySend(Resource.SuccessData(placeInfoList))
            } else {
                // Emit a failure resource
                val exception = task.exception
                val resource = if (exception != null) {
                    Resource.FailureData<List<PlaceInfo>>(
                        Failure(
                            message = context.getString(R.string.server_is_down),
                            screenIdInt = 0,
                            exceptionCode = 0,
                            customCode = 0
                        )
                    )
                } else {
                    Resource.FailureData(
                        Failure(
                            message = "Failed to retrieve place predictions",
                            screenIdInt = 0,
                            exceptionCode = 0,
                            customCode = 0
                        )
                    )
                }
                trySend(resource)
            }
            close() // Close the channel when the task is completed
        }

        // Handle task cancellation
        awaitClose {
            return@awaitClose
        }

    }


    fun getLatLngForPlace(placeId: String): Flow<Resource<LatLng?>> = callbackFlow {


        val placeFields = listOf(Place.Field.LAT_LNG)
        val request = FetchPlaceRequest.builder(placeId, placeFields).build()

        val listener = placesClient.fetchPlace(request)

        listener.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val response = task.result

                val placeInfoList = response.place.latLng

                // Emit a success resource
                trySend(Resource.SuccessData(placeInfoList))
            } else {
                // Emit a failure resource
                val exception = task.exception
                val resource = if (exception != null) {
                    Resource.FailureData<LatLng>(
                        Failure(
                            message = context.getString(R.string.server_is_down),
                            screenIdInt = 0,
                            exceptionCode = 0,
                            customCode = 0
                        )
                    )
                } else {
                    Resource.FailureData<LatLng>(
                        Failure(
                            message = "Failed to retrieve place predictions",
                            screenIdInt = 0,
                            exceptionCode = 0,
                            customCode = 0
                        )
                    )
                }
                trySend(resource)
            }
            close() // Close the channel when the task is completed

        }
        awaitClose {
            return@awaitClose
        }


    }.onCompletion { cause ->
        if (cause != null) {

            // Handle any exceptions or cancellation here
        }
    }
}
