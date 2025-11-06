package com.axelcuc.cuartitosapp

import com.axelcuc.cuartitosapp.ui.theme.CuartitosAppTheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.axelcuc.cuartitosapp.viewmodel.StudentViewModel
import com.axelcuc.cuartitosapp.presentation.navegation.NavManager
import com.axelcuc.cuartitosapp.ui.theme.CuartitosAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val studentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            CuartitosAppTheme {
                NavManager(studentViewModel)
            }
        }
    }
}