package com.axelcuc.coroutines_app.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController


@Composable
fun DashboardView(navController: NavController){
    Scaffold {
        DashboardContent(it, navController)
    }
}

@Composable
fun DashboardContent(paddingValues: PaddingValues, navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .wrapContentSize(Alignment.Center)
    ) {
        Button(
            onClick = {
                navController.navigate("ButtonsView")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = Color.Black
            )
        ) {
            Text("Ir a botones")
        }
    }
}