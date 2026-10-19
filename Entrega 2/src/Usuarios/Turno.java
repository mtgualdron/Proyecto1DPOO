package Usuarios;

import Excepciones.ClubException;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Turno{
	
	private DayOfWeek dia;
	private LocalTime hora;
	private final Empleado empleado;
    private Turno turnoNuevo;
    private EstadoSolicitud estado;
    
	public Turno(Empleado empleado,DayOfWeek dia, LocalTime hora) throws ClubException {
		if (empleado == null) {
            throw new ClubException("Todo turno necesita un empleado asignado.");
        }
        if (dia == null || hora == null) {
            throw new ClubException("Para crear un turno indica el día y la hora.");
        }
		this.dia = dia;
		this.hora = hora;
		this.empleado = empleado;
	}
	
    public boolean coincideCon(DayOfWeek otroDia, LocalTime otraHora) {
        return dia == otroDia && hora.equals(otraHora);
    }
	
    
    public void solicitarCambio(Turno nuevo) throws ClubException {
    	if (estaPendiente()) {
        throw new ClubException("Este turno ya tiene una solicitud de cambio esperando respuesta. ¡Un poquito de paciencia!");
    }
    	if (nuevo == null) {
        throw new ClubException("Indica el turno al que te gustaría cambiarte.");
    }
    	if (nuevo.getEmpleado() != empleado) {
        throw new ClubException("El turno nuevo debe pertenecer al mismo empleado que lo solicita.");
    }
    	if (nuevo.coincideCon(dia, hora)) {
        throw new ClubException("El turno nuevo es igual al actual, así que no hay nada que cambiar.");
    }
    	this.turnoNuevo = nuevo;
    	this.estado = EstadoSolicitud.PENDIENTE;
}

    public boolean estaPendiente() {
    return estado == EstadoSolicitud.PENDIENTE;
}

    public void aprobarTurno() throws ClubException {
    if (!estaPendiente()) {
        throw new ClubException("Este turno no tiene ninguna solicitud de cambio pendiente por aprobar.");
    }
    reprogramar(turnoNuevo.getDia(), turnoNuevo.getHora());
}

    public void rechazarTurno() throws ClubException {
    if (!estaPendiente()) {
        throw new ClubException("Este turno no tiene ninguna solicitud de cambio pendiente por rechazar.");
    }
    this.turnoNuevo = null;
    this.estado = EstadoSolicitud.RECHAZADO;
}

    void reprogramar(DayOfWeek nuevoDia, LocalTime nuevaHora) {
    	this.dia = nuevoDia;
    	this.hora = nuevaHora;
    	this.turnoNuevo = null;
    	this.estado = EstadoSolicitud.APROBADO;
}
    public DayOfWeek getDia() {
		return dia;
	}

	public void setDia(DayOfWeek dia) {
		this.dia = dia;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Turno getTurnoNuevo() {
		return turnoNuevo;
	}

	public void setTurnoNuevo(Turno turnoNuevo) {
		this.turnoNuevo = turnoNuevo;
	}

	public EstadoSolicitud getEstado() {
		return estado;
	}

	public void setEstado(EstadoSolicitud estado) {
		this.estado = estado;
	}

	public Empleado getEmpleado() {
		return empleado;
	}
}


