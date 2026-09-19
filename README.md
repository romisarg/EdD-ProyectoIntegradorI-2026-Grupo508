# Proyecto Integrador I - Juego de Cartas - EdD

* **Asignatura:** Estructuras de Datos - Ciclo 2026 - 2do Cuatrimestre
* **Institución:** Facultad de Ingeniería - Universidad Nacional de Jujuy (UNJu)
* **Carreras:** Ingeniería Informática / Licenciatura en Sistemas

---

## 📝 Descripción del Proyecto

El trabajo  consiste en la simulación de un juego de cartas para 4 jugadores que compiten durante **3 rondas**, tomando cartas de un mazo de naipes franceses de 52 cartas mezcladas al azar.

## Mapa conceptual del juego:

```text
                         ┌─────────────────────┐
                         │       PARTIDA       │
                         └──────────┬──────────┘
                                    │
                    ┌───────────────┼───────────────┐
                    │               │               │
                    ▼               ▼               ▼
             ┌────────────┐  ┌────────────┐  ┌────────────┐
             │ 4 JUGADORES│  │    MAZO    │  │ 3 RONDAS   │
             └─────┬──────┘  │ 52 CARTAS  │  └─────┬──────┘
                   │         └─────┬──────┘        │
                   │               │               │
                   ▼               ▼               │
              ┌─────────┐    ┌──────────┐          │
              │  COLA   │    │  PILA    │          │
              │  FIFO   │    │  MAZO    │          │
              └────┬────┘    └────┬─────┘          │
                   │              │                │
                   │              ▼                │
                   │       ┌──────────────┐        │
                   └──────►│ CADA JUGADOR │◄───────┘
                           │ RECIBE 1 CARTA│
                           └──────┬───────┘
                                  │
                                  ▼
                         ┌─────────────────┐
                         │ COMPARAR VALORES│
                         └────────┬────────┘
                                  │
                    ┌─────────────┴─────────────┐
                    │                           │
                    ▼                           ▼
             ┌──────────────┐           ┌──────────────┐
             │ HAY GANADOR  │           │    EMPATE    │
             └──────┬───────┘           └──────┬───────┘
                    │                          │
                    ▼                          ▼
          ┌──────────────────┐       ┌──────────────────┐
          │ Gana la ronda y  │       │ Cada jugador     │
          │ recibe las 4     │       │ conserva su      │
          │ cartas           │       │ propia carta     │
          └────────┬─────────┘       └────────┬─────────┘
                   │                          │
                   └────────────┬─────────────┘
                                ▼
                       ┌──────────────────┐
                       │  POZO DEL        │
                       │  JUGADOR (PILA)  │
                       └────────┬─────────┘
                                │
                                ▼
                       ┌──────────────────┐
                       │ CALCULAR         │
                       │ PUNTAJES FINALES │
                       └────────┬─────────┘
                                │
                                ▼
                       ┌──────────────────┐
                       │ MOSTRAR GANADOR  │
                       │ DE LA PARTIDA    │
                       └──────────────────┘
```

---

## ⚙️ Estructuras y clases utilizadas

### * ed.tda.Arreglo<T>
```bash
TDA Arreglo genérico de tamaño fijo.
Es la estructura base sobre la que se construyen la Pila y la Cola,
y también se usa como auxiliar durante el mezclado del mazo.

**Operaciones:** `insertar()` · `obtener()` · `modificar()` · `longitud()` ·
`capacidad()` · `estaVacio()` · `estaLleno()` · `intercambiar()`
```
### * ed.tda.Pila<T>
```bash
Estructura LIFO (Last In, First Out) construida sobre `Arreglo<T>`.

**Operaciones:** `apilar()` · `desapilar()` · `verCima()` · `estaVacia()` ·
`estaLlena()` · `tamanio()`

**Se usa para:**
1. Administrar el mazo de cartas.
2. Administrar el pozo de cartas acumuladas de cada jugador.
```
### * ed.tda.Cola<T>
```bash
Estructura FIFO (First In, First Out) construida sobre `Arreglo<T>`.
Administra el orden de turno de los 4 jugadores durante las rondas.

**Operaciones:** `encolar()` · `desencolar()` · `frente()` · `estaVacia()` ·
`estaLlena()` · `tamanio()`
```
### * modelo.Carta
```bash
Representa una carta individual del mazo francés.

**Atributos:** `palo`, `valor` (1 a 13), `disponible` (indica si la carta
sigue en el mazo o ya fue entregada a un jugador).

Expone getters para consultar sus datos y un setter para actualizar su
disponibilidad.
```
### * modelo.Mazo
```bash
Representa el mazo francés de 52 cartas, administrado internamente con una
`Pila<Carta>`.

**Responsabilidades:**
- Generar las 52 cartas (4 palos × valores del 1 al 13).
- Mezclarlas con el algoritmo Fisher-Yates.
- Entregar cartas durante las rondas y marcarlas como no disponibles.
- Informar cuántas cartas quedan disponibles.
```
### * modelo.Jugador
```bash
Representa a cada participante de la partida.

**Atributos:** `nombre`, `apellido`, `edad`, `pozo` (una `Pila<Carta>` con las
cartas que el jugador va ganando).

**Comportamiento:** getters para sus datos, un método para recibir cartas
(las apila en su pozo) y otro para calcular su puntaje final sumando el
valor de todas las cartas acumuladas.
```
### * juego.ControladorJuego
```bash
Controla la lógica principal de la partida.

**Responsabilidades:**
- Ejecutar las 3 rondas, tomando a los jugadores de la Cola y repartiéndoles
  cartas del Mazo.
- Comparar los valores de cada ronda y determinar su ganador (o resolver el
  empate, si lo hay).
- Entregar las cartas correspondientes al pozo del jugador que corresponda.
- Al finalizar, calcular los puntajes y determinar al o los ganadores.
```
### * main.Principal
```bash
Punto de entrada del programa.

**Flujo:** da la bienvenida, registra a los cuatro jugadores, crea el mazo y
la Cola de turnos, instancia el `ControladorJuego`, y pregunta si se quiere
iniciar la partida. Al terminar, pregunta si se desea jugar de nuevo — en
ese caso arranca una partida completamente nueva, con mazo y jugadores
recién creados.
```

---

## 🔗 Relación entre las estructuras y el juego

Las estructuras de datos se utilizan en situaciones concretas dentro del juego:

### Arreglo

Permite trabajar con una cantidad fija y conocida de elementos.

Se utiliza para:

* Almacenar temporalmente los cuatro jugadores de una ronda.
* Almacenar las cuatro cartas de una ronda.
* Almacenar los puntajes finales.
* Almacenar temporalmente las cartas durante el proceso de mezcla del mazo.

### Pila

Su comportamiento **LIFO (Last In, First Out)** se utiliza para:

* Administrar el mazo y extraer cartas.
* Acumular las cartas obtenidas por cada jugador.
* Desapilar las cartas al momento de calcular el puntaje.

### Cola

Su comportamiento **FIFO (First In, First Out)** permite:

* Mantener el orden de los cuatro jugadores.
* Obtener el jugador correspondiente durante el reparto.
* Volver a incorporar al jugador a la cola para conservar el orden durante las siguientes rondas.

---

## 📂 Estructura de Archivos del Proyecto

```text
juego-cartas/
├── bin/                            # Binarios compilados (.class)
├── src/
│   ├── ed/
│   │   └── tda/
│   │       ├── Arreglo.java        # TDA Arreglo estático genérico
│   │       ├── Pila.java           # TDA Pila basado en TDA Arreglo
│   │       └── Cola.java           # TDA Cola para turnos
│   ├── modelo/
│   │   ├── Carta.java              # Entidad Carta con estado
│   │   ├── Mazo.java               # Mazo de 52 cartas y barajado
│   │   └── Jugador.java            # Entidad Jugador con Pila propia
│   ├── juego/
│   │   └── ControladorJuego.java   # Lógica de rondas y reglas
│   └── main/
│       └── Principal.java           # Punto de entrada del programa
├── run.bat                         # Script de compilación y ejecución en Windows
├── .gitignore
└── README.md
```

---

## 🚀 Compilación y Ejecución

### Ejecución en Windows (`run.bat`)

El proyecto cuenta con un script ejecutable para compilar y correr todo en un solo paso sin escribir comandos largos.

#### Opción 1: Desde el Explorador de Archivos

1. Abrí la carpeta raíz del proyecto (`/EdD-ProyectoIntegradorI-2026-Grupo508`).
2. Hacé **doble clic** sobre el archivo `run.bat`.

#### Opción 2: Desde la Consola (CMD o PowerShell)

1. Abrí la terminal en la carpeta del proyecto.
2. Ejecutá el siguiente comando:

```cmd
.\run.bat
```

El script compila los archivos `.java` del proyecto y luego ejecuta la clase principal.

---

## ✅ Estado actual del proyecto

En el funcionamiento principal del juego se encuentra implementado:

* [x] TDA Arreglo
* [x] TDA Pila
* [x] TDA Cola
* [x] Clase Carta
* [x] Clase Mazo
* [x] Clase Jugador
* [x] Controlador de la partida
* [x] Registro de jugadores
* [x] Mezcla aleatoria del mazo
* [x] Desarrollo de 3 rondas
* [x] Comparación de cartas
* [x] Resolución de empates
* [x] Acumulación de cartas
* [x] Cálculo de puntajes
* [x] Determinación del ganador o ganadores
* [x] Opción de iniciar una nueva partida

