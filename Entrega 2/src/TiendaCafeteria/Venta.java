package TiendaCafeteria;

import java.util.Date;

public class Venta {
    private Date fecha;
    private String rubro;
    private double impuesto;
    private double propina;
    private double valor;
    private double descuento;

    public Venta(Date fecha, String rubro, double valor, double impuesto, double propina, double descuento) {
        this.fecha = fecha;
        this.rubro = rubro;
        this.valor = valor;
        this.impuesto = impuesto;
        this.propina = propina;
        this.descuento = descuento;
    }

    public double calcularTotal() {
        return (this.valor + this.impuesto + this.propina) - this.descuento;
    }

    public int calcularPuntosFidelidad(double valorTotal) {
        return (int) (valorTotal * 0.02); 
    }

    public boolean esRubro(String rubroBuscado) {
        return this.rubro.equalsIgnoreCase(rubroBuscado);
    }

    public Date getFecha() { 
    	return fecha; 
    	}
    public String getRubro() { 
    	return rubro; 
    	}
    public double getValor() { 
    	return valor; 
    	}
}
