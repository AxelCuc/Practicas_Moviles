package com.axelcuc.cuartitosapp.presentation.navegation

import com.axelcuc.cuartitosapp.presentation.views.AddView
import com.axelcuc.cuartitosapp.presentation.views.HomeView


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.axelcuc.cuartitosapp.presentation.views.DetailView
import com.axelcuc.cuartitosapp.presentation.views.EditView
import com.axelcuc.cuartitosapp.viewmodel.StudentViewModel


@Composable
fun NavManager(viewModel: StudentViewModel){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "DashboardView"
    ){
        composable(
            "DashboardView"
        ) {
            HomeView(navController, viewModel)
        }

        composable(
            "AddView"
        ){
            AddView(navController, viewModel)
        }

        composable(
            "details/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            DetailView(navController,viewModel,id)
        }

        composable(
            "edit/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            EditView(navController,viewModel,id)
        }


    }
}