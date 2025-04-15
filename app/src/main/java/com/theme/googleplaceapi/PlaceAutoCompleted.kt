package com.theme.googleplaceapi

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.theme.googleplaceapi.component.Location

private const val TAG = "PlaceAutoCompleted"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesAutoComplete(
    onPlaceSelected: (String) -> Unit,
    location: (Double, Double) -> Unit,
) {
    val context = LocalContext.current
    val fusedLocationProviderClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val placesClient = remember { Places.createClient(context) }

    val query = remember { mutableStateOf("") }
    val predictions = remember { mutableStateOf<List<AutocompletePrediction>>(emptyList()) }

    Scaffold(
        topBar = {
            TopAppBar(title = {Text(text = "Google Pleace")})
        }
    ) {paddingValue ->
        Column(modifier = Modifier
            .padding(paddingValue)
            .padding(horizontal = 20.dp)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = query.value,
                onValueChange = { newQuery ->
                    query.value = newQuery
                    fetchPlacePredictions(newQuery, placesClient) { predictions.value = it }
                },
                label = { Text("Search Places") }
            )

            LazyColumn {
                items(predictions.value.size) { position ->
                    Location(
                        locationTitle = predictions.value[position].getPrimaryText(null).toString(),
                        locationDetails = predictions.value[position].getFullText(null).toString(),
                        onClick = {
                            val placeFields = listOf(Place.Field.LAT_LNG, Place.Field.NAME)

                            val request = FetchPlaceRequest.builder(
                                predictions.value[position].placeId,
                                placeFields
                            ).build()

                            placesClient.fetchPlace(request)
                                .addOnSuccessListener { response ->
                                    val place = response.place
                                    val latLng = place.latLng
                                    Log.d(TAG, "PlacesAutoComplete: lat lng $latLng")
                                    latLng?.let {
                                        val locationString = "${it.latitude},${it.longitude}"
                                        val lat = it.latitude
                                        val lon = it.longitude
                                        onPlaceSelected(locationString)

                                        location.invoke(lat, lon)
                                    }
                                }
                                .addOnFailureListener { exception ->

                                }
                        }
                    )
                }
            }
        }
    }


}
