package Usuarios;

import Excepciones.ClubException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fisioterapeuta extends Empleado {
	
	private final List<String> registros;

	public Fisioterapeuta(String login, String contraseña, String codigoDescuento)
			throws ClubException {
		super(login, contraseña, codigoDescuento);
		this.registros = new ArrayList <>();
	}
	public void registrarEvaluacionFisica (Socio j, String descripcion, boolean apto) {
		if (j == null) {
            throw new ClubException("Necesito saber a qué jugador pertenece la evaluación física.");
        }
        if (descripcion == null || descripcion.isEmpty()) {
            throw new ClubException(" la descripción no puede quedar vacía.");
        }
        j.setFisicoApto(apto);
        registros.add(j.getLogin() + "|" + LocalDate.now() + "|" + (apto ? "APTO" : "NO APTO") + "|"
                + descripcion.trim().replace("|", "/"));
    }
	public List<String> consultarHistorialMedico(Socio s) throws ClubException {
        if (s == null) {
            throw new ClubException("Indica el jugador del que quieres ver el historial médico.");
        }
        List<String> historial = new ArrayList<>();
        for (String r : registros) {
            String[] partes = r.split("\\|", 4);
            if (partes[0].equals(s.getLogin())) {
                historial.add(partes[1] + " - " + partes[2] + ": " + partes[3]);
            }
        }
        return historial;
    }

    public List<String> getRegistros() {
        return Collections.unmodifiableList(registros);
    }
}
	
	
	
	

