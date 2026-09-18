package modelo;

import ed.tda.Pila;

/**
 * Representa a un jugador de la partida.
 */
public class Jugador {

    private String nombre;
    private String apellido;
    private int edad;

    private Pila<Carta> pozo;

    /**
     * Constructor del jugador.
     */
    public Jugador(String nombre, String apellido, int edad) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;

        // Un jugador puede llegar a recibir hasta 52 cartas
        this.pozo = new Pila<>(52);
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    /**
     * Agrega una carta al pozo del jugador.
     */
    public void recibirCarta(Carta carta) {
        pozo.apilar(carta);
    }

    /**
     * Calcula el puntaje sumando los valores de las cartas.
     */
    public int calcularPuntaje() {

        int puntaje = 0;

        while (!pozo.estaVacia()) {

            Carta carta = pozo.desapilar();

            puntaje += carta.getValor();
        }

        return puntaje;
    }
}
