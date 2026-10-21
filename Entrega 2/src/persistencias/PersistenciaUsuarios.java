package persistencias;
import club.Club;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;

import Usuarios.*;
import Excepciones.*;

public class PersistenciaUsuarios {
    private static final String ARCHIVO = "usuarios.txt";
    private static final String SEPARADOR = ";";

    public PersistenciaUsuarios() {
    }

    public void cargarUsuarios(Club seneca, String carpeta) throws IOException, PersistenciaException{
        String ruta = carpeta + "/" + ARCHIVO;
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))){
            String linea = "";
            while ((linea = reader.readLine()) != null){
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")){
                    continue;
                }
                String[] partes = linea.split(SEPARADOR);
                String tipoUsuario = partes[0];
                String login = partes[1];
                String password = partes[2];
                if (tipoUsuario.equals("SOCIO")){
                    int edad = Integer.parseInt(partes[3]);
                    boolean aptoFisico = Boolean.parseBoolean(partes[4]);
                    int puntosFidelidad = Integer.parseInt(partes[5]);

                    Socio nuevoSocio = new Socio(login, password, edad);
                    seneca.agregarUsuario(nuevoSocio);
                }
                else if (tipoUsuario.equals("ENTRENADOR")){
                    String codigoDescuento = partes[3];
                    Entrenador nuevoEntrenador = new Entrenador(login, password, codigoDescuento);
                    seneca.agregarUsuario(nuevoEntrenador);
                }
                else if (tipoUsuario.equals("FISIOTERAPEUTA")){
                    String codigoDescuento = partes[3];
                    Fisioterapeuta nuevoFisio = new Fisioterapeuta(login, password, codigoDescuento);
                    seneca.agregarUsuario(nuevoFisio);
                }
                else if (tipoUsuario.equals("ADMIN")){
                    Administrador nuevoAdmin = new Administrador(login, password);
                    seneca.agregarUsuario(nuevoAdmin);
                }
                else{
                    throw new PersistenciaException("No se pudo leer el archivo");
                }
            }
        }
    }

    

}
