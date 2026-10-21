package TiendaCafeteria;

import java.util.ArrayList;
import java.util.List;

public class Snack {
	
	// atributos
    private String nombre;
    private double precio;
    private List<String> alergenos;
    
    //constructor
    public Snack(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.alergenos = new ArrayList<>();
    }
    //metodos
    public boolean tieneAlergenos() {
    	return alergenos != null && !alergenos.isEmpty();
    }
    
    public void agregarAlergeno(String alergeno) {
    	if (alergeno != null && !alergeno.trim().isEmpty()) {
            this.alergenos.add(alergeno);
    	}
    }
    
    // metodos getters y setters 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<String> getAlergenos() {
        return alergenos;
    }

    public void setAlergenos(List<String> alergenos) {
        this.alergenos = alergenos;
    }
}

