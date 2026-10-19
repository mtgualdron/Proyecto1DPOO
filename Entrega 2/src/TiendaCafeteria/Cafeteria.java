package TiendaCafeteria;

import Usuarios.Usuario; 
import java.util.ArrayList;
import java.util.List;

public class Cafeteria {
    private List<Bebida> bebidas;
    private List<Snack> snacks;
    
    public static final double IMPUESTO_CONSUMO = 0.08; 
    public static final double PROPINA_SUGERIDA = 0.10;  

    public Cafeteria() {
        this.bebidas = new ArrayList<>();
        this.snacks = new ArrayList<>();
    }

    public void agregarBebida(Bebida b) {
        this.bebidas.add(b);
    }

    public void agregarSnack(Snack s) {
        this.snacks.add(s);
    }

    public List<String> informarAlergenos(List<Snack> snacksAComprar) {
        List<String> reporteAlergenos = new ArrayList<>();
        for (Snack s : snacksAComprar) {
            if (s.tieneAlergenos()) {
                reporteAlergenos.add("El snack " + s.getNombre() + " contiene alérgenos: " + s.getAlergenos());
            }
        }
        return reporteAlergenos;
    }

    public double calcularImpuestoConsumo(double subtotal) {
        return subtotal * IMPUESTO_CONSUMO;
    }

    public double calcularPropinaSugerida(double subtotal) {
        return subtotal * PROPINA_SUGERIDA;
    }
    

}