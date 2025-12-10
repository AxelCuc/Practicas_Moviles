package com.example.examapp.data.repository

import com.example.examapp.data.local.MovieDao
import com.example.examapp.data.local.FavoriteMovie
import com.example.examapp.data.remote.MovieApi
import com.example.examapp.model.MovieDetail
import com.example.examapp.model.MovieSearchResponse
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val api: MovieApi, private val dao: MovieDao) {

    // --- REMOTO (API) ---
    // Busca películas (paginado)
    suspend fun searchMovies(query: String, page: Int, apiKey: String): MovieSearchResponse {
        return api.searchMovies(query, page, apiKey)
    }

    // Detalle de película
    suspend fun getMovieDetail(id: String, apiKey: String): MovieDetail {
        return api.getMovieDetail(id, apiKey)
    }

    // --- LOCAL (ROOM - Favoritos) ---
    val allFavorites: Flow<List<FavoriteMovie>> = dao.getAllFavorites()

    suspend fun insertFavorite(movie: FavoriteMovie) {
        dao.insertFavorite(movie)
    }

    suspend fun deleteFavorite(id: String) {
        dao.deleteFavorite(id)
    }

    fun isFavorite(id: String): Flow<Boolean> {
        return dao.isFavorite(id)
    }
}