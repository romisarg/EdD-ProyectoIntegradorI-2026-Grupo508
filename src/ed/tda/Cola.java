package ed.tda;

/**
 * TDA Cola genérico
 * Se utiliza para gestionar el turno de los jugadores.
 *
 * @param <T> Tipo de dato almacenado en la cola.
*/

public class Cola<T> {

    private Arreglo<T> arreglo;

    public Cola(int capacidadMax) {
        this.arreglo = new Arreglo<>(capacidadMax);
    }

    /**
     * Agrega un elemento al final de la cola.
     */
    public void encolar(T elemento) {
        if (estaLlena()) {
            throw new IllegalStateException("La cola está llena.");
        }

        arreglo.insertar(elemento);
    }

    /**
     * Retira y retorna el primer elemento de la cola.
     */
    public T desencolar() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía.");
        }

        T primero = arreglo.obtener(0);

        Arreglo<T> nuevoArreglo = new Arreglo<>(arreglo.capacidad());

        for (int i = 1; i < arreglo.longitud(); i++) {
            nuevoArreglo.insertar(arreglo.obtener(i));
        }

        this.arreglo = nuevoArreglo;

        return primero;
    }

    /**
     * Retorna el primer elemento sin retirarlo.
     */
    public T frente() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía.");
        }

        return arreglo.obtener(0);
    }

    /**
     * Indica si la cola está vacía.
     */
    public boolean estaVacia() {
        return arreglo.estaVacio();
    }

    /**
     * Indica si la cola está llena.
     */
    public boolean estaLlena() {
        return arreglo.estaLleno();
    }

    /**
     * Retorna la cantidad de elementos de la cola.
     */
    public int tamanio() {
        return arreglo.longitud();
    }
}
