package Usuarios;

import java.util.List;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import Excepciones.ClubException;

public class Entrenador extends Empleado {
	
	private final List<SesionEntrenamiento> sesiones;

	public Entrenador(String login, String contraseña, String codigoDescuento) throws ClubException {
		super(login, contraseña, codigoDescuento);
		this.sesiones = new ArrayList<>();
		
	}
	public SesionEntrenamiento programarSesion(LocalDate fecha, LocalTime hora, int duracion, Instalacion insta)
            throws ClubException {
        SesionEntrenamiento sesion = new SesionEntrenamiento(fecha, hora, duracion, this, insta);
        if (!sesion.disponibleIns(sesion.getInicio(), sesion.getFin())) {
            throw new ClubException("La instalación " + insta.getNombre()
                    + " ya está ocupada en ese horario. ¿Probamos con otra hora u otra instalación?");
        }
        for (SesionEntrenamiento otra : sesiones) {
            if (otra.getInicio().isBefore(sesion.getFin()) && sesion.getInicio().isBefore(otra.getFin())) {
                throw new ClubException("Ya tienes otra sesión programada que se cruza con ese horario.");
            }
        }
        insta.bloquear(sesion.getInicio(), sesion.getFin());
        sesiones.add(sesion);
        return sesion;
    }

    public Asistencia registrarAsistencia(SesionEntrenamiento sesion, Socio jugador, String estado)
            throws ClubException {
        verificarSesionPropia(sesion);
        return sesion.registrarAsistencia(jugador, estado);
    }

    public Asistencia registrarEvaluacion(SesionEntrenamiento sesion, Socio jugador, int nota, String comentario)
            throws ClubException {
        verificarSesionPropia(sesion);
        Asistencia asistencia = sesion.getAsistencia(jugador);
        if (asistencia == null) {
            throw new ClubException("Primero registra la asistencia de " + nombreDe(jugador)
                    + " para poder evaluar su desempeño.");
        }
        asistencia.asignarEvaluacion(nota, comentario);
        return asistencia;
    }

    public void registrarTarjeta(Partido p, Socio jugador, String tipo) throws ClubException {
        if (p == null || jugador == null) {
            throw new ClubException("Necesitamos el partido y el jugador para registrar la tarjeta.");
        }
        if (!DeporteConjunto.FUTBOL.equalsIgnoreCase(p.getDeporte().getNombre())) {
            throw new ClubException("Las tarjetas solo se registran en partidos de fútbol.");
        }
        if (tipo == null) {
            throw new ClubException("Indica si la tarjeta es AMARILLA o ROJA.");
        }
        EstadisticaPartido e = p.getEstadistica(jugador);
        EstadisticaFutbol futbol;
        if (e == null) {
            futbol = new EstadisticaFutbol(jugador);
            p.registrarEstadistica(futbol);
        } else {
            futbol = (EstadisticaFutbol) e;
        }
        String t = tipo.trim().toUpperCase();
        if (t.equals("AMARILLA")) {
            futbol.registrarAmarilla();
        } else if (t.equals("ROJA")) {
            futbol.registrarRoja();
        } else {
            throw new ClubException("El tipo de tarjeta debe ser AMARILLA o ROJA.");
        }
    }

    private void verificarSesionPropia(SesionEntrenamiento sesion) throws ClubException {
        if (sesion == null || !sesiones.contains(sesion)) {
            throw new ClubException("Esa sesión no está entre tus sesiones programadas.");
        }
    }

    private String nombreDe(Socio s) {
        return s == null ? "el jugador" : s.getLogin();
    }

    public List<SesionEntrenamiento> getSesiones() {
        return Collections.unmodifiableList(sesiones);
    }
}
	


