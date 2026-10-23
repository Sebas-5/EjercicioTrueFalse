[CLAUDE.md](https://github.com/user-attachments/files/33883971/CLAUDE.md)
# Contexto para Claude: Práctica 2 de PGL (App de preguntas)

## Quién soy y cómo quiero que me ayudes

- Soy Sebastian, alumno de 2º DAM en el CIFP César Manrique (Tenerife). Llámame "papi".
- Háblame en español, informal, con respuestas cortas y directas.
- Este repo es la Práctica 2 del módulo PGL (Programación multimedia y dispositivos móviles).
- Mi objetivo es APRENDER para el examen, no copiar. Por defecto:
  - Explícame qué hay que hacer y por qué, con pistas, y deja que lo escriba yo.
  - Revisa mi código, dime qué falla y por qué, y deja que lo arregle yo.
  - Ve paso a paso. No me sueltes todo el código de golpe.
  - Si te pregunto algo concreto ("¿cómo pongo X?"), dame la respuesta con un ejemplo corto.
- Si te pido expresamente que termines algo por mí, hazlo, pero:
  - Usa SOLO las formas que hemos visto en clase (sección "Lo que hemos dado en clase").
  - Mantén mi estilo y mis nombres de variables.
  - Explícame después qué has hecho, en pocas líneas, para que lo entienda.
- Si te equivocas en algo, reconócelo diciendo "my bad".

## El enunciado

App de preguntas de verdadero/falso. Se entrega el enlace del repositorio de GitHub. Fecha: 29/10 (en el enunciado pone 2023, es errata).

### Parte obligatoria (5 puntos)

- Lista (pool) de 5 preguntas que se van iterando (no hay base de datos).
- Interfaz: dos Text, una Image y tres Button (TRUE, FALSE, NEXT).
- NEXT lleva un icono de flecha a la derecha (clase `Icons` o importado con Vector Asset).
- NEXT muestra la siguiente pregunta. Al llegar al final, vuelve a la primera.
- TRUE/FALSE comprueban si la respuesta es correcta:
  - Acierto: mensaje de confirmación en VERDE.
  - Fallo: mensaje de error en ROJO.
  - Pintar en rojo el botón de la elección errónea y en verde el de la correcta.
  - Al pulsar NEXT, borrar el mensaje.
- Diseño jerárquico y modular de componentes, con state hoisting.
- Se valora: código limpio y modular, textos en strings.xml, constantes para márgenes y padding, diseño descendente de Composables.

### Parte optativa (5 puntos)

1. Botón PREV con icono de flecha a la izquierda. Desde la primera pregunta, va a la última. (0.25)
2. Botón RANDOM que muestra una pregunta al azar. (0.25)
3. Pantalla de menú principal con navegación (pila de navegación) y pantalla de estadísticas: aciertos, fallos, % aciertos, % fallos, clicks totales en botones. Guardar con SharedPreferences (Anexo II). (1)
4. Modo examen: no guarda clicks ni aciertos, no se puede cambiar la respuesta una vez dada (desactivar botones con `enabled`). Al final, AlertDialog con imagen y texto según la nota: 100% motivador, 50-99% normal, menos de 50% insultante (Anexo III). (1)
5. Pantalla para añadir preguntas (enunciado y respuesta) que se guardan en un fichero de texto (sin machacarlo, añadiendo al final). Las preguntas se leen del fichero al iniciar, ya no del array. Toast al guardar. Usar FileInputStream/FileOutputStream con FileReader/FileWriter (lo visto en Acceso a Datos) en `applicationContext.filesDir`. Ningún componente abre el fichero directamente: todo va en `data/PreguntaRepository.kt` con `cargarPreguntas()` y `guardarPregunta()`. Menú principal con 3 botones: añadir preguntas, jugar (eligiendo modo) y estadísticas. (1.5)
6. Modo simple / speedrun: no muestra imágenes y añade un `LinearProgressIndicator` con el progreso de la partida. (1)

### Estructura de carpetas pedida (Anexo I)

```
com.example.ejercicio
├── data/PreguntaRepository.kt      (solo para el extra 5)
├── model/Pregunta.kt
├── navigation/Destinos.kt          (las claves / NavKey)
├── navigation/Navigation.kt        (la pila y el NavDisplay)
├── ui/componentes/                 (botones, textos y piezas reutilizables)
├── ui/pantallas/                   (pantallas completas)
└── MainActivity.kt                 (solo setContent y cargar el componente principal)
```

### Anexos del enunciado

- Anexo II (SharedPreferences): `getSharedPreferences(nombre, Context.MODE_PRIVATE)`, con el nombre del fichero como constante en strings.xml. Escribir con `edit().putInt(...).apply()`. Leer con `getInt(clave, valorPorDefecto)`. Guardar al salir de la pantalla de juego usando `BackHandler { }`.
- Anexo III (diálogos): `AlertDialog` de Material 3 con `title`, `text`, `icon`, `onDismissRequest`, `confirmButton`, `dismissButton`. Se muestra u oculta con una variable de estado Boolean.

## Lo que hemos dado en clase (usar SOLO esto)

### Tema 2.1: Layouts e imágenes
- Layouts: `Column`, `Row`, `Box`. `verticalArrangement` / `horizontalArrangement` (Center, SpaceBetween, SpaceEvenly, SpaceAround, `spacedBy`) y `horizontalAlignment` / `verticalAlignment`.
- Modifiers encadenados (el orden importa): `size`, `width`, `height`, `fillMaxSize`, `fillMaxWidth`, `fillMaxHeight`, `padding`, `background`, `border`, `clip`, `weight`, `wrapContentSize`, `align`, `aspectRatio`.
- `Text` con `color`, `fontSize` (sp), `fontWeight`, `textAlign`, `maxLines`, `overflow`.
- `Image(painter = painterResource(id = R.drawable.x), contentDescription = "", modifier = ..., contentScale = ...)`. Imágenes en `res/drawable`.
- Unidades: dp para tamaños, sp para textos.
- Textos en `strings.xml` con `stringResource(R.string.x)`.

### Tema 2.2: Estado y recomposición
- Estado: `var x by remember { mutableStateOf(valor) }`.
- Botones: `Button`, `FilledTonalButton`, `OutlinedButton`, `ElevatedButton`, `TextButton`, con `onClick`, `enabled`, `colors`, `contentPadding`.
- Colores de botón: el profe usa `ButtonColors(containerColor = ..., contentColor = ButtonDefaults.buttonColors().contentColor, disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor, disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor)`.
- `TextField` con `rememberTextFieldState()`, `label`, `placeholder`, `isError`, `supportingText`.
- `RadioButton`, `Switch`, `Checkbox`.
- State hoisting: "EL PADRE BAJA EL ESTADO, EL HIJO SUBE LOS EVENTOS". El padre tiene la variable de estado y le pasa al hijo una lambda. Ejemplo del profe:

```kotlin
@Composable
fun EjemploSH(miModifier: Modifier) {
    var colorColumna by remember { mutableStateOf(Color.White) }
    Column(modifier = miModifier.fillMaxSize().background(colorColumna)) {
        BloqueBoton(texto = "Primer Boton", eventoPadre = { colorRecibido ->
            colorColumna = colorRecibido
        })
    }
}

@Composable
fun BloqueBoton(texto: String, eventoPadre: (Color) -> Unit) {
    Button(onClick = {
        eventoPadre(Color.Red)
    }) {
        Text(texto)
    }
}
```

### Tema 2.3: Actividades y navegación
- Ciclo de vida: onCreate, onStart, onResume, onPause, onStop, onRestart, onDestroy. `rememberSaveable`.
- Navigation 3: destinos con `data object` (sin parámetros) o `data class` (con parámetros) que implementan `NavKey`. Pila con `rememberNavBackStack(Inicio)`. `NavDisplay(backStack = ..., onBack = { backStack.removeLastOrNull() }, entryProvider = entryProvider { entry<Destino> { Pantalla(...) } })`. Navegar con `backStack.add(Destino)`.
- NUNCA pasar la pila a los componentes hijos: los hijos reciben lambdas (callbacks) y la navegación las ejecuta.

### Otras cosas vistas
- `Toast.makeText(context, "mensaje", Toast.LENGTH_SHORT).show()` con `val context = LocalContext.current` fuera del onClick.
- Ficheros en Java/Kotlin: lo visto en el módulo Acceso a Datos (FileReader, FileWriter, BufferedReader, BufferedWriter).

### NO usar (no lo hemos visto)
- ViewModel, StateFlow, corrutinas, LiveData, DataStore, Room.
- Anotaciones como `@StringRes` o `@DrawableRes`.
- Librerías externas que no estén en el proyecto.

## Estado actual del proyecto

- Paquete: `com.example.ejercicio`. Repo en GitHub: `EjercicioTrueFalse`.
- Compose BOM 2026.02.01. Ojo: con material3 nuevo, `Icons` puede necesitar añadir `implementation("androidx.compose.material:material-icons-core")` en `app/build.gradle.kts`.
- Hecho:
  - `model/Pregunta.kt`: `data class Pregunta(val enunciado: String, val respuesta: Boolean, val imagen: Int)`.
  - `model/BancoPreguntas.kt`: `val preguntas = listOf(...)` con 5 preguntas e imágenes `R.drawable.imagen1` a `imagen5`.
  - `ui/theme/Color.kt`: `VerdeAcierto = Color(0xFF1DAD2B)` y `RojoMalo = Color(0xFF990000)`.
  - `ui/theme/Dimens.kt`: `object Dimens { paddingPantalla = 16.dp, espacioPequeno = 8.dp }`.
  - `strings.xml`: `app_name`, `boton_true`, `boton_false`, `boton_next`, `mensaje_acierto` (un hueco %1$s) y `mensaje_fallo` (dos huecos %1$s y %2$s).
  - `ui/pantallas/PantallaPreguntas.kt`: recibe `innerPadding: PaddingValues` desde el Scaffold de MainActivity. Estado: `indice` (Int) y `textoUsuario` (String, el mensaje que se muestra; así lo indicó el profe). Muestra enunciado, imagen, mensaje, Row con botones TRUE/FALSE y botón NEXT. El NEXT ya funciona: vuelve a 0 con `if (indice == preguntas.size - 1)` y vacía `textoUsuario`.
- Pendiente en la parte obligatoria:
  1. `mensaje_fallo` tiene 2 huecos y se le pasaba 1 dato, lo que cierra la app. Calcular los textos según la pregunta actual: `textoAcierto` con `respuesta.toString()` y `textoFallo` con `(!respuesta).toString()` y `respuesta.toString()`.
  2. Rellenar los `else` de TRUE y FALSE con el mensaje de fallo.
  3. Mensaje verde si acierta y rojo si falla.
  4. Textos de los botones desde strings.xml (ahora están escritos a mano).
  5. Colores de los botones: normales antes de responder; al responder, verde el de la respuesta correcta y rojo el otro.
  6. Icono de flecha en NEXT.
  7. Sacar las piezas a `ui/componentes` (botones, mensaje…) con state hoisting, dejar MainActivity limpia y borrar el `Greeting` que sobra.
- Después: los extras, en orden.
