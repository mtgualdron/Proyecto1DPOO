package Partidos;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import deporteConjunto.Equipo;
import deporteConjunto.DeporteConjunto;

public class PartidoInterno extends Partido {

	// atributos
    private List<Equipo> equiposParticipantes;

    // Constructor 
    public PartidoInterno(Date fecha, DeporteConjunto deporte, List<Equipo> equiposParticipantes) { 
    	super(fecha, deporte);
        this.equiposParticipantes = new ArrayList<Equipo>(equiposParticipantes);
    } 
    
    //metodos 

    public List<Equipo> getEquiposParticipantes() {
    	
    }

    @Override
    
    /**
  	Saber si el partido se puede jugar si: 
     Debe haber exactamente 2 equipos inscritos.
     Los equipos deben pertenecer a la misma categoría y deporte.
     Cada equipo debe cumplir con el número mínimo de jugadores habilitados 
         (7 en fútbol, 5 en baloncesto, 6 en voleibol).
     */
    public boolean puedeJugarse() {
    	if (equiposParticipantes.size() != 2) {
            return false;
        }

        Equipo equipo1 = equiposParticipantes.get(0);
        Equipo equipo2 = equiposParticipantes.get(1);

        if (!equipo1.getCategoria().equals(equipo2.getCategoria())) {
            return false;
        }

     
        for (Equipo e : equiposParticipantes) {
            if (!e.cumpleMinimoJugadores()) {
                return false;
            }
        }

        return true;
    }
}