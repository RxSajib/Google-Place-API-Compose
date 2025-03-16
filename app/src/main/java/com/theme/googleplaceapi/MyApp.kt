package com.theme.googleplaceapi

import android.app.Application
import com.google.android.libraries.places.api.Places

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if(!Places.isInitialized()){
            Places.initialize(applicationContext, "AIzaSyDXJNhJI9Qtfgap9aozUd8-oZ2bg5ky1h8")
        }
    }
}