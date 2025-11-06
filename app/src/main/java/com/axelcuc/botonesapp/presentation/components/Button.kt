package com.axelcuc.botonesapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun NormalButton(
    title: String,
    onClick: (() -> Unit)? = null

){
    if (onClick == null){
        Button(
            onClick = {
                println("Hola mundo")
            }
        ) {
            Text(title)
        }
        return
    }
    Button(
        onClick = onClick
    ) {
        Text(title)
    }


}

@Composable
fun CustomOutlineButton(){
    OutlinedIconButton(
        onClick = {},
//        Modifier.background(color = Color.Magenta, ShapeDefaults.Small)
    ) {
        Text("Outlined")
    }
}

@Composable
fun CustomIconButton(){
    IconButton(
        onClick = {}
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = ""
        )
    }
}

@Composable
fun CustomFloatingActionButton(){
    FloatingActionButton(
        onClick = {}
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = ""
        )
    }
}