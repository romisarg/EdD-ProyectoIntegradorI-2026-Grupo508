package modelo;

import ed.tda.Arreglo;
import java.util.Random;

/**
 * Clase Mazo que representa las 52 cartas francesas.
 * Implementado internamente sobre el TDA Arreglo propio.
 */
public class Mazo {
    private Arreglo<Carta> cartas;
    private int cartasRepartidas;

    public Mazo() {
        this.cartas = new Arreglo<>(52);
        this.cartasRepartidas = 0;
        inicializarMazo();
    }

    /**
     * Carga los 52 naipes franceses en el TDA Arreglo.
     */
    private void inicializarMazo() {
        String[] palos = {"Trébol", "Pica", "Corazones", "Diamantes"};
        for (String palo : palos) {
            for (int valor = 1; valor <= 13; valor++) {
                cartas.insertar(new Carta(palo, valor, true));
            }
        }
    }

    /**
     * Mezcla el mazo de forma aleatoria usando el algoritmo de Fisher-Yates.
     */
    public void mezclar() {
        Random rand = new Random();
        int n = cartas.longitud();
        for (int i = n - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            cartas.intercambiar(i, j);
        }
    }

    /**
     * Extrae un naipe del mazo si hay disponibles y cambia su estado a no disponible.
     */
    public Carta sacarCarta() {
        if (tieneCartasDisponibles()) {
            Carta carta = cartas.obtener(cartasRepartidas);
            carta.setDisponible(false);
            cartasRepartidas++;
            return carta;
        }
        return null;
    }

    public boolean tieneCartasDisponibles() {
        return cartasRepartidas < cartas.longitud();
    }

    public int cartasRestantes() {
        return cartas.longitud() - cartasRepartidas;
    }
}
