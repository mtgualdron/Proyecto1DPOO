package TiendaCafeteria;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Usuarios.Usuario;
import Usuarios.Empleado;

public class TiendaDeportiva {

    //  Atributos 
    private static final double IVA = 0.19;        
    private List<Articulo> articulosDisponibles;
    private List<Venta> ventas;

    //  Constructor 
    public TiendaDeportiva() {
        this.articulosDisponibles = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }

  
    // Métodos

    // descuento por código o por puntos (no acumulables), IVA 19%
    public Venta vender(Usuario comprador, Map<Articulo, Integer> articulos,
                        String codigo, int puntosACanjear, List<Empleado> empleados) {
        if (comprador == null)
            throw new IllegalArgumentException("El comprador es obligatorio");
        if (articulos == null || articulos.isEmpty())
            throw new IllegalArgumentException("Debe incluir al menos un artículo");

        //Validar cantidades y stock, y calcular subtotal
        double subtotal = 0;
        for (Map.Entry<Articulo, Integer> item : articulos.entrySet()) {
            Articulo articulo = item.getKey();
            int cantidad = item.getValue();
            if (cantidad <= 0)
                throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
            if (!articulosDisponibles.contains(articulo))
                throw new IllegalArgumentException("El artículo no está en la tienda");
            if (articulo.getCantidadDisponible() < cantidad)
                throw new IllegalArgumentException("No hay suficiente stock de " + articulo.getNombre());
            subtotal += articulo.getPrecio() * cantidad;
        }

        //Descuento por código o por puntos 
        boolean hayCodigo = codigo != null && !codigo.isBlank();
        double descuento = 0;
        if (hayCodigo) {
            validarCanjePuntos(comprador, codigo, puntosACanjear);
            descuento = subtotal * calcularDescuento(comprador, codigo, empleados);
        } else if (puntosACanjear > 0) {
            validarCanjePuntos(comprador, codigo, puntosACanjear);
            descuento = Math.min(comprador.canjearPuntos(puntosACanjear), subtotal);
        }

        //  IVA sobre el valor ya descontado
        double iva = calcularIva(subtotal - descuento);

        //Descontar inventario
        for (Map.Entry<Articulo, Integer> item : articulos.entrySet()) {
        	item.getKey().setCantidadDisponible(item.getKey().getCantidadDisponible() - item.getValue());
        }

        // Registrar la venta (fecha y rubro: Restricción de ventas)
        Venta venta = new Venta(LocalDate.now(), "TIENDA", subtotal, descuento, iva, 0, comprador);
        ventas.add(venta);
        comprador.registrarCompra(venta);   
        return venta;
    }

    // 15% al empleado dueño del código, 8% a otros
    public double calcularDescuento(Usuario comprador, String codigo, List<Empleado> empleados) {
        for (Empleado empleado : empleados) {
            if (empleado.esCodigoEmpleado(codigo))
                return empleado.getPorcentajeDescuento(comprador);
        }
        throw new IllegalArgumentException("El código de descuento no existe");
    }

    // toda compra en la tienda paga IVA 19%
    public double calcularIva(double subtotal) {
        return subtotal * IVA;
    }

    // Agrega un artículo al catálogo 
    public void agregarArticulo(Articulo articulo) {
        if (articulo == null)
            throw new IllegalArgumentException("El artículo es obligatorio");
        for (Articulo existente : articulosDisponibles) {
            if (existente.getNombre().equalsIgnoreCase(articulo.getNombre()))
                throw new IllegalArgumentException("Ya existe un artículo con ese nombre");
        }
        articulosDisponibles.add(articulo);
    }

    // con código no se canjean puntos; sin puntos suficientes se rechaza
    public void validarCanjePuntos(Usuario comprador, String codigo, int puntos) {
        if (puntos <= 0) return;  
        boolean hayCodigo = codigo != null && !codigo.isBlank();
        if (hayCodigo)
            throw new IllegalArgumentException("El descuento por código no se acumula con puntos");
        if (!comprador.tienePuntosSuficientes(puntos))
            throw new IllegalArgumentException("No hay suficientes puntos");
    }
 // Getters 
    public List<Articulo> getArticulosDisponibles() { 
    	return new ArrayList<>(articulosDisponibles); 
    	}
    public List<Venta> getVentas() { 
    	return new ArrayList<>(ventas); 
    	}
}