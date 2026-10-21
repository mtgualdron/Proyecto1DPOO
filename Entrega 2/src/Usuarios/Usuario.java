package Usuarios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Excepciones.ClubException;
import Tienda.Venta;

public abstract class Usuario {
	
	public static final int pesos_punto = 1000;
	public static final double valor_punto = 50.0;

	private String login;
	private String contraseña;
	private int puntosFidelidad;
	private List<Venta> historialdecompras;
	
	
	public Usuario(String login, String contraseña) {
		super();
		this.login = login;
		this.contraseña = contraseña;
		this.puntosFidelidad = 0;
		this.historialdecompras = new ArrayList<>();
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
	
	public List<Venta> getHistorialCompras() {
		return Collections.unmodifiableList(historialdecompras);
	}
	public void setHistorialCompras() {
		this.historialdecompras = historialdecompras;
	}
	
	public boolean verificar (String login, String contraseña) {
		return this.login.equals(login) && this.contraseña.equals(contraseña);
	}
	
	public void acumularPuntos (double valorVenta) {
		if (valorVenta >0) {
			puntosFidelidad += (valorVenta / pesos_punto);}
	}
	public boolean tienePuntosSuficientes (int puntos) {
		if (puntos > 0 && puntosFidelidad >= puntos) {
			return true;
		}
		return false;
	}
	
	public int canjearPuntos (int puntos) {
		if (puntos <= 0) {
			throw new ClubException(" Indica una cantidad de puntos mayor que cero.");
		}
		if (!tienePuntosSuficientes(puntos)) {
			throw new ClubException(" Tienes " + puntosFidelidad
					+ " puntos y quieres redimir " + puntos);
		}
		puntosFidelidad -= puntos;
		return (int) ((puntos) * (valor_punto));
	}
	
	public void registrarCompra(Venta v) {
		if (v == null) {
			throw new ClubException("no hay una venta para registrar.");
		}
		historialdecompras.add(v);
		acumularPuntos(v.calcularTotal());
	}

}
