package com.example.examapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.examapp.data.local.AppDatabase
import com.example.examapp.data.remote.RetrofitClient
import com.example.examapp.data.repository.MovieRepository
import com.example.examapp.ui.screens.*
import com.example.examapp.ui.viewmodel.MovieViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inyección de dependencias manual (Rápido para examen)
        val database = AppDatabase.getDatabase(this)
        val repository = MovieRepository(RetrofitClient.api, database.movieDao())
        val factory = MovieViewModelFactory(repository)

        setContent {
            MaterialTheme {
                ExamAppNavigation(factory)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamAppNavigation(factory: MovieViewModelFactory) {
    val navController = rememberNavController()
    // Para saber en qué pantalla estamos (para pintar la barra inferior si quisiéramos)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Inicio") },
                    selected = currentRoute == "home",
                    onClick = { navController.navigate("home") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                    label = { Text("Favoritos") },
                    selected = currentRoute == "favorites",
                    onClick = { navController.navigate("favorites") }
                )
            }
        }
    ) { innerPadding ->
        // GRAFO DE NAVEGACIÓN
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            // Pantalla 1: Home
            composable("home") {
                // Usamos viewModel() pasando la factory para obtener la instancia
                HomeScreen(viewModel = viewModel(factory = factory), navController = navController)
            }

            // Pantalla 2: Buscador
            composable("search") {
                // Instancia nueva o compartida, depende de la lógica. Aquí nueva para limpiar estado.
                SearchScreen(viewModel = viewModel(factory = factory), navController = navController)
            }

            // Pantalla 3: Favoritos
            composable("favorites") {
                FavoritesScreen(viewModel = viewModel(factory = factory), navController = navController)
            }

            // Pantalla 4: Detalle (recibe ID)
            composable(
                route = "detail/{imdbId}",
                arguments = listOf(navArgument("imdbId") { type = NavType.StringType })
            ) { backStackEntry ->
                val imdbId = backStackEntry.arguments?.getString("imdbId") ?: ""
                DetailScreen(imdbId = imdbId, viewModel = viewModel(factory = factory), navController = navController)
            }
        }
    }
}