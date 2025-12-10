package com.example.examapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examapp.data.local.FavoriteMovie
import com.example.examapp.data.repository.MovieRepository
import com.example.examapp.model.MovieRemote
import com.example.examapp.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    // Estado de la lista (Landing/Buscador)
    private val _uiState = MutableStateFlow<UiState<List<MovieRemote>>>(UiState.Idle)
    val uiState: StateFlow<UiState<List<MovieRemote>>> = _uiState

    // Lista acumulada para el scroll infinito
    private val currentList = mutableListOf<MovieRemote>()

    // Variables de control
    private var currentPage = 1
    private var currentQuery = "Marvel" // Búsqueda por defecto
    private var isLastPage = false
    private var isLoadingMore = false

    init {
        // Cargar datos iniciales
        searchMovies(currentQuery, reset = true)
    }

    fun searchMovies(query: String, reset: Boolean = false) {
        if (reset) {
            currentPage = 1
            currentList.clear()
            isLastPage = false
            _uiState.value = UiState.Loading
            currentQuery = query
        }

        if (isLastPage || isLoadingMore) return

        isLoadingMore = true

        viewModelScope.launch {
            try {
                // Llamada a la API
                val response = repository.searchMovies(currentQuery, currentPage, Constants.API_KEY)

                if (response.response == "True" && !response.search.isNullOrEmpty()) {
                    currentList.addAll(response.search)
                    _uiState.value = UiState.Success(currentList.toList())
                    currentPage++
                } else {
                    if (reset) {
                        // Si es la primera carga y falla/no hay resultados
                        _uiState.value = UiState.Error("No se encontraron resultados o fin de la lista.")
                    }
                    isLastPage = true
                }
            } catch (e: Exception) {
                // Manejo de error (ej. sin internet)
                if (reset) _uiState.value = UiState.Error("Error de conexión: ${e.localizedMessage}")
            } finally {
                isLoadingMore = false
            }
        }
    }

    // Función para manejar el toggle de favorito desde la lista
    fun toggleFavorite(movie: MovieRemote) {
        viewModelScope.launch {
            // Verificamos si ya es favorito (esto es simplificado, idealmente observaríamos el Flow)
            // Por simplicidad en el examen, "agregamos" y manejamos la UI reactiva en el composable
            val fav = FavoriteMovie(
                imdbID = movie.imdbID,
                title = movie.title,
                year = movie.year,
                type = movie.type,
                poster = movie.poster
            )
            repository.insertFavorite(fav)
        }
    }
}