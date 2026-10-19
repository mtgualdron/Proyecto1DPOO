package Partidos;

import Usuarios.Socio; 

public class EstadisticaBaloncesto extends EstadisticaPartido {
   
    private int puntos;
    private int rebotes;

    public EstadisticaBaloncesto(Socio socio, int puntos, int rebotes) {
        super(socio); 
        this.puntos = puntos;
        this.rebotes = rebotes;
    }

    public void sumarPuntos(int cantidad) {
        if (cantidad > 0) {
            this.puntos += cantidad;
        }
    }

    public void registrarRebote() {
        this.rebotes++;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getRebotes() {
        return rebotes;
    }

    public void setRebotes(int rebotes) {
        this.rebotes = rebotes;
    }
}
