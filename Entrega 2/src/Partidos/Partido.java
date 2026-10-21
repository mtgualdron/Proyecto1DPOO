package Partidos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Usuarios.Socio;
import DeporteConjunto.DeporteConjunto;

public abstract class Partido {

	// atributos
    private Date fecha;
    private int marcadorLocal;
    private int marcadorVisitante;
    private DeporteConjunto deporte;
    private List<EstadisticaPartido> estadisticas;
    private List<Socio> participantes;

    // construtctor
    public Partido(Date fecha, DeporteConjunto deporte) {
        this.fecha = fecha;
        this.deporte = deporte;
        this.marcadorLocal = 0;
        this.marcadorVisitante = 0;
        this.estadisticas = new ArrayList<EstadisticaPartido>();
        this.participantes = new ArrayList<Socio>();
    }
    
    // metodos  
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
    
    public DeporteConjunto getDeporte() { 
        return deporte; 
    }
    
    public void setDeporte(DeporteConjunto deporte) { 
        this.deporte = deporte; 
    }
    
    public List<Socio> getParticipantes() { 
        return participantes; 
    }
    
    public void agregarParticipante(Socio socio) { 
        this.participantes.add(socio); 
    }
}

    public boolean puedeJugarse() {
    	 /** Restricción de mínimo (7 fútbol, 5 baloncesto, 6 voleibol)  */
    	 for (Equipo e : getEquiposDelClub()) {
             if (!e.cumpleMinimoJugadores()) return false;
         }
         return true;
    	
    }

    public void registrarMarcador(int local, int visitante) {
    	/** marcador final de cada partido y sin empate en baloncesto ni voleibol. En voleibol el marcador son sets */
        
    	this.marcadorLocal = local;
        this.marcadorVisitante = visitante;
    }

    public boolean hayEmpate() {
    	/** puntos de liga: en fútbol el empate da 1 punto */
    	
        return this.marcadorLocal == this.marcadorVisitante;
    }

    public EstadisticaPartido obtenerEstadistica(Socio socio) {
    	 /**Devuelve null si el jugador no tiene estadística */
    	for (EstadisticaPartido estadistica : estadisticas) {
            if (estadistica.getJugador().equals(socio)) 
   
            	return estadistica;
        }
        return null;
    }

    public void agregarEstadistica(EstadisticaPartido estadistica) {
    	// pendiente descripcion
    	   if (estadistica == null)
               throw new IllegalArgumentException("La estadística es obligatoria");
           if (!participantes.contains(estadistica.getJugador()))
               throw new IllegalArgumentException("El jugador no participó en el partido");
           if (obtenerEstadistica(estadistica.getJugador()) != null)
               throw new IllegalArgumentException("El jugador ya tiene estadística en este partido");
           if (!corresponde(estadistica))
               throw new IllegalArgumentException("La estadística no corresponde al deporte");
        this.estadisticas.add(estadistica);
    }
    
    
}