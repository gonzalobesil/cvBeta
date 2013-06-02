package com.example.comoviajarbeta.data;

public class Lugar {

	private long id;
	private long departamentoId;
	private String nombre;
	private String longitud;
	private String latitud;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getDepartamentoId() {
		return departamentoId;
	}

	public void setDepartamentoId(long lugarId) {
		this.departamentoId = lugarId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	
}
