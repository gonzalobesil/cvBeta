package com.example.comoviajarbeta.data;

public class TipoAlerta {

	private long id;
	private String nombre;
	private int agrupacionAlerta;

	private String imagen;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAgrupacionAlerta() {
		return agrupacionAlerta;
	}

	public void setAgrupacionAlerta(int tipoAlerta) {
		this.agrupacionAlerta = tipoAlerta;
	}
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}
