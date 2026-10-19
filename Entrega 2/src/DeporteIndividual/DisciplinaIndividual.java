package DeporteIndividual;


import Excepciones.ClubException;
import Usuarios.Socio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisciplinaIndividual {

    private final String nombre;
    private final int maxInstalacionesSimultaneas;
    private final List<Instalacion> instalaciones;
    private final List<InscripcionIndividual> inscripciones;

    public DisciplinaIndividual(String nombre, int maxInstalacionesSimultaneas) throws ClubException {
        if (nombre == null || nombre.isBlank()) {
            throw new ClubException("Toda disciplina necesita un nombre, ¿cuál le ponemos?");
        }
        if (maxInstalacionesSimultaneas <= 0) {
            throw new ClubException("El máximo de instalaciones simultáneas debe ser al menos 1.");
        }
        this.nombre = nombre.trim();
        this.maxInstalacionesSimultaneas = maxInstalacionesSimultaneas;
        this.instalaciones = new ArrayList<>();
        this.inscripciones = new ArrayList<>();
    }

    public void agregarInstalacion(Instalacion i) throws ClubException {
        if (i == null) {
            throw new ClubException("No encontramos la instalación que quieres agregar.");
        }
        if (instalaciones.contains(i)) {
            throw new ClubException("La instalación " + i.getNombre() + " ya hace parte de " + nombre + ".");
        }
        instalaciones.add(i);
    }

    public boolean tieneInstalacion(Instalacion i) {
        return instalaciones.contains(i);
    }

    public boolean alcanzoMaximoSimultaneas(java.time.LocalDateTime inicio, java.time.LocalDateTime fin) {
        int ocupadas = 0;
        for (Instalacion i : instalaciones) {
            if (i.estaOcupada(inicio, fin)) {
                ocupadas++;
            }
        }
        return ocupadas >= maxInstalacionesSimultaneas;
    }

    public void registrarInscripcion(InscripcionIndividual ins) throws ClubException {
        if (ins == null || ins.getDisciplina() != this) {
            throw new ClubException("Esa inscripción no corresponde a la disciplina " + nombre + ".");
        }
        if (estaInscrito(ins.getSocio())) {
            throw new ClubException(ins.getSocio().getLogin() + " ya está inscrito en " + nombre + ".");
        }
        inscripciones.add(ins);
    }

    public boolean estaInscrito(Socio s) {
        return getInscripcion(s) != null;
    }

    public InscripcionIndividual getInscripcion(Socio s) {
        for (InscripcionIndividual i : inscripciones) {
            if (i.getSocio() == s) {
                return i;
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMaxInstalacionesSimultaneas() {
        return maxInstalacionesSimultaneas;
    }

    public List<Instalacion> getInstalaciones() {
        return Collections.unmodifiableList(instalaciones);
    }

    public List<InscripcionIndividual> getInscripciones() {
        return Collections.unmodifiableList(inscripciones);
    }
}
