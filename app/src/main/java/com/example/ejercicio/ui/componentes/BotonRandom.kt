package com.example.ejercicio.ui.componentes

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.ejercicio.R

@Composable
fun BotonRandom(eventoPadre: () -> Unit) {
    Button(onClick = {
        eventoPadre()
    }) {
        Text(stringResource(R.string.boton_random))
    }
}
