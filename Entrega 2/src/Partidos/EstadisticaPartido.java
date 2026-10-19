package Partidos;

import Usuarios.Socio;

public abstract class EstadisticaPartido {
	protected Socio socio; 

    public EstadisticaPartido(Socio socio) {
        this.socio = socio;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }
}

