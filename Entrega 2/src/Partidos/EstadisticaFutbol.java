package Partidos;

import Usuarios.Socio;
public class EstadisticaFutbol extends EstadisticaPartido {
	    private int goles;
	    private int asistencias;
	    private int tarjetasAmarillas;
	    private boolean tarjetasRojas;

	    public EstadisticaFutbol(Socio socio, int goles, int asistencias, int tarjetasAmarillas, boolean tarjetasRojas) {
	        super(socio); 
	        this.goles = goles;
	        this.asistencias = asistencias;
	        this.tarjetasAmarillas = tarjetasAmarillas;
	        this.tarjetasRojas = tarjetasRojas;
	    }

	    public void registrarAmarilla() {
	        this.tarjetasAmarillas++;
	    }

	    public void registrarRoja() {
	        this.tarjetasRojas = true;
	    }

	    public boolean generaSuspension() {
	        return this.tarjetasAmarillas >= 2 || this.tarjetasRojas;
	    }

	    public int getGoles() {
	        return goles;
	    }

	    public void setGoles(int goles) {
	        this.goles = goles;
	    }

	    public int getAsistencias() {
	        return asistencias;
	    }

	    public void setAsistencias(int asistencias) {
	        this.asistencias = asistencias;
	    }

	    public int getTarjetasAmarillas() {
	        return tarjetasAmarillas;
	    }

	    public void setTarjetasAmarillas(int tarjetasAmarillas) {
	        this.tarjetasAmarillas = tarjetasAmarillas;
	    }

	    public boolean isTarjetasRojas() {
	        return tarjetasRojas;
	    }

	    public void setTarjetasRojas(boolean tarjetasRojas) {
	        this.tarjetasRojas = tarjetasRojas;
	    }
}

