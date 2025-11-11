package com.axelcuc.examenpractica.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.axelcuc.examenpractica.data.local.entity.FormEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface FormDao {

    @Query("SELECT * FROM form_entries ORDER BY timestamp DESC")
    fun getAllEntries(): Flow<List<FormEntry>>

    @Insert
    suspend fun insertEntry(entry: FormEntry)

    @Query("DELETE FROM form_entries WHERE id = :entryId")
    suspend fun deleteEntry(entryId: Int)

    @Query("DELETE FROM form_entries")
    suspend fun deleteAllEntries()
}