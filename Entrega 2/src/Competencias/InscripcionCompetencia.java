package Competencias;
import java.util.List;

import Usuarios.Socio;


public class InscripcionCompetencia {
	private List<Socio> nomina;

	public InscripcionCompetencia(List<Socio> nomina) {
		this.nomina = nomina;
	}
	
	public List<Socio> getNomina() {
		return nomina;
	}

	public boolean estaEnNomina(Socio jugador) {
		if (this.nomina.contains(jugador)) {
			return true;
		}
		return false;
	}
	
	
}