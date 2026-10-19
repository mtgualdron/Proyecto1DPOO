package Usuarios;

import Excepciones.ClubException;

public abstract class Usuario {
	
	private String login;
	private String contraseña;
	private int puntosFidelidad;
	
	
	public Usuario(String login, String contraseña) {
		super();
		this.login = login;
		this.contraseña = contraseña;
		this.puntosFidelidad = 0;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		if (contraseña == null || contraseña.trim().isEmpty()) {

            throw new ClubException(" :( la contraseña no puede estar vacía.");
        }

        this.contraseña = contraseña;
	}
	


	public void setLogin(String login) {
		if (login == null || login.trim().isEmpty()) {

            throw new ClubException(" :( el login no puede estar vacío.");
        }

        this.login = login;
	}


	public String getLogin() {
		return login;
	}

	public int getPuntosFidelidad() {
		return puntosFidelidad;
	}
	
	public void setPuntosFidelidad(int puntosFidelidad) {
		if (puntosFidelidad <0) {
			throw new ClubException (" no puedes tener menos de 0 puntos");
		}
		this.puntosFidelidad = puntosFidelidad;
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
