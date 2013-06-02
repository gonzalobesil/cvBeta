package com.example.comoviajarbeta.data;

import java.util.Comparator;

public class Estacion implements Comparable<Estacion>{

	private long id;
	private long departamentoId;
	private String nombre;
	private String telefono;
	private String direccion;
	private String imagen;
	private String longitud;
	private String latitud;
	private String empresa;
	private float distancia;


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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	@Override
	public int compareTo(Estacion another) {
		if(this.distancia > another.getDistancia())
		{
			return 1;
		}
		else if (this.distancia < another.getDistancia())
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
}
