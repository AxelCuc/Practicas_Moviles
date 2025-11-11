package com.axelcuc.examenpractica.repository

import com.axelcuc.examenpractica.data.local.dao.FormDao
import com.axelcuc.examenpractica.data.local.entity.FormEntry
import kotlinx.coroutines.flow.Flow

class FormRepository(private val formDao: FormDao) {

    val allEntries: Flow<List<FormEntry>> = formDao.getAllEntries()

    suspend fun insertEntry(entry: FormEntry) {
        formDao.insertEntry(entry)
    }

    suspend fun deleteEntry(entryId: Int) {
        formDao.deleteEntry(entryId)
    }

    suspend fun deleteAllEntries() {
        formDao.deleteAllEntries()
    }
}