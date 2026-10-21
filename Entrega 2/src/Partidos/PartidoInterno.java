package Partidos;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import DeporteConjunto.Equipo;
import DeporteConjunto.DeporteConjunto;

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
    	return this.equiposParticipantes;
    }

}