package Partidos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Usuarios.Socio;
import DeporteConjunto.DeporteConjunto;

public abstract class Partido {

    private Date fecha;
    private int marcadorLocal;
    private int marcadorVisitante;
    private DeporteConjunto deporte;
    private List<EstadisticaPartido> estadisticas;
    private List<Socio> participantes;

    public Partido(Date fecha, DeporteConjunto deporte) {
        this.fecha = fecha;
        this.deporte = deporte;
        this.marcadorLocal = 0;
        this.marcadorVisitante = 0;
        this.estadisticas = new ArrayList<EstadisticaPartido>();
        this.participantes = new ArrayList<Socio>();
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getMarcadorLocal() {
        return marcadorLocal;
    }

    public int getMarcadorVisitante() {
        return marcadorVisitante;
    }

    public boolean puedeJugarse() {
        return false;
    }

    public void registrarMarcador(int local, int visitante) {
        this.marcadorLocal = local;
        this.marcadorVisitante = visitante;
    }

    public boolean hayEmpate() {
        return this.marcadorLocal == this.marcadorVisitante;
    }

    public EstadisticaPartido getEstadistica(Socio socio) {
        return null;
    }

    public void agregarEstadistica(EstadisticaPartido estadistica) {
        this.estadisticas.add(estadistica);
    }
    
    
}