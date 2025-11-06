package com.axelcuc.cuartitosapp.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.axelcuc.cuartitosapp.domain.models.Student
import com.axelcuc.cuartitosapp.viewmodel.StudentViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(navControler: NavController, viewModel: StudentViewModel, id: Int?){
    val student: Student? = viewModel.getStudent(id)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Text("detalles del estudiante")
                        Text("${student?.name}")
                    }

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
    ){
        ContentDetail(it,student)
    }

}

@Composable
fun ContentDetail(paddingValues: PaddingValues, student: Student?){
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        if (student != null){
            Text(student.name)
            Text(student.description)
        }
    }

}