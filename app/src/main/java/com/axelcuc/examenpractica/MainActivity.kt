package com.axelcuc.examenpractica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.axelcuc.examenpractica.data.datastore.DataStoreDarkMode
import com.axelcuc.examenpractica.data.local.AppDatabase
import com.axelcuc.examenpractica.repository.FormRepository
import com.axelcuc.examenpractica.ui.AppNav
import com.axelcuc.examenpractica.ui.theme.ExamenAppTheme
import com.axelcuc.examenpractica.viewmodel.FormViewModelFactory
import com.axelcuc.examenpractica.viewmodel.ThemeViewModel
import com.axelcuc.examenpractica.viewmodel.ThemeViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataStore = DataStoreDarkMode(applicationContext)
        val database = AppDatabase.getDatabase(applicationContext)
        val formRepository = FormRepository(database.formDao())

        val themeViewModelFactory = ThemeViewModelFactory(dataStore)
        val formViewModelFactory = FormViewModelFactory(formRepository)

        setContent {
            val themeViewModel: ThemeViewModel = viewModel(factory = themeViewModelFactory)
            val isDarkMode by themeViewModel.isDarkMode.collectAsState()

            ExamenAppTheme(darkTheme = isDarkMode) {
                AppNav(
                    themeViewModel = themeViewModel,
                    formViewModelFactory = formViewModelFactory
                )
            }
        }
    }
}