package com.axelcuc.coroutines_app.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.axelcuc.coroutines_app.presentation.viewmodel.CoroutinesViewModel
import kotlin.getValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonsView(navController: NavController, viewModel: CoroutinesViewModel){
    Scaffold(
        topBar ={
            CenterAlignedTopAppBar(
                title = {
                    Text("Practica de Coroutines")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        ContentButons(it, viewModel)
    }
}

@Composable
fun ContentButons(paddingValues: PaddingValues, viewModel: CoroutinesViewModel){
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .height(400.dp)
            .fillMaxWidth(),
        horizontalAlignment =
            Alignment.CenterHorizontally,
        verticalArrangement =
            Arrangement.Center
    ) {
        Row(){
            Button(
                onClick = {
                    viewModel.callToApi()
                }
            ) {
                Text("Click 1")
            }
            if (viewModel.isLoading.value) CircularProgressIndicator()
            if (viewModel.result.value.isNotEmpty() && !viewModel.isLoading.value) {
                Text(viewModel.result.value)
            }
        }
        Row {
            Button(
                onClick = {
                    viewModel.callToApi2()
                }
            ) {
                Text("Click 2")
            }
            if (viewModel.isLoading2.value) CircularProgressIndicator()
            if (viewModel.result2.value.isNotEmpty() && !viewModel.isLoading2.value) {
                Text(viewModel.result2.value)
            }
        }
        Row {
            Button(
                onClick = {
                    viewModel.callToApi3()
                }
            ) {
                Text("Click 3")
            }
            if (viewModel.isLoading3.value) CircularProgressIndicator()
            if (viewModel.result3.value.isNotEmpty() && !viewModel.isLoading3.value) {
                Text(viewModel.result3.value)
            }
        }
    }
}