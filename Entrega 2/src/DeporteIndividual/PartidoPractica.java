package DeporteIndividual;


import Excepciones.ClubException;
import Usuarios.Socio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartidoPractica {

    private final List<Socio> jugadores;
    private final DisciplinaIndividual disciplina;
    private final LocalDate fecha;
    private final List<EstadisticaDisciplina> estadisticas;
    private boolean jugado;

    public PartidoPractica(DisciplinaIndividual disciplina, Socio a, Socio b, LocalDate fecha) throws ClubException {
        if (disciplina == null || a == null || b == null) {
            throw new ClubException("Para programar una práctica necesitamos la disciplina y a los dos jugadores.");
        }
        if (fecha == null) {
            throw new ClubException("Indica la fecha de la práctica.");
        }
        if (a == b) {
            throw new ClubException("Para una práctica necesitas un compañero distinto de ti. ¡Invita a alguien!");
        }
        this.disciplina = disciplina;
        this.fecha = fecha;
        this.jugadores = new ArrayList<>();
        this.jugadores.add(a);
        this.jugadores.add(b);
        this.estadisticas = new ArrayList<>();
        validar();
    }

    private void validar() throws ClubException {
        Socio a = jugadores.get(0);
        Socio b = jugadores.get(1);
        InscripcionIndividual ia = disciplina.getInscripcion(a);
        InscripcionIndividual ib = disciplina.getInscripcion(b);
        if (ia == null) {
            throw new ClubException(a.getLogin() + " aún no está inscrito en " + disciplina.getNombre() + ".");
        }
        if (ib == null) {
            throw new ClubException(b.getLogin() + " aún no está inscrito en " + disciplina.getNombre() + ".");
        }
        if (!ia.esNivelCompatible(ib)) {
            throw new ClubException("¡Qué pena! " + a.getLogin() + " (" + ia.getNivel() + ") y " + b.getLogin()
                    + " (" + ib.getNivel() + ") no son de niveles compatibles. "
                    + "Las prácticas son entre el mismo nivel o niveles contiguos.");
        }
    }

    public boolean puedeJugarse() {
        try {
            validar();
            return true;
        } catch (ClubException e) {
            return false;
        }
    }

    public void agregarEstadistica(EstadisticaDisciplina e) throws ClubException {
        if (e == null) {
            throw new ClubException("No encontramos la estadística que quieres registrar.");
        }
        if (!jugadores.contains(e.getJugador())) {
            throw new ClubException(e.getJugador().getLogin() + " no participó en este partido de práctica.");
        }
        for (EstadisticaDisciplina otra : estadisticas) {
            if (otra.getJugador() == e.getJugador()) {
                throw new ClubException(e.getJugador().getLogin() + " ya tiene estadísticas registradas en este partido.");
            }
        }
        estadisticas.add(e);
    }

   
    public void actualizarRanking(Socio ganador) throws ClubException {
        if (jugado) {
            throw new ClubException("El resultado de este partido ya fue registrado, ¡no se puede repetir!");
        }
        if (ganador == null || !jugadores.contains(ganador)) {
            throw new ClubException("El ganador debe ser uno de los dos jugadores del partido.");
        }
        Socio perdedor = jugadores.get(0) == ganador ? jugadores.get(1) : jugadores.get(0);
        ganador.obtenerRanking(disciplina).actualizar(true);
        perdedor.obtenerRanking(disciplina).actualizar(false);
        jugado = true;
    }

    public List<Socio> getJugadores() {
        return Collections.unmodifiableList(jugadores);
    }

    public DisciplinaIndividual getDisciplina() {
        return disciplina;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public List<EstadisticaDisciplina> getEstadisticas() {
        return Collections.unmodifiableList(estadisticas);
    }

    public boolean isJugado() {
        return jugado;
    }
}
