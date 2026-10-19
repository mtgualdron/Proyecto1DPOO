package DeporteIndividual;

import Excepciones.ClubException;
import Usuarios.Socio;

import java.util.Arrays;
import java.util.List;


public class InscripcionIndividual {

    public static final List<String> NIVELES = Arrays.asList("PRINCIPIANTE", "INTERMEDIO", "AVANZADO");

    private final Socio socio;
    private final DisciplinaIndividual disciplina;
    private String nivel;

    public InscripcionIndividual(Socio socio, DisciplinaIndividual disciplina, String nivel) throws ClubException {
        if (socio == null || disciplina == null) {
            throw new ClubException("Para inscribirte necesitamos al socio y la disciplina.");
        }
        this.socio = socio;
        this.disciplina = disciplina;
        this.nivel = normalizar(nivel);
    }

    private static String normalizar(String nivel) throws ClubException {
        if (nivel == null || !NIVELES.contains(nivel.trim().toUpperCase())) {
            throw new ClubException("El nivel '" + nivel + "' no existe. Los niveles disponibles son: " + NIVELES + ".");
        }
        return nivel.trim().toUpperCase();
    }

    public static int eloInicialPara(String nivel) {
        int pos = NIVELES.indexOf(nivel.trim().toUpperCase());
        return pos == 0 ? 1000 : (pos == 1 ? 1250 : 1500);
    }

 
    public boolean esNivelCompatible(InscripcionIndividual otra) {
        if (otra == null || otra.disciplina != disciplina) {
            return false;
        }
        return Math.abs(NIVELES.indexOf(nivel) - NIVELES.indexOf(otra.nivel)) <= 1;
    }

       public void actualizarNivelPorElo(int elo) {
        if (elo < 1100) {
            nivel = NIVELES.get(0);
        } else if (elo < 1400) {
            nivel = NIVELES.get(1);
        } else {
            nivel = NIVELES.get(2);
        }
    }

    public Socio getSocio() {
        return socio;
    }

    public DisciplinaIndividual getDisciplina() {
        return disciplina;
    }

    public String getNivel() {
        return nivel;
    }
}
