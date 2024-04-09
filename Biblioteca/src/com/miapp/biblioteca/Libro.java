package com.miapp.biblioteca;

public class Libro {
	private String titulo;
	private String autor;
	private String ISBN;
	private String genero;
	private boolean disponible;
	
	public Libro() {
	}	
	
	public Libro(String titulo, String autor, String iSBN, String genero, boolean disponible) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		ISBN = iSBN;
		this.genero = genero;
		this.disponible = disponible;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", ISBN=" + ISBN + ", genero=" + genero
				+ ", disponible=" + disponible + "]";
	}
	
	
}
