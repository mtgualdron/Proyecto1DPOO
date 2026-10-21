package TiendaCafeteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import usuarios.Socio;
import usuarios.Administrador;
import deporteindividual.Reserva;
import deporteindividual.Instalacion;

public class Cafeteria {
	
	// Atributos 
	
    private List<Bebida> bebidas;
    private List<Snack> snacks;
    
    public static final double IMPUESTO_CONSUMO = 0.08; 
    public static final double PROPINA_SUGERIDA = 0.10;  

    	// constructor 
    public Cafeteria() {
        this.bebidas = new ArrayList<>();
        this.snacks = new ArrayList<>();
    }

    // metodos 
    
    public Venta vender(Socio comprador, List<Bebida> bebidas, List<Snack> snacks, double propina) {
        double subtotal = 0.0;

        
        if (bebidas != null) {
            for (Bebida b : bebidas) {
                if (b != null) {
                    subtotal += b.getPrecio();
                }
            }
        }

      
        if (snacks != null) {
            for (Snack s : snacks) {
                if (s != null) {
                    subtotal += s.getPrecio();
                }
            }
        }

        double impuesto = calcularImpuestoConsumo(subtotal);
        double total = subtotal + impuesto + propina;

  
        java.util.Date fechaActual = new java.util.Date();
        Venta nuevaVenta = new Venta(fechaActual, total, "Cafeteria", comprador);

        
        if (comprador != null) {
            comprador.registrarCompra(nuevaVenta);
        }

        return nuevaVenta;
    }

       
    public double calcularImpuestoConsumo(double subtotal) {
        return subtotal * IMPUESTO_CONSUMO;
    }

    public double calcularPropinaSugerida(double subtotal) {
        return subtotal * PROPINA_SUGERIDA;
    }
    
    public void agregarBebida(Bebida b) {
    	if (b != null) {
            this.bebidas.add(b);
        }    
    }

    public void agregarSnack(Snack s) {
    	if (s != null) {
            this.snacks.add(s);
        }
    }

    public List<String> informarAlergenos(List<Snack> snacksAComprar) {
    	List<String> reporteAlergenos = new ArrayList<>();
        
        if (snacksAComprar != null) {
            for (Snack s : snacksAComprar) {
                if (s != null && s.tieneAlergenos()) {
                    reporteAlergenos.add("El snack " + s.getNombre() + " contiene alérgenos: " + s.getAlergenos());
                }
            }
        }
        
        return reporteAlergenos;
    }   
    
    public boolean estaEnInstalacionSinBebidaCaliente( Socio comprador, List<Bebida> bebidasAComprar) {
    	if (comprador == null) {
            return false;
        }
        if (comprador.isBebidaCalienteActiva()) {
            return false; 
        }

        boolean incluyeBebidaCaliente = false;
        if (bebidasAComprar != null) {
            for (Bebida b : bebidasAComprar) {
                if (b != null && b.isCaliente()) { 
                    incluyeBebidaCaliente = true;
                    break;
                }
            }
        }
        if (incluyeBebidaCaliente) {
  
            for (Reserva r : comprador.getReservas()) { 
                if (r != null && r.getInstalacion() != null) {
                    String nombreInstalacion = r.getInstalacion().getNombre().toLowerCase();
                   
                    if (nombreInstalacion.contains("padel") || nombreInstalacion.contains("tenis de mesa")) {
                    	return false; 
                    }
                }
            }
        }

        return true; 
    }
}