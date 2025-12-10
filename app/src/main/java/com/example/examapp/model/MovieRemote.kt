package com.example.examapp.model

import com.google.gson.annotations.SerializedName

// Modelo para un item de la lista (Búsqueda)
data class MovieRemote(
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String
)

// Modelo para la respuesta de la lista (contiene la lista y metadatos)
data class MovieSearchResponse(
    @SerializedName("Search") val search: List<MovieRemote>?,
    @SerializedName("totalResults") val totalResults: String?,
    @SerializedName("Response") val response: String
)

// Modelo para el detalle completo (cuando pides info específica)
data class MovieDetail(
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("imdbRating") val rating: String
)