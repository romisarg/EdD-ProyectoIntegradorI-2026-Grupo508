package main;

import java.util.Scanner;

import ed.tda.Cola;
import juego.ControladorJuego;
import modelo.Jugador;
import modelo.Mazo;

/**
 * Clase principal del proyecto.
 * Esqueleto para el punto de entrada de la aplicación.
 */

public class Principal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean jugarNuevamente = true;

        System.out.println("======================================");
        System.out.println("          JUEGO DE CARTAS");
        System.out.println("======================================");
        System.out.println();

        System.out.println("Bienvenido al juego.");
        System.out.println("La partida se juega con 4 jugadores.");
        System.out.println("Cada partida tiene 3 rondas.");
        System.out.println();

        while (jugarNuevamente) {

            // Creamos un nuevo mazo
            Mazo mazo = new Mazo();

            // Creamos la cola para los 4 jugadores
            Cola<Jugador> jugadores = new Cola<>(4);

            System.out.println("===== REGISTRO DE JUGADORES =====");
            System.out.println();

            // Registramos los cuatro jugadores
            for (int i = 1; i <= 4; i++) {

                System.out.println("Jugador " + i);

                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Apellido: ");
                String apellido = scanner.nextLine();

                System.out.print("Edad: ");
                int edad = Integer.parseInt(scanner.nextLine());

                Jugador jugador = new Jugador(nombre, apellido, edad);

                jugadores.encolar(jugador);

                System.out.println();
            }

            System.out.println("======================================");
            System.out.println("Los 4 jugadores fueron registrados.");
            System.out.println("La partida comenzará con 3 rondas.");
            System.out.println("======================================");
            System.out.println();

            System.out.print("¿Desea comenzar la partida? (s/n): ");
            String respuesta = scanner.nextLine();

            if (respuesta.equalsIgnoreCase("s")) {

                // Creamos el controlador del juego
                ControladorJuego controlador =
                        new ControladorJuego(mazo, jugadores, 3);

                // Iniciamos la partida
                controlador.iniciarPartida();

            } else {

                System.out.println();
                System.out.println("Partida cancelada.");
            }

            System.out.println();
            System.out.print("¿Desea jugar otra partida? (s/n): ");

            respuesta = scanner.nextLine();

            if (!respuesta.equalsIgnoreCase("s")) {
                jugarNuevamente = false;
            }

            System.out.println();
        }

        System.out.println("======================================");
        System.out.println("       ¡Fin del Juego!");
        System.out.println("======================================");

        scanner.close();
    }
}
