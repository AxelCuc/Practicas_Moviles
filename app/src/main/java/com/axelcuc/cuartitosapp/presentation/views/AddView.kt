package com.axelcuc.cuartitosapp.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.axelcuc.cuartitosapp.domain.models.Student
import com.axelcuc.cuartitosapp.viewmodel.StudentViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navControler: NavController, viewModel: StudentViewModel){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Agregar estudiante")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {navControler.navigate("DashboardView")}
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {

        ContentAdd(it,viewModel)
    }
}


@Composable
fun ContentAdd(paddingValues: PaddingValues, viewModel: StudentViewModel){
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imgURL by remember { mutableStateOf("") }
    var students = mutableListOf<Student>()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Nombre")
            }
        )
        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
            },
            label = {
                Text("Descripcion")
            }
        )
        OutlinedTextField(
            value = imgURL,
            onValueChange = {
                imgURL = it
            },
            label = {
                Text("ImgURL")
            }
        )

        Button(
            onClick = {
                viewModel.addStudent(name,description,imgURL)
                name = ""
                description = ""
                imgURL = ""
            }
        ) {
            Text("Guardar")
        }
    }

}