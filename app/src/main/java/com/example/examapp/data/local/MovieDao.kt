package com.example.examapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    // Devuelve un Flow para que la UI se actualice sola si cambia la DB
    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<FavoriteMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movie: FavoriteMovie)

    @Query("DELETE FROM favorites WHERE imdbID = :id")
    suspend fun deleteFavorite(id: String)

    // Útil para saber si pintar el corazón lleno o vacío
    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE imdbID = :id)")
    fun isFavorite(id: String): Flow<Boolean>
}