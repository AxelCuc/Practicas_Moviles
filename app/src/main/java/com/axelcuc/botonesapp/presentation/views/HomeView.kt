package com.axelcuc.botonesapp.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.axelcuc.botonesapp.presentation.components.CustomFloatingActionButton
import com.axelcuc.botonesapp.presentation.components.CustomIconButton
import com.axelcuc.botonesapp.presentation.components.CustomOutlineButton
import com.axelcuc.botonesapp.presentation.components.NormalButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Cuarto A")}
            )
        },
        floatingActionButton = {
            CustomFloatingActionButton()
        }
    ) {
        Content(it, navController)
    }
}

@Composable
fun Content(innerPaddingValues: PaddingValues, navController: NavController){
    Column(
        modifier = Modifier
            .padding(innerPaddingValues)
    ) {
        NormalButton("Click", onClick = {
            navController.navigate("Details")
        })
        NormalButton("Click 2")
        NormalButton("Click 3"){
            println("Emilia está grabando sin el consentimiento del maestro. Nos vemos en los tribunales")
        }
        //CustomOutlineButton()
        CustomIconButton()
    }
}