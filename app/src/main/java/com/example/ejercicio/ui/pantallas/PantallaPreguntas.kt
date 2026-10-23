package com.example.ejercicio.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejercicio.R
import com.example.ejercicio.model.preguntas
import com.example.ejercicio.ui.theme.Dimens
import com.example.ejercicio.ui.theme.RojoMalo
import com.example.ejercicio.ui.theme.VerdeAcierto

@Composable
fun PantallaPreguntas( innerPadding : PaddingValues) {
    var indice by remember { mutableStateOf(0) }
    val textoFallo = stringResource(R.string.mensaje_fallo, (!preguntas[indice].respuesta).toString(), preguntas[indice].respuesta.toString())
    val textoAcierto = stringResource(R.string.mensaje_acierto, preguntas[indice].respuesta.toString())
    var textoUsuario by remember { mutableStateOf<String>("") }


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)) {
        Text(text = preguntas[indice].enunciado)
        Image(
            painter = painterResource(id = preguntas[indice].imagen),
            contentDescription = ""
        )

        Text(
            text = textoUsuario,
            color = if (textoUsuario == textoAcierto) VerdeAcierto else RojoMalo
        )

        Row(){
            Button(onClick = {


                if (preguntas[indice].respuesta){
                    textoUsuario = textoAcierto
                } else {
                    textoUsuario = textoFallo
                }

            }, colors = ButtonColors(
                containerColor = VerdeAcierto,
                contentColor = ButtonDefaults.buttonColors().contentColor,
                disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor,
                disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor
            )  ){
                Text("True")
            }
            Button(onClick = {

                if(!preguntas[indice].respuesta){
                    textoUsuario = textoAcierto
                } else {
                    textoUsuario = textoFallo
                }
            }, colors = ButtonColors(
                containerColor = RojoMalo,
                contentColor = ButtonDefaults.buttonColors().contentColor,
                disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor,
                disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor
            )  )  {
                Text ("False")
            }
        }
        Button (onClick =  {
            if(indice == preguntas.size -1){
                indice = 0
            } else {
                indice++
            }
            textoUsuario = ""
            // text1.setString("")

        }){
            Text ("Siguiente papi")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPantallaPreguntas() {
    PantallaPreguntas(PaddingValues(0.dp))
}
