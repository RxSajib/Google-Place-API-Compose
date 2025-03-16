package com.theme.googleplaceapi

import android.util.Log
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient

private const val TAG = "PlacePredictions"
fun fetchPlacePredictions(
    query: String,
    placesClient: PlacesClient,
    onResult: (List<AutocompletePrediction>) -> Unit
) {
    if (query.isEmpty()) {
        onResult(emptyList())
        return
    }

    val request = FindAutocompletePredictionsRequest.builder()
        .setQuery(query)
        .build()

    placesClient.findAutocompletePredictions(request)
        .addOnSuccessListener { response ->
            onResult(response.autocompletePredictions)
        }
        .addOnFailureListener { exception ->
            Log.e("PlacesAutoComplete", "Error fetching predictions: ", exception)
        }
}
