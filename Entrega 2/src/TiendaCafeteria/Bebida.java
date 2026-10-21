package TiendaCafeteria;

public class Bebida {
	// atributos
    private String nombre;
    private double precio;
    private boolean caliente;
    
    //constructor
    public Bebida(String nombre, double precio, boolean caliente) {
        this.nombre = nombre;
        this.precio = precio;
        this.caliente = caliente;
    }
    // metodos getters y setters 
    public boolean isCaliente() {
        return this.caliente;
    }
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

    public void setCaliente(boolean caliente) {
        this.caliente = caliente;
    }
}

