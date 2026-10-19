package Excepciones;

public class ClubException extends RuntimeException {
 
    private static final long serialVersionUID = 1L;
 
    public ClubException(String mensaje) {
        super(mensaje);
    }

}
