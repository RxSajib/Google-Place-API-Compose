package com.theme.googleplaceapi.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.theme.googleplaceapi.PlacesAutoComplete

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            val context = LocalContext.current
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)){

                    PlacesAutoComplete(location = {lat, lng ->

                    }, onPlaceSelected = {

                    })
                }
            }
        }
    }
}