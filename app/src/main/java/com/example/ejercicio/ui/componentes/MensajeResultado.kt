package com.example.ejercicio.ui.componentes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MensajeResultado(texto: String, color: Color) {
    Text(
        text = texto,
        color = color
    )
}
