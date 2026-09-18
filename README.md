# Proyecto Integrador I - Juego de Cartas

**Asignatura:** Estructuras de Datos - Ciclo 2026 - 2do Cuatrimestre
**Institución:** Facultad de Ingeniería - Universidad Nacional de Jujuy (UNJu)
**Carreras:** Ingeniería Informática / Licenciatura en Sistemas

---

## Descripción General

El proyecto consiste en la simulación de un juego de cartas para 4 jugadores que compiten durante **3 rondas**, tomando cartas de un mazo de naipes franceses de 52 cartas mezcladas al azar.

En cada ronda, los jugadores reciben una carta y comparan sus valores numéricos. El jugador que obtiene la carta de mayor valor se lleva las cartas de los demás y las guarda en su **pozo acumulador (Pila)**.

En caso de empate en el valor máximo, cada jugador conserva su propia carta.

Al finalizar las 3 rondas, se calcula el puntaje de cada jugador sumando los valores de las cartas acumuladas en su pozo. Gana el jugador o jugadores que obtengan el mayor puntaje.

---

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

## Lo Que Ya Está Hecho y Cómo Funciona

Actualmente se encuentran implementadas las estructuras de datos y las clases necesarias para el funcionamiento del juego:

### 1. `ed.tda.Arreglo<T>` (TDA Arreglo Genérico)

* **Cómo funciona:** Es una estructura de tamaño fijo que utiliza internamente un arreglo `Object[]` para almacenar elementos genéricos.
* **Funcionalidad clave:** Ofrece inserción al final (`insertar`), acceso por índice mediante `obtener(i)`, modificación de elementos (`modificar`), verificación de estado (`estaVacio`, `estaLleno`) y el método `intercambiar(i, j)`, utilizado durante el proceso de barajado del mazo.

### 2. `ed.tda.Pila<T>` (TDA Pila Genérico)

* **Cómo funciona:** Implementación propia de una estructura LIFO (Last In, First Out) construida sobre la clase `Arreglo<T>`.
* **Funcionalidad clave:** Permite apilar elementos en la cima (`apilar`), consultar el elemento superior (`verCima`) y desapilar (`desapilar`).
* **Uso en el juego:** Se utiliza para representar tanto el **mazo de cartas** como el **pozo acumulador de cada jugador**.

### 3. `modelo.Carta`

* **Cómo funciona:** Modela un naipe individual del mazo francés.
* **Atributos:** Contiene el `palo` (Trébol, Pica, Corazones, Diamantes), el `valor` (1 a 13) y el estado de la carta (`disponible` o `no disponible`).

### 4. `modelo.Mazo`

* **Cómo funciona:** Encapsula las 52 cartas francesas organizadas internamente mediante una `Pila<Carta>`.
* **Funcionalidad clave:**

  * `inicializarYMezclar()`: Genera las 52 cartas correspondientes a los cuatro palos y los valores del 1 al 13, y luego las mezcla.
  * `mezclar()`: Permite volver a inicializar y mezclar el mazo utilizando el algoritmo de **Fisher-Yates**, aprovechando el método `intercambiar` del TDA Arreglo.
  * `sacarCarta()`: Extrae una carta del mazo y cambia su estado a no disponible (`disponible = false`).
  * `tieneCartasDisponibles()`: Permite verificar si quedan cartas en el mazo.
  * `cartasRestantes()`: Indica la cantidad de cartas que quedan en el mazo.

---

# Descripción de las clases:

## `ed.tda.Arreglo<T>`

Es un TDA Arreglo genérico de tamaño fijo.

Se utiliza como estructura auxiliar para almacenar elementos y acceder a ellos mediante índices.

Entre sus operaciones se encuentran:

* `insertar()`
* `obtener()`
* `modificar()`
* `longitud()`
* `capacidad()`
* `estaVacio()`
* `estaLleno()`
* `intercambiar()`

También se utiliza durante el proceso de mezcla del mazo.

---

## `ed.tda.Pila<T>`

Implementa una estructura **LIFO (Last In, First Out)** utilizando el TDA `Arreglo`.

Sus principales operaciones son:

* `apilar()`
* `desapilar()`
* `verCima()`
* `estaVacia()`
* `estaLlena()`
* `tamanio()`

Se utiliza para:

1. Administrar el mazo de cartas.
2. Administrar el pozo de cartas de cada jugador.

---

## `ed.tda.Cola<T>`

Implementa una estructura **FIFO (First In, First Out)** utilizando el TDA `Arreglo`.

Se utiliza para administrar el orden de los cuatro jugadores durante las rondas.

Sus principales operaciones son:

* `encolar()`
* `desencolar()`
* `verFrente()`
* `estaVacia()`
* `estaLlena()`
* `tamanio()`

---

## `modelo.Carta`

Representa una carta individual del mazo francés.

Contiene:

```text
palo
valor
disponible
```

El valor de la carta se encuentra entre 1 y 13.

El atributo `disponible` permite indicar si la carta continúa disponible en el mazo o si ya fue entregada a un jugador.

Además, permite consultar y modificar el estado de disponibilidad de la carta.

---

## `modelo.Mazo`

Representa el mazo francés de 52 cartas.

Sus principales responsabilidades son:

* Crear las 52 cartas.
* Mezclarlas aleatoriamente.
* Administrarlas mediante una Pila.
* Entregar cartas durante las rondas.
* Marcar las cartas entregadas como no disponibles.
* Informar cuántas cartas quedan disponibles.

Para generar las cartas se utilizan los cuatro palos y los valores del 1 al 13.

El barajado se realiza mediante el algoritmo **Fisher-Yates**.

---

## `modelo.Jugador`

Representa a cada participante de la partida.

Contiene:

```text
nombre
apellido
edad
pozo
```

El `pozo` es una `Pila<Carta>` donde se almacenan las cartas que el jugador obtiene durante las rondas.

También permite:

* Consultar sus datos mediante getters.
* Recibir cartas y almacenarlas en su pozo.
* Calcular su puntaje final sumando los valores de las cartas acumuladas.

---

## `juego.ControladorJuego`

Es la clase encargada de controlar la lógica principal de la partida.

Se ocupa de:

* Ejecutar las 3 rondas.
* Obtener los jugadores de la Cola.
* Solicitar cartas al Mazo.
* Comparar los valores de las cartas de cada ronda.
* Determinar el ganador de cada ronda.
* Entregar las cartas correspondientes al ganador.
* Resolver los empates.
* Calcular los puntajes finales.
* Determinar el jugador o jugadores con mayor puntaje.
* Mostrar los resultados de la partida.

---

## `main.Principal`

Es el punto de entrada del programa.

Se encarga de:

* Mostrar la bienvenida al juego.
* Registrar a los cuatro jugadores.
* Crear el mazo.
* Crear la Cola de jugadores.
* Crear el `ControladorJuego`.
* Preguntar si se desea comenzar la partida.
* Iniciar la partida.
* Preguntar si se desea jugar otra partida.

Cuando el usuario decide jugar nuevamente, se crea una **nueva partida desde cero**, con un nuevo mazo y un nuevo registro de jugadores.

---

## Relación entre las estructuras y el problema

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

## Estructura de Archivos del Proyecto

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

## Compilación y Ejecución

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

## Estado actual del proyecto

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

