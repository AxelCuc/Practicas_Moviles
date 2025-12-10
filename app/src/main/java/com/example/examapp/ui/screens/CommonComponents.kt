package com.example.examapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

// Tarjeta reutilizable para la lista
@Composable
fun MovieItem(
    title: String,
    poster: String,
    year: String,
    onItemClick: () -> Unit,
    icon: ImageVector, // Corazón o Basura
    onIconClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            // Imagen con Coil
            AsyncImage(
                model = poster,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                error = rememberVectorPainter(Icons.Default.BrokenImage) // Icono si falla carga
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.titleMedium, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = "Año: $year", style = MaterialTheme.typography.bodySmall)
            }

            IconButton(onClick = onIconClick) {
                Icon(imageVector = icon, contentDescription = "Acción", tint = Color.Red)
            }
        }
    }
}

// Pantalla de Error / Offline
@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "⚠️ $message", modifier = Modifier.padding(16.dp))
            Button(onClick = onRetry) {
                Text("Reintentar")
            }
        }
    }
}

// Helper para el placeholder de imagen rota
@Composable
fun rememberVectorPainter(image: ImageVector) = androidx.compose.ui.graphics.vector.rememberVectorPainter(image)