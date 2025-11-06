package com.axelcuc.botonesapp.presentation.NavManager

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.axelcuc.botonesapp.presentation.views.DetailsView
import com.axelcuc.botonesapp.presentation.views.HomeView


@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable("Home") {
            HomeView(navController)
        }
        composable("Details") {
            DetailsView(navController)
        }
    }
}