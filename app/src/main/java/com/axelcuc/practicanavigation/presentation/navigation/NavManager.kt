package com.axelcuc.practicanavigation.presentation.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.axelcuc.practicanavigation.presentation.views.DetailsView
import com.axelcuc.practicanavigation.presentation.views.HomeView

@Composable
fun NavManager(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable(
            "Home"
        ) { HomeView(navController) }

        composable(
            "Details/{name}", arguments = listOf(
                navArgument(name = "name"){
                    type = NavType.StringType
                }
            )
        ) {
            val name = it.arguments?.getString("name")?:"nada"
            DetailsView(navController, name)
        }
    }
}