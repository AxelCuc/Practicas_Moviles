package com.example.examapp.data.remote

import com.example.examapp.model.MovieDetail
import com.example.examapp.model.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    // Búsqueda paginada: ?s=terminator&page=1&apikey=xyz
    @GET("/")
    suspend fun searchMovies(
        @Query("s") query: String,
        @Query("page") page: Int,
        @Query("apikey") apiKey: String
    ): MovieSearchResponse

    // Detalle: ?i=tt12345&apikey=xyz
    @GET("/")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String
    ): MovieDetail
}