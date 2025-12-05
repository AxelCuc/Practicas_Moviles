package com.axelcuc.intents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.danielflores.recognitionapp.ui.theme.RecognitionAppTheme
import com.danielflores.recognitionapp.viewmodels.ScannerViewModel
import com.danielflores.recognitionapp.views.TabsView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: ScannerViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            RecognitionAppTheme {
                TabsView(viewModel)
            }
        }
    }
}