# Proyecto Integrador I - Juego de Cartas
**Asignatura:** Estructuras de Datos - Ciclo 2026 - 2do Cuatrimestre  
**Institución:** Facultad de Ingeniería - Universidad Nacional de Jujuy (UNJu)  
**Carreras:** Ingeniería Informática / Licenciatura en Sistemas  

---

##  Descripción General
El proyecto consiste en la simulación de un juego de cartas de 4 jugadores que compiten durante varias rondas tomando cartas de un mazo de naipes franceses (52 cartas) ordenadas al azar. En cada ronda, los jugadores comparan sus cartas y el que obtiene la carta de mayor valor numérico se lleva las cartas de los demás y las guarda en su **pozo acumulador (Pila)**. En caso de empate en el valor máximo, cada jugador conserva su carta. Gana el jugador que obtenga el mayor puntaje al finalizar las rondas.

---

## Lo Que Ya Está Hecho y Cómo Funciona

Actualmente se encuentran implementadas y documentadas las estructuras base y las clases del dominio del mazo:

### 1. `ed.tda.Arreglo<T>` (TDA Arreglo Genérico)
* **Cómo funciona:** Es una estructura contigua de memoria de tamaño fijo (`capacidadMax`). Almacena elementos genéricos utilizando un array `Object[]`. 
* **Funcionalidad clave:** Ofrece inserción ordenada al final (`insertar`), acceso directo por índice `obtener(i)` en tiempo constante $\mathcal{O}(1)$, verificación de estado (`estaVacio`, `estaLleno`) y el método `intercambiar(i, j)`, indispensable para modificar de posición dos elementos en operaciones de ordenamiento o barajado.

### 2. `ed.tda.Pila<T>` (TDA Pila Genérico)
* **Cómo funciona:** Implementación propia de una estructura LIFO (Last In, First Out) **construida sobre la clase `Arreglo<T>`**.
* **Funcionalidad clave:** Permite apilar elementos en la cima (`apilar`), consultar el elemento superior (`verCima`) y desapilar (`desapilar`). En el contexto del juego, se utiliza para representar el pozo de cartas ganadas por cada jugador, permitiendo acumular los naipes y luego desapilarlos al final para calcular el puntaje total.

### 3. `modelo.Carta`
* **Cómo funciona:** Modela un naipe individual del mazo francés.
* **Atributos:** Contiene el `palo` (Trébol, Pica, Corazones, Diamantes), el `valor` (1 a 13) y el estado de la carta (`disponible` o `no disponible`).

### 4. `modelo.Mazo`
* **Cómo funciona:** Encapsula las 52 cartas francesas organizadas internamente mediante el `Arreglo<Carta>`.
* **Funcionalidad clave:** 
  * `inicializarMazo()`: Genera las 52 combinaciones de cartas.
  * `mezclar()`: Implementa el algoritmo de **Fisher-Yates** aprovechando el método `intercambiar` del TDA Arreglo para barajar las cartas al azar.
  * `sacarCarta()`: Entrega una carta del mazo y cambia su estado a no disponible (`disponible = false`).

---

##  Lo Que Falta Implementar (Para el Resto del Equipo)

Para completar el desarrollo del programa de acuerdo a las consignas de la cátedra, restan por implementar los siguientes módulos:

### 1. `ed.tda.Cola<T>` (TDA Cola propio)
* **Objetivo:** Implementar el TDA Cola (FIFO) para la gestión del **turno de los 4 jugadores**.
* **Métodos requeridos:** `encolar(T x)`, `desencolar()`, `frente()`, `estaVacia()`, `tamanio()`.

### 2. `modelo.Jugador`
* **Objetivo:** Representar a cada participante del juego.
* **Atributos requeridos:** `nombre`, `apellido`, `edad` (exigidos por la consigna) y una instancia de `Pila<Carta>` (su pozo de cartas ganadas).
* **Métodos requeridos:** Getters/Setters, método para recibir y apilar cartas en su pozo, y método para calcular el puntaje total desapilando las cartas de su pozo.

### 3. `juego.ControladorJuego`
* **Objetivo:** Orquestar la partida e integrar todas las estructuras de datos.
* **Lógica requerida:**
  * Controlar la ronda actual (ejecutar las rondas o limitar a 3 rondas).
  * Usar el TDA Cola para dar turno a los 4 jugadores.
  * Despachar cartas del `Mazo`, comparar los valores de la ronda actual y determinar al ganador de la ronda.
  * Enviar las cartas ganadas al pozo (`Pila`) del ganador correspondiente (o hacer que cada uno conserve la suya si hay empate).
  * Calcular los puntajes finales y determinar al o los ganadores.

### 4. `main.Principal`
* **Objetivo:** Punto de entrada ejecutable del sistema. Debe instanciar a los jugadores, el mazo, iniciar el juego y mostrar los resultados y el informe final en consola.

---

## Estructura de Archivos del Proyecto

```text
juego-cartas/
├── bin/                      # Binarios compilados (.class)
├── src/
│   ├── ed/
│   │   └── tda/
│   │       ├── Arreglo.java  # [HECHO] TDA Arreglo estático genérico
│   │       ├── Pila.java     # [HECHO] TDA Pila basado en TDA Arreglo
│   │       └── Cola.java     # [PENDIENTE] TDA Cola para Turnos
│   ├── modelo/
│   │   ├── Carta.java        # [HECHO] Entidad Carta con estado
│   │   ├── Mazo.java         # [HECHO] Mazo de 52 cartas y barajado
│   │   └── Jugador.java      # [PENDIENTE] Entidad Jugador con Pila propia
│   ├── juego/
│   │   └── ControladorJuego.java # [PENDIENTE] Lógica de rondas y reglas
│   └── main/
│       └── Principal.java    # [PENDIENTE] Punto de entrada del programa
├── run.sh                    # Script de compilación y ejecución en Linux
├── .gitignore
└── README.md

## Compilación y Ejecución


### Ejecución en Windows (`run.bat`)

El proyecto cuenta con un script ejecutable para compilar y correr todo en un solo paso sin escribir comandos largos.

#### Opción 1: Desde el Explorador de Archivos
1. Abrí la carpeta del proyecto (`juego-cartas/`).
2. Hacé **doble clic** sobre el archivo `run.bat`.

#### Opción 2: Desde la Consola (CMD o PowerShell)
1. Abrí la terminal en la carpeta del proyecto.
2. Ejecutá el siguiente comando:

```cmd
.\run.bat
