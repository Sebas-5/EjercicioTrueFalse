package com.example.ejercicio.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.ejercicio.ui.pantallas.PantallaEstadisticas
import com.example.ejercicio.ui.pantallas.PantallaMenu
import com.example.ejercicio.ui.pantallas.PantallaPreguntas

@Composable
fun Navegacion(innerPadding: PaddingValues) {
    val backStack = rememberNavBackStack(Menu)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Menu> {
                PantallaMenu(
                    innerPadding = innerPadding,
                    eventoJugar = { backStack.add(Juego) },
                    eventoExamen = { backStack.add(Examen) },
                    eventoSimple = { backStack.add(Simple) },
                    eventoEstadisticas = { backStack.add(Estadisticas) }
                )
            }
            entry<Juego> {
                PantallaPreguntas(
                    innerPadding = innerPadding,
                    modoExamen = false,
                    modoSimple = false,
                    eventoVolver = { backStack.removeLastOrNull() }
                )
            }
            entry<Examen> {
                PantallaPreguntas(
                    innerPadding = innerPadding,
                    modoExamen = true,
                    modoSimple = false,
                    eventoVolver = { backStack.removeLastOrNull() }
                )
            }
            entry<Simple> {
                PantallaPreguntas(
                    innerPadding = innerPadding,
                    modoExamen = false,
                    modoSimple = true,
                    eventoVolver = { backStack.removeLastOrNull() }
                )
            }
            entry<Estadisticas> {
                PantallaEstadisticas(innerPadding)
            }
        }
    )
}
