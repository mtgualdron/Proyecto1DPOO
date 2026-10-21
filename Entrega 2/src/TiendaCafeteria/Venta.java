package TiendaCafeteria;

import java.time.LocalDate;
import Usuarios.Usuario;

public class Venta {
    private LocalDate fecha;
    private String rubro;
    private double impuesto;
    private double propina;
    private double valor;
    private double descuento;
    private Usuario comprador;

    	// constructor
    public Venta(LocalDate fecha, String rubro, double valor, double descuento, double impuesto, double propina, Usuario comprador) {
        this.fecha = fecha;
        this.rubro = rubro;
        this.valor = valor;
        this.descuento = descuento;
        this.impuesto = impuesto;
        this.propina = propina;
        this.comprador = comprador;
    }
    
    

    public double calcularTotal() {
        return (this.valor + this.impuesto + this.propina) - this.descuento;
    }

    public int calcularPuntosFidelidad(double valorTotal) {
        return (int) (valorTotal * 0.02); 
    }

    public boolean esRubro(String rubroBuscado) {
    	if (rubroBuscado == null || this.rubro == null) {
        return false;
    }
    return this.rubro.equalsIgnoreCase(rubroBuscado);
}
    
    // getters y setters 

    public LocalDate getFecha() { 
    	return fecha; 
    	}
    public String getRubro() { 
    	return rubro; 
    	}
    public double getValor() { 
    	return valor; 
    	}
    public Usuario getComprador() {
    	return comprador; 
    	}
    public void setComprador(Usuario comprador) {
    	this.comprador = comprador; 
    	}
}

