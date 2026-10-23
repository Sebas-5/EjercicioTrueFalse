package com.example.ejercicio.ui.pantallas

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejercicio.R
import com.example.ejercicio.ui.theme.Dimens

@Composable
fun PantallaEstadisticas(innerPadding: PaddingValues) {
    val context = LocalContext.current
    val preferencias = context.getSharedPreferences(stringResource(R.string.preferencias_nombre), Context.MODE_PRIVATE)
    val aciertos = preferencias.getInt(stringResource(R.string.clave_aciertos), 0)
    val fallos = preferencias.getInt(stringResource(R.string.clave_fallos), 0)
    val clicks = preferencias.getInt(stringResource(R.string.clave_clicks), 0)
    val total = aciertos + fallos
    val porcentajeAciertos = if (total == 0) 0 else aciertos * 100 / total
    val porcentajeFallos = if (total == 0) 0 else fallos * 100 / total

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.espacioPequeno, Alignment.CenterVertically)
    ) {
        Text(
            text = stringResource(R.string.estadisticas_titulo),
            fontSize = 28.sp,
            modifier = Modifier.padding(Dimens.paddingPantalla)
        )
        Text(text = stringResource(R.string.estadisticas_aciertos, aciertos))
        Text(text = stringResource(R.string.estadisticas_fallos, fallos))
        Text(text = stringResource(R.string.estadisticas_porcentaje_aciertos, porcentajeAciertos))
        Text(text = stringResource(R.string.estadisticas_porcentaje_fallos, porcentajeFallos))
        Text(text = stringResource(R.string.estadisticas_clicks, clicks))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPantallaEstadisticas() {
    PantallaEstadisticas(PaddingValues(0.dp))
}
