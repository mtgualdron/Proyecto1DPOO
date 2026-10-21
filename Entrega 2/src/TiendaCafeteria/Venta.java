package TiendaCafeteria;

import java.util.Date;
import Usuarios.Usuario;

public class Venta {
    private Date fecha;
    private String rubro;
    private double impuesto;
    private double propina;
    private double valor;
    private double descuento;
    private Usuario comprador;

    public Venta(Date fecha, String rubro, double valor, double impuesto, double propina, double descuento, Usuario comprador) {
        this.fecha = fecha;
        this.rubro = rubro;
        this.valor = valor;
        this.impuesto = impuesto;
        this.propina = propina;
        this.descuento = descuento;
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

    public Date getFecha() { 
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

