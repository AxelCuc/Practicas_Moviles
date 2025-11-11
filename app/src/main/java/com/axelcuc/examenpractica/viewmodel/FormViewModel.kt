package com.axelcuc.examenpractica.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.axelcuc.examenpractica.data.local.entity.FormEntry
import com.axelcuc.examenpractica.repository.FormRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FormViewModel(private val repository: FormRepository) : ViewModel() {

    private val _entries = MutableStateFlow<List<FormEntry>>(emptyList())
    val entries: StateFlow<List<FormEntry>> = _entries.asStateFlow()

    init {
        viewModelScope.launch {
            repository.allEntries.collect { list ->
                _entries.value = list
            }
        }
    }

    fun insertEntry(name: String, email: String, message: String) {
        viewModelScope.launch {
            val entry = FormEntry(name = name, email = email, message = message)
            repository.insertEntry(entry)
        }
    }

    fun deleteEntry(entryId: Int) {
        viewModelScope.launch {
            repository.deleteEntry(entryId)
        }
    }

    fun deleteAllEntries() {
        viewModelScope.launch {
            repository.deleteAllEntries()
        }
    }
}

class FormViewModelFactory(private val repository: FormRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FormViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}