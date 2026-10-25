package com.example.ejercicio.ui.pantallas

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejercicio.R
import com.example.ejercicio.model.preguntas
import com.example.ejercicio.ui.componentes.BotonNext
import com.example.ejercicio.ui.componentes.BotonPrev
import com.example.ejercicio.ui.componentes.BotonRandom
import com.example.ejercicio.ui.componentes.BotonRespuesta
import com.example.ejercicio.ui.componentes.MensajeResultado
import com.example.ejercicio.ui.theme.Dimens
import com.example.ejercicio.ui.theme.RojoMalo
import com.example.ejercicio.ui.theme.VerdeAcierto

@Composable
fun PantallaPreguntas(
    innerPadding: PaddingValues,
    modoExamen: Boolean,
    modoSimple: Boolean,
    eventoVolver: () -> Unit
) {
    var indice by remember { mutableStateOf(0) }
    val textoFallo = stringResource(R.string.mensaje_fallo, (!preguntas[indice].respuesta).toString(), preguntas[indice].respuesta.toString())
    val textoAcierto = stringResource(R.string.mensaje_acierto, preguntas[indice].respuesta.toString())
    var textoUsuario by remember { mutableStateOf<String>("") }
    var mostrarDialogo by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val preferencias = context.getSharedPreferences(stringResource(R.string.preferencias_nombre), Context.MODE_PRIVATE)
    val claveAciertos = stringResource(R.string.clave_aciertos)
    val claveFallos = stringResource(R.string.clave_fallos)
    val claveClicks = stringResource(R.string.clave_clicks)
    var aciertos by remember { mutableStateOf(if (modoExamen) 0 else preferencias.getInt(claveAciertos, 0)) }
    var fallos by remember { mutableStateOf(if (modoExamen) 0 else preferencias.getInt(claveFallos, 0)) }
    var clicks by remember { mutableStateOf(if (modoExamen) 0 else preferencias.getInt(claveClicks, 0)) }

    BackHandler {
        if (!modoExamen) {
            preferencias.edit()
                .putInt(claveAciertos, aciertos)
                .putInt(claveFallos, fallos)
                .putInt(claveClicks, clicks)
                .apply()
        }
        eventoVolver()
    }

    val colorTrue = if (preguntas[indice].respuesta || textoUsuario == "") VerdeAcierto else RojoMalo
    val colorFalse = if (!preguntas[indice].respuesta && textoUsuario != "") VerdeAcierto else RojoMalo
    val botonesActivos = !(modoExamen && textoUsuario != "")

    val porcentaje = aciertos * 100 / preguntas.size
    val imagenResultado = if (porcentaje == 100) R.drawable.imagen1 else if (porcentaje >= 50) R.drawable.imagen5 else R.drawable.imagen2
    val textoResultado = if (porcentaje == 100) {
        stringResource(R.string.examen_motivador, aciertos, preguntas.size)
    } else if (porcentaje >= 50) {
        stringResource(R.string.examen_normal, aciertos, preguntas.size)
    } else {
        stringResource(R.string.examen_insulto, aciertos, preguntas.size)
    }

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
        if (modoSimple) {
            LinearProgressIndicator(
                progress = { (indice + 1).toFloat() / preguntas.size },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.paddingPantalla)
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            if (!modoSimple) {
                Image(
                    painter = painterResource(id = preguntas[indice].imagen),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        }

        MensajeResultado(
            texto = textoUsuario,
            color = if (textoUsuario == textoAcierto) VerdeAcierto else RojoMalo
        )

        Row(horizontalArrangement = Arrangement.spacedBy(Dimens.espacioPequeno)) {
            BotonRespuesta(
                texto = stringResource(R.string.boton_true),
                color = colorTrue,
                activo = botonesActivos,
                eventoPadre = {
                    clicks++
                    if (preguntas[indice].respuesta) {
                        aciertos++
                        textoUsuario = textoAcierto
                    } else {
                        fallos++
                        textoUsuario = textoFallo
                    }
                }
            )
            BotonRespuesta(
                texto = stringResource(R.string.boton_false),
                color = colorFalse,
                activo = botonesActivos,
                eventoPadre = {
                    clicks++
                    if (!preguntas[indice].respuesta) {
                        aciertos++
                        textoUsuario = textoAcierto
                    } else {
                        fallos++
                        textoUsuario = textoFallo
                    }
                }
            )
        }
        if (!modoExamen) {
            Row(horizontalArrangement = Arrangement.spacedBy(Dimens.espacioPequeno)) {
                BotonPrev(eventoPadre = {
                    clicks++
                    if (indice == 0) {
                        indice = preguntas.size - 1
                    } else {
                        indice--
                    }
                    textoUsuario = ""
                })
                BotonRandom(eventoPadre = {
                    clicks++
                    indice = (0..preguntas.size - 1).random()
                    textoUsuario = ""
                })
            }
        }
        BotonNext(eventoPadre = {
            clicks++
            if (indice == preguntas.size - 1) {
                if (modoExamen) {
                    mostrarDialogo = true
                } else {
                    indice = 0
                }
            } else {
                indice++
            }
            textoUsuario = ""
        })
    }

    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = {
                mostrarDialogo = false
                eventoVolver()
            },
            icon = {
                Image(
                    painter = painterResource(id = imagenResultado),
                    contentDescription = "",
                    modifier = Modifier.size(Dimens.tamanoImagenDialogo)
                )
            },
            title = { Text(stringResource(R.string.examen_titulo)) },
            text = { Text(textoResultado) },
            confirmButton = {
                TextButton(onClick = {
                    mostrarDialogo = false
                    eventoVolver()
                }) {
                    Text(stringResource(R.string.boton_menu))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    mostrarDialogo = false
                    aciertos = 0
                    indice = 0
                    textoUsuario = ""
                }) {
                    Text(stringResource(R.string.boton_repetir))
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPantallaPreguntas() {
    PantallaPreguntas(PaddingValues(0.dp), false, false, {})
}
