package com.axelcuc.botonesapp.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.axelcuc.botonesapp.presentation.components.CustomFloatingActionButton
import com.axelcuc.botonesapp.presentation.components.NormalButton


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun DetailsView(navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("Details A")},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        DetailsContent(it)
    }
}

@Composable
fun DetailsContent(innerPaddingValues: PaddingValues){
    Column(
        modifier = Modifier
            .padding(innerPaddingValues)
    ) {
        Text("Hola mundo")
    }
}