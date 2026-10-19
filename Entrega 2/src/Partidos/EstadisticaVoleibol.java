package Partidos;

import Usuarios.Socio; 

public class EstadisticaVoleibol extends EstadisticaPartido {
   
    private int aces;
    private int bloques;

    public EstadisticaVoleibol(Socio socio, int aces, int bloques) {
        super(socio); 
        this.aces = aces;
        this.bloques = bloques;
    }

    public void registrarAce() {
        this.aces++;
    }

    public void registrarBloque() {
        this.bloques++;
    }

   
    public int getAces() {
        return aces;
    }

    public void setAces(int aces) {
        this.aces = aces;
    }

    public int getBloques() {
        return bloques;
    }

    public void setBloques(int bloques) {
        this.bloques = bloques;
    }
}
