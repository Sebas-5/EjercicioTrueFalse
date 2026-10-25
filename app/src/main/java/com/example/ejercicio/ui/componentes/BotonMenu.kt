package com.example.ejercicio.ui.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ejercicio.ui.theme.Dimens

@Composable
fun BotonMenu(texto: String, eventoPadre: () -> Unit) {
    Button(
        onClick = {
            eventoPadre()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.paddingPantalla, vertical = Dimens.espacioPequeno)
    ) {
        Text(texto)
    }
}
