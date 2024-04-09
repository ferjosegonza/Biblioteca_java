package com.miapp.biblioteca.service;
import java.util.ArrayList;
import java.util.Iterator;

import com.miapp.biblioteca.Libro;
import com.miapp.biblioteca.Usuario;

public class LibroServicio {
	private ArrayList<Libro> biblioteca = new ArrayList<>();

	public LibroServicio() {
	}
	
	public LibroServicio(ArrayList<Libro> biblioteca) {
		this.biblioteca = biblioteca;
	}

	public void setBiblioteca(ArrayList<Libro> biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	public void crearLibro(String titulo, String autor, String ISBN, String genero, boolean disponible) {
		Libro nuevoLibro = new Libro(titulo, autor, ISBN, genero, disponible);
		biblioteca.add(nuevoLibro);
	}
	
	/*public ArrayList<Libro> getBiblioteca() {
	return biblioteca;
	}*/
	public ArrayList<Libro> obtenerTodosLosLibros() {
		return biblioteca;
	}
	
	public void actualizarLibro(String ISBN, String titulo, String autor, String genero, boolean disponible) {
		boolean encontrado = false;
		for (Libro libro : biblioteca) {
			if (libro.getISBN().equals(ISBN)) {
				encontrado = true;
				libro.setAutor(autor);
				libro.setTitulo(titulo);
				libro.setGenero(genero);
				libro.setDisponible(disponible);
				System.out.println("Los datos del libro fueron actualizados a:");
				System.out.println(libro);
				break;
			}
		}
		
		if(!encontrado) {
			System.out.println("El libro con ese ISBN no fue encontrado. Por favor tenga en cuenta mayúsculas y minúsculas.");
		}
	}
	
	public void eliminarLibro(String ISBN) {
		boolean encontrado = false;
		Iterator<Libro> it = biblioteca.iterator();
		
		while(it.hasNext()) {
			if (it.next().getISBN().equals(ISBN)) {
				encontrado = true;
				it.remove();
				System.out.println("Los datos del libro fueron eliminados.");
				break;
			}
		}
		
		if(!encontrado) {
			System.out.println("El libro con ese ISBN no fue encontrado. Por favor tenga en cuenta mayúsculas y minúsculas.");
		}
	}
	
	/*public Libro getLibro(int ISBN) {
		return biblioteca;
	}*/
	
	public Libro buscarLibroPorISBN(String ISBN) {
		for(Libro libro : biblioteca) {
			if (libro.getISBN().equals(ISBN)) {
				return libro;
			}
		}
		return null;
	}
	
	public ArrayList<Libro> buscarLibrosPorTitulo(String titulo){
		ArrayList<Libro> librosEncontrados = new ArrayList<>();
		for (Libro libro : biblioteca) {
			if (libro.getTitulo().equalsIgnoreCase(titulo)) {
				librosEncontrados.add(libro);
			}
		}
		return librosEncontrados;
	}
	
	public boolean verificarDisponibilidad(Libro libro) {
		return libro.isDisponible();
	}
	
}
