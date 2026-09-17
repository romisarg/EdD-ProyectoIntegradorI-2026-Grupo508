package ed.tda;

/**
 * TDA Arreglo Genérico de tamaño fijo.
 * @param <T> Tipo de dato almacenado en el arreglo.
 */
public class Arreglo<T> {
    private Object[] elementos;
    private int cantidad;
    private int capacidadMax;

    public Arreglo(int capacidadMax) {
        if (capacidadMax <= 0) {
            throw new IllegalArgumentException("La capacidad máxima debe ser mayor a cero.");
        }
        this.capacidadMax = capacidadMax;
        this.elementos = new Object[capacidadMax];
        this.cantidad = 0;
    }

    public void insertar(T elemento) {
        if (cantidad >= capacidadMax) {
            throw new IllegalStateException("El arreglo está lleno.");
        }
        elementos[cantidad] = elemento;
        cantidad++;
    }

    @SuppressWarnings("unchecked")
    public T obtener(int indice) {
        if (indice < 0 || indice >= cantidad) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        return (T) elementos[indice];
    }

    @SuppressWarnings("unchecked")
    public void modificar(int indice, T elemento) {
        if (indice < 0 || indice >= cantidad) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        elementos[indice] = elemento;
    }

    public int longitud() {
        return cantidad;
    }

    public int capacidad() {
        return capacidadMax;
    }

    public boolean estaVacio() {
        return cantidad == 0;
    }

    public boolean estaLleno() {
        return cantidad == capacidadMax;
    }

    public void intercambiar(int i, int j) {
        if (i < 0 || i >= cantidad || j < 0 || j >= cantidad) {
            throw new IndexOutOfBoundsException("Índices inválidos para intercambio.");
        }
        Object temp = elementos[i];
        elementos[i] = elementos[j];
        elementos[j] = temp;
    }
}
