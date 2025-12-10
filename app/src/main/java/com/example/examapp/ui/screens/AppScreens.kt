package com.example.examapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // <--- FALTABA ESTO
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.examapp.ui.viewmodel.*

// --- 1. PANTALLA LANDING (Scroll Infinito) ---
@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    val state by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    // Lógica Scroll Infinito
    val isAtBottom by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            if (totalItems == 0) return@derivedStateOf false
            val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisible >= totalItems - 2
        }
    }

    LaunchedEffect(isAtBottom) {
        if (isAtBottom) viewModel.searchMovies("", reset = false)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("search") }) {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (val s = state) {
                is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is UiState.Error -> ErrorScreen(s.message) { viewModel.searchMovies("Marvel", true) }
                is UiState.Success -> {
                    LazyColumn(state = listState) {
                        items(s.data) { movie ->
                            MovieItem(
                                title = movie.title,
                                poster = movie.poster,
                                year = movie.year,
                                onItemClick = { navController.navigate("detail/${movie.imdbID}") },
                                icon = Icons.Default.FavoriteBorder,
                                onIconClick = { viewModel.toggleFavorite(movie) }
                            )
                        }
                        item {
                            Box(modifier = Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator(modifier = Modifier.size(24.dp))
                            }
                        }
                    }
                }
                else -> {}
            }
        }
    }
}

// --- 2. PANTALLA BUSCADOR ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: HomeViewModel, navController: NavController) {
    var query by remember { mutableStateOf("") }
    val state by viewModel.uiState.collectAsState()

    Column {
        TextField(
            value = query,
            onValueChange = {
                query = it
                if (it.length > 2) viewModel.searchMovies(it, reset = true)
            },
            label = { Text("Buscar artista/película...") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        when (val s = state) {
            is UiState.Success -> {
                LazyColumn {
                    items(s.data) { movie ->
                        MovieItem(
                            title = movie.title,
                            poster = movie.poster,
                            year = movie.year,
                            onItemClick = { navController.navigate("detail/${movie.imdbID}") },
                            icon = Icons.Default.FavoriteBorder,
                            onIconClick = { viewModel.toggleFavorite(movie) }
                        )
                    }
                }
            }
            is UiState.Loading -> LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            is UiState.Error -> Text("Sin resultados o Error", modifier = Modifier.padding(16.dp))
            else -> {}
        }
    }
}

// --- 3. PANTALLA FAVORITOS ---
@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel, navController: NavController) {
    val favorites by viewModel.favorites.collectAsState()

    if (favorites.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No tienes favoritos aún ❤️")
        }
    } else {
        LazyColumn {
            items(favorites) { fav ->
                MovieItem(
                    title = fav.title,
                    poster = fav.poster,
                    year = fav.year,
                    onItemClick = { navController.navigate("detail/${fav.imdbID}") },
                    icon = Icons.Default.Delete,
                    onIconClick = { viewModel.removeFavorite(fav.imdbID) }
                )
            }
        }
    }
}

// --- 4. PANTALLA DETALLE ---
@Composable
fun DetailScreen(imdbId: String, viewModel: DetailViewModel, navController: NavController) {
    val state by viewModel.detailState.collectAsState()
    val isFav by viewModel.isFavorite.collectAsState()

    LaunchedEffect(imdbId) {
        viewModel.loadMovieDetail(imdbId)
    }

    Scaffold(
        floatingActionButton = {
            if (state is UiState.Success) {
                FloatingActionButton(
                    onClick = { viewModel.toggleFavorite((state as UiState.Success).data) },
                    containerColor = if (isFav) Color.Red else MaterialTheme.colorScheme.primaryContainer
                ) {
                    Icon(
                        imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Fav",
                        tint = if (isFav) Color.White else Color.Black
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when (val s = state) {
                is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is UiState.Error -> ErrorScreen(s.message) { viewModel.loadMovieDetail(imdbId) }
                is UiState.Success -> {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        AsyncImage(
                            model = s.data.poster,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth().height(300.dp),
                            contentScale = ContentScale.Crop
                        )
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = s.data.title, style = MaterialTheme.typography.headlineMedium)
                            Text(text = "Año: ${s.data.year} | Rating: ${s.data.rating}", style = MaterialTheme.typography.labelLarge)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Género: ${s.data.genre}", style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = s.data.plot, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
                else -> {}
            }
        }
    }
}