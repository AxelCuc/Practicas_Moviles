package com.example.examapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteMovie(
    @PrimaryKey val imdbID: String, // El ID es único
    val title: String,
    val year: String,
    val type: String,
    val poster: String
)