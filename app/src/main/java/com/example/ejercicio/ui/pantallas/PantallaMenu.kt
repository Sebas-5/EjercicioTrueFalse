package com.example.ejercicio.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejercicio.R
import com.example.ejercicio.ui.componentes.BotonMenu
import com.example.ejercicio.ui.theme.Dimens

@Composable
fun PantallaMenu(
    innerPadding: PaddingValues,
    eventoJugar: () -> Unit,
    eventoExamen: () -> Unit,
    eventoSimple: () -> Unit,
    eventoEstadisticas: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.espacioPequeno, Alignment.CenterVertically)
    ) {
        Text(
            text = stringResource(R.string.menu_titulo),
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(Dimens.paddingPantalla)
        )
        BotonMenu(
            texto = stringResource(R.string.boton_jugar),
            eventoPadre = eventoJugar
        )
        BotonMenu(
            texto = stringResource(R.string.boton_examen),
            eventoPadre = eventoExamen
        )
        BotonMenu(
            texto = stringResource(R.string.boton_simple),
            eventoPadre = eventoSimple
        )
        BotonMenu(
            texto = stringResource(R.string.boton_estadisticas),
            eventoPadre = eventoEstadisticas
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPantallaMenu() {
    PantallaMenu(PaddingValues(0.dp), {}, {}, {}, {})
}
