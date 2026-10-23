package com.example.ejercicio.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejercicio.R
import com.example.ejercicio.model.preguntas
import com.example.ejercicio.ui.componentes.BotonNext
import com.example.ejercicio.ui.componentes.BotonRespuesta
import com.example.ejercicio.ui.componentes.MensajeResultado
import com.example.ejercicio.ui.theme.Dimens
import com.example.ejercicio.ui.theme.RojoMalo
import com.example.ejercicio.ui.theme.VerdeAcierto

@Composable
fun PantallaPreguntas( innerPadding : PaddingValues) {
    var indice by remember { mutableStateOf(0) }
    val textoFallo = stringResource(R.string.mensaje_fallo, (!preguntas[indice].respuesta).toString(), preguntas[indice].respuesta.toString())
    val textoAcierto = stringResource(R.string.mensaje_acierto, preguntas[indice].respuesta.toString())
    var textoUsuario by remember { mutableStateOf<String>("") }

    // Mientras no haya mensaje, los botones tienen su color normal
    val colorNormal = ButtonDefaults.buttonColors().containerColor
    val colorTrue = if (textoUsuario == "") colorNormal else if (preguntas[indice].respuesta) VerdeAcierto else RojoMalo
    val colorFalse = if (textoUsuario == "") colorNormal else if (!preguntas[indice].respuesta) VerdeAcierto else RojoMalo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.espacioPequeno)
    ) {
        Text(
            text = preguntas[indice].enunciado,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.paddingPantalla)
        )
        Image(
            painter = painterResource(id = preguntas[indice].imagen),
            contentDescription = "",
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )

        MensajeResultado(
            texto = textoUsuario,
            color = if (textoUsuario == textoAcierto) VerdeAcierto else RojoMalo
        )

        Row(horizontalArrangement = Arrangement.spacedBy(Dimens.espacioPequeno)) {
            BotonRespuesta(
                texto = stringResource(R.string.boton_true),
                color = colorTrue,
                eventoPadre = {
                    if (preguntas[indice].respuesta) {
                        textoUsuario = textoAcierto
                    } else {
                        textoUsuario = textoFallo
                    }
                }
            )
            BotonRespuesta(
                texto = stringResource(R.string.boton_false),
                color = colorFalse,
                eventoPadre = {
                    if (!preguntas[indice].respuesta) {
                        textoUsuario = textoAcierto
                    } else {
                        textoUsuario = textoFallo
                    }
                }
            )
        }
        BotonNext(eventoPadre = {
            if (indice == preguntas.size - 1) {
                indice = 0
            } else {
                indice++
            }
            textoUsuario = ""
        })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPantallaPreguntas() {
    PantallaPreguntas(PaddingValues(0.dp))
}
