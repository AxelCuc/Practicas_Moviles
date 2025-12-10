package com.example.examapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examapp.data.local.FavoriteMovie
import com.example.examapp.data.repository.MovieRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repository: MovieRepository) : ViewModel() {

    // Convertimos el Flow de Room directamente a StateFlow para Compose
    val favorites: StateFlow<List<FavoriteMovie>> = repository.allFavorites
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun removeFavorite(id: String) {
        viewModelScope.launch {
            repository.deleteFavorite(id)
        }
    }
}