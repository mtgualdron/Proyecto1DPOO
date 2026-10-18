package ClubDeportivo;

public abstract class usuario {
	
	private String login;
	private String contraseña;
	private int puntosFidelidad;
	
	
	public usuario(String login, String contraseña) {
		super();
		this.login = login;
		this.contraseña = contraseña;
		this.puntosFidelidad = 0;
	}


	public String getLogin() {
		return login;
	}

	public int getPuntosFidelidad() {
		return puntosFidelidad;
	}
	
	public boolean verificar (String login, String contraseña) {
		return this.login.equals(login) && this.contraseña.equals(contraseña);
	}
	
	public void acumularPuntos (double valorVenta) {
		if (valorVenta >0) {
			puntosFidelidad += valorVenta;
		}
	}
	public boolean tienePuntosSuficientes (int puntos) {
		if (puntos > 0 && puntosFidelidad >= puntos) {
			return true;
		}
		return false;
	}
	
	public int canjearPuntos (int puntos) {
		if (tienePuntosSuficientes(puntos)) {
			puntosFidelidad -= puntos;
			return puntos;
		}
		return 0;
	}


	
	
	

}
