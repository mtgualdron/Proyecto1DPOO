package DeporteConjunto;

import Excepciones.ClubException;

public class Categoria {
	

    public static final Categoria SUB_13 = predefinida("Sub-13", 0, 12);
    public static final Categoria SUB_15 = predefinida("Sub-15", 13, 14);
    public static final Categoria SUB_17 = predefinida("Sub-17", 15, 16);
    public static final Categoria MAYORES = predefinida("Mayores", 17, 120);

    private final String nombre;
    private final int edadMinima;
    private final int edadMaxima;

    public Categoria(String nombre, int edadMinima, int edadMaxima) throws ClubException {
        if (nombre == null || nombre.isBlank()) {
            throw new ClubException("Toda categoría necesita un nombre.");
        }
        if (edadMinima < 0 || edadMaxima < edadMinima) {
            throw new ClubException("El rango de edad de la categoría " + nombre + " no es válido.");
        }
        this.nombre = nombre.trim();
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
    }

    private static Categoria predefinida(String nombre, int min, int max) {
        try {
            return new Categoria(nombre, min, max);
        } catch (ClubException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public boolean cumpleEdad(int edad) {
        return edad >= edadMinima && edad <= edadMaxima;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public int getEdadMaxima() {
        return edadMaxima;
    }

    @Override
    public String toString() {
        return nombre;
    }
}



