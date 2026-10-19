package Partidos;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import DeporteConjunto.Equipo;
import DeporteConjunto.DeporteConjunto;

public class PartidoInterno extends Partido {

    private List<Equipo> equiposParticipantes;

    public PartidoInterno(Date fecha, DeporteConjunto deporte,
                          List<Equipo> equiposParticipantes) super(fecha, deporte);
        this.equiposParticipantes = new ArrayList<Equipo>(equiposParticipantes);
    }

    public List<Equipo> getEquiposParticipantes() {
        return new ArrayList<Equipo>(equiposParticipantes);
    }

    public void agregarEquipo(Equipo equipo) {
        this.equiposParticipantes.add(equipo);
    }

    public void eliminarEquipo(Equipo equipo) {
        this.equiposParticipantes.remove(equipo);
    }

    @Override
    public boolean puedeJugarse() {
    	//pediente
        return false;
    }
}