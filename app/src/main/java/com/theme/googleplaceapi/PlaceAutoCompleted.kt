package com.theme.googleplaceapi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesAutoComplete(
    onPlaceSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val fusedLocationProviderClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val placesClient = remember { Places.createClient(context) }

    var query = remember { mutableStateOf("") }
    var predictions = remember { mutableStateOf<List<AutocompletePrediction>>(emptyList()) }

    Scaffold(
        topBar = {
            TopAppBar(title = {Text(text = "Google Pleace")})
        }
    ) {paddingValue ->
        Column(modifier = Modifier.padding(paddingValue).padding(horizontal = 20.dp)) {
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
                    Text(
                        text = predictions.value[position].getPrimaryText(null).toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onPlaceSelected(predictions.value[position].placeId)
                            }
                            .padding(16.dp)
                    )
                }
            }
        }
    }


}
