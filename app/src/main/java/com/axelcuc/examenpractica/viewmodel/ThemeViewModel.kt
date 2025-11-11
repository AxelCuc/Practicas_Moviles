package com.axelcuc.examenpractica.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.axelcuc.examenpractica.data.datastore.DataStoreDarkMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ThemeViewModel(private val dataStore: DataStoreDarkMode) : ViewModel() {

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    init {
        viewModelScope.launch {
            dataStore.isDarkMode.collect { isDark ->
                _isDarkMode.value = isDark
            }
        }
    }

    fun toggleTheme() {
        viewModelScope.launch {
            val newValue = !_isDarkMode.value
            dataStore.saveDarkMode(newValue)
        }
    }
}

class ThemeViewModelFactory(private val dataStore: DataStoreDarkMode) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThemeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThemeViewModel(dataStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}