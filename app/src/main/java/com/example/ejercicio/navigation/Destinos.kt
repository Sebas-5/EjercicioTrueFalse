package com.example.ejercicio.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Menu : NavKey

@Serializable
data object Juego : NavKey

@Serializable
data object Estadisticas : NavKey
