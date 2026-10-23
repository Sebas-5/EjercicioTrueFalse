package com.example.ejercicio.ui.componentes

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BotonRespuesta(texto: String, color: Color, eventoPadre: () -> Unit) {
    Button(onClick = {
        eventoPadre()
    }, colors = ButtonColors(
        containerColor = color,
        contentColor = ButtonDefaults.buttonColors().contentColor,
        disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor,
        disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor
    )) {
        Text(texto)
    }
}
