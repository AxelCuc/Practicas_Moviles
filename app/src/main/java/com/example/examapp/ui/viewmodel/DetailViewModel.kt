package com.example.examapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examapp.data.local.FavoriteMovie
import com.example.examapp.data.repository.MovieRepository
import com.example.examapp.model.MovieDetail
import com.example.examapp.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _detailState = MutableStateFlow<UiState<MovieDetail>>(UiState.Loading)
    val detailState: StateFlow<UiState<MovieDetail>> = _detailState

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    fun loadMovieDetail(imdbId: String) {
        // 1. Observar si es favorito en local
        viewModelScope.launch {
            repository.isFavorite(imdbId).collect { isFav ->
                _isFavorite.value = isFav
            }
        }

        // 2. Cargar datos de la API
        viewModelScope.launch {
            _detailState.value = UiState.Loading
            try {
                val detail = repository.getMovieDetail(imdbId, Constants.API_KEY)
                _detailState.value = UiState.Success(detail)
            } catch (e: Exception) {
                _detailState.value = UiState.Error("No se pudo cargar detalle (Offline?)")
            }
        }
    }

    fun toggleFavorite(detail: MovieDetail) {
        viewModelScope.launch {
            if (_isFavorite.value) {
                repository.deleteFavorite(detail.imdbID)
            } else {
                val fav = FavoriteMovie(
                    imdbID = detail.imdbID,
                    title = detail.title,
                    year = detail.year,
                    type = "detail", // Dato genérico
                    poster = detail.poster
                )
                repository.insertFavorite(fav)
            }
        }
    }
}