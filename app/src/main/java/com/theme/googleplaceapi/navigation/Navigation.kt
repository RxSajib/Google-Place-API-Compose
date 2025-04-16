package com.theme.googleplaceapi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.theme.googleplaceapi.ui.HomeScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val controller  = rememberNavController()
    NavHost(navController = controller, startDestination = HomeScreen){
        composable<HomeScreen> {
            HomeScreen()
        }
    }
}