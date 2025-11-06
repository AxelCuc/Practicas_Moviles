package com.axelcuc.coroutines_app.presentation.navegation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.axelcuc.coroutines_app.presentation.viewmodel.CoroutinesViewModel
import com.axelcuc.coroutines_app.presentation.views.ButtonsView
import com.axelcuc.coroutines_app.presentation.views.DashboardView


@Composable
fun NavManager(viewModel: CoroutinesViewModel){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "DashboardView"
    ){
        composable(
            "DashboardView"
        ){
            DashboardView(navController)
        }

        composable(
            "ButtonsView"
        ){
            ButtonsView(navController, viewModel)
        }

    }
}