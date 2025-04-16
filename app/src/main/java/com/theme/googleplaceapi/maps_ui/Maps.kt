package com.theme.googleplaceapi.maps_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap

@Composable
fun Maps(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()){
        GoogleMap(modifier = Modifier.fillMaxSize())
    }

}