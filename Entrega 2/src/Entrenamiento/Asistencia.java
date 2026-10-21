package Entrenamiento;

import Excepciones.ClubException;
import Usuarios.Socio;

public class Asistencia {
	
	public static final String asistio = "asistio";
    public static final String fallo = "fallo";
    public static final int sin_nota = -1;

    private String estado;
    private final Socio socio;
    private int nota;
    private String comentario;

    public Asistencia(Socio socio, String estado) throws ClubException {
        if (socio == null) {
            throw new ClubException("Para registrar la asistencia necesitamos al jugador.");
        }
        this.socio = socio;
        this.nota = sin_nota;
        this.comentario = "";
        setEstado(estado);
    }

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) throws ClubException {
        if (estado == null) {
            throw new ClubException("Indica si el jugador ASISTIO o FALLO.");
        }
        String est = estado.trim().toLowerCase();
        if (!est.equals(asistio) && !est.equals(fallo)) {
            throw new ClubException("El estado '" + estado + "' no es válido. Usa asistio o fallo.");
        }
        this.estado = est;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Socio getSocio() {
		return socio;
	}
	

    public boolean esNotaValida(int nota) {
        return nota >= 0 && nota <= 10;
    }

    public boolean tieneEvaluacion() {
        return nota != sin_nota;
    }
	
	public void asignarEvaluacion(int nota, String comentario) throws ClubException {
        if (!asistio.equals(estado)) {
            throw new ClubException(socio.getLogin() + " no asistió a la sesión, así que no podemos evaluar su desempeño.");
        }
        if (!esNotaValida(nota)) {
            throw new ClubException("La nota debe estar entre 0 y 10. Recibimos " + nota + ".");
        }
        if (comentario == null || comentario.isBlank()) {
            throw new ClubException("Déjale un comentario al jugador junto con su nota, ¡le servirá para mejorar!");
        }
        this.nota = nota;
        this.comentario = comentario.trim();
    }

    

}
