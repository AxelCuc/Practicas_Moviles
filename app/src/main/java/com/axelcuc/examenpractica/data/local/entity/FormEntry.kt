package com.axelcuc.examenpractica.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "form_entries")
data class FormEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val message: String,
    val timestamp: Long = System.currentTimeMillis()
)