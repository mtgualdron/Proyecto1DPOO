package DeporteIndividual;


import Excepciones.ClubException;
import Usuarios.Socio;

import java.time.LocalDateTime;

public class Reserva {

    private final LocalDateTime inicio;
    private final LocalDateTime fin;
    private final int numeroJugadores;
    private final String modalidad;
    private final Socio socio;
    private final Instalacion instalacion;

    public Reserva(LocalDateTime inicio, LocalDateTime fin, int numeroJugadores, String modalidad,
                   Socio socio, Instalacion instalacion) throws ClubException {
        if (inicio == null || fin == null || !inicio.isBefore(fin)) {
            throw new ClubException("El horario de la reserva no es válido: el inicio debe ser antes del fin.");
        }
        if (numeroJugadores <= 0) {
            throw new ClubException("Una reserva necesita al menos un jugador.");
        }
        if (modalidad == null || modalidad.isBlank()) {
            throw new ClubException("Indica la modalidad de juego (SENCILLOS o DOBLES).");
        }
        if (socio == null || instalacion == null) {
            throw new ClubException("Para reservar necesitamos al socio y la instalación.");
        }
        this.inicio = inicio;
        this.fin = fin;
        this.numeroJugadores = numeroJugadores;
        this.modalidad = modalidad.trim().toUpperCase();
        this.socio = socio;
        this.instalacion = instalacion;
    }

    public boolean coincideCon(LocalDateTime otroInicio, LocalDateTime otroFin) {
        return inicio.isBefore(otroFin) && otroInicio.isBefore(fin);
    }

    public boolean estaActivaEn(LocalDateTime momento) {
        return momento != null && !momento.isBefore(inicio) && momento.isBefore(fin);
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public int getNumeroJugadores() {
        return numeroJugadores;
    }

    public String getModalidad() {
        return modalidad;
    }

    public Socio getSocio() {
        return socio;
    }

    public Instalacion getInstalacion() {
        return instalacion;
    }
}
