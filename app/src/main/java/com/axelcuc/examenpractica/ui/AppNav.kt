package com.axelcuc.examenpractica.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.axelcuc.examenpractica.ui.screens.DashboardScreen
import com.axelcuc.examenpractica.ui.screens.FormScreen
import com.axelcuc.examenpractica.ui.screens.ThemeScreen
import com.axelcuc.examenpractica.viewmodel.FormViewModel
import com.axelcuc.examenpractica.viewmodel.FormViewModelFactory
import com.axelcuc.examenpractica.viewmodel.ThemeViewModel

@Composable
fun AppNav(
    themeViewModel: ThemeViewModel,
    formViewModelFactory: FormViewModelFactory
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            DashboardScreen(
                onNavigateToTheme = { navController.navigate("theme") },
                onNavigateToForm = { navController.navigate("form") }
            )
        }

        composable("theme") {
            ThemeScreen(
                themeViewModel = themeViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable("form") {
            val formViewModel: FormViewModel = viewModel(factory = formViewModelFactory)
            FormScreen(
                formViewModel = formViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}