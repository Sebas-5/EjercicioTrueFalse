package com.example.ejercicio.ui.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.ejercicio.R

@Composable
fun BotonNext(eventoPadre: () -> Unit) {
    Button(onClick = {
        eventoPadre()
    }, modifier = Modifier.fillMaxWidth()) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = ""
        )
        Text(stringResource(R.string.boton_next))
    }
}
