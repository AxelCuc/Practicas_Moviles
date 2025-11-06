package com.axelcuc.cuartitosapp.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.axelcuc.cuartitosapp.domain.models.Student
import com.axelcuc.cuartitosapp.viewmodel.StudentViewModel



@Composable
fun HomeView(navController: NavController, viewModel: StudentViewModel){
    val students = viewModel.getAllStudents()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("AddView")
                }
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(it)
                .padding(8.dp)
        ) {
            Column {
                students.forEach { it ->
                    Content(item = it, navController)
                }
            }

        }

    }

}





@Composable
fun Content(item: Student, navController: NavController) {
    Row(

        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(8.dp)
            .clickable {
                navController.navigate("details/${item.id}")
            }

    ) {
        Column(

        ){
            Text(item.name)
            Text(
                text = item.description,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.CenterEnd)
                .padding(horizontal = 8.dp)

        ) {
            Icon(
                modifier = Modifier
                    .clickable{
                        navController.navigate("edit/${item.id}")
                    },
                imageVector = Icons.Default.Edit, contentDescription = "")
        }

    }

}