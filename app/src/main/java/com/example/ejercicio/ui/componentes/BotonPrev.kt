package com.example.ejercicio.ui.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.ejercicio.R

@Composable
fun BotonPrev(eventoPadre: () -> Unit) {
    Button(onClick = {
        eventoPadre()
    }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = ""
        )
        Text(stringResource(R.string.boton_prev))
    }
}
