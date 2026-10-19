package TiendaCafeteria;

import java.util.ArrayList;
import java.util.List;

public class Snack {
    private String nombre;
    private double precio;
    private List<String> alergenos;

    public Snack(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.alergenos = new ArrayList<>();
    }

    public void agregarAlergeno(String alergeno) {
        this.alergenos.add(alergeno);
    }

    public boolean tieneAlergenos() {
        return !this.alergenos.isEmpty();
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public List<String> getAlergenos() { return alergenos; }
}
