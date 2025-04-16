package com.theme.googleplaceapi

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.theme.googleplaceapi.ui.theme.GooglePlaceAPITheme
import androidx.core.net.toUri
import com.theme.googleplaceapi.navigation.Navigation

private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GooglePlaceAPITheme {
                Navigation()
            }
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun openPlaceInGoogleMaps(context: Context, placeId: String) {
        val gmmIntentUri =
            "https://www.google.com/maps/search/?api=1&query=place_id:$placeId".toUri()
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps") // Google Maps অ্যাপ ওপেন করবে

        if (mapIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(mapIntent)
        } else {
            Toast.makeText(context, "Google Maps অ্যাপ পাওয়া যায়নি!", Toast.LENGTH_SHORT).show()
        }
    }
}

