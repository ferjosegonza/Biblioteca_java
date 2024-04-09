package com.miapp.biblioteca;
import java.util.ArrayList;

public class Usuario {
	private String nombre;
	private int id;
	private ArrayList<Libro> libros_prestados = new ArrayList<>();
	
	public Usuario() {
	}
	
	public Usuario(String nombre, int id, ArrayList<Libro> libros_prestados) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.libros_prestados = libros_prestados;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Libro> getLibros_prestados() {
		return libros_prestados;
	}

	public void setLibros_prestados(ArrayList<Libro> libros_prestados) {
		this.libros_prestados = libros_prestados;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", id=" + id + ", libros_prestados="
				+ libros_prestados + "]";
	} 
	
	
}
