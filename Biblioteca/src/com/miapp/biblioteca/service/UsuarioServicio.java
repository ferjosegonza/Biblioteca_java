package com.miapp.biblioteca.service;
import java.util.ArrayList;
import java.util.Iterator;

import com.miapp.biblioteca.Libro;
import com.miapp.biblioteca.Usuario;

public class UsuarioServicio {
	
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	
	public UsuarioServicio(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios; 
	}
	
	public void crearUsuario(String nombre, int id) {
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setId(id);
		nuevoUsuario.setNombre(nombre);
		usuarios.add(nuevoUsuario);
	}
	
	public ArrayList<Usuario> obtenerTodosLosUsuarios(){
		return usuarios;
	}
	
	public void actualizarUsuario(int id, String nuevoNombre) {
		boolean encontrado = false;
		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				encontrado = true;
				usuario.setNombre(nuevoNombre);
				System.out.println("Los datos del usuario fueron actualizados a:");
				System.out.println(usuario);
				return;
			}
		}
		
		if(!encontrado) {
			System.out.println("El usuario no fue encontrado. Por favor tenga en cuenta mayúsculas y minúsculas.");
		}
	}
	
	public void eliminarUsuario(int id) {
		boolean encontrado = false;
		Iterator<Usuario> it = usuarios.iterator();
		
		while (it.hasNext()) {
			if (it.next().getId() == id) {
				encontrado = true;
				it.remove();
				System.out.println("Los datos del usuario fueron eliminados. Lista de usuarios actuales:");
				for (Usuario usuario : usuarios) {
					System.out.println(usuario);
				}
				break;
			}
		}
		
		if(!encontrado) {
			System.out.println("El usuario no fue encontrado. Por favor tenga en cuenta mayúsculas y minúsculas.");
		}
	}
	
	public void prestarLibro(Libro libro, Usuario usuario) {
		if (libro.isDisponible()) {
			usuario.getLibros_prestados().add(libro);
			libro.setDisponible(false);
		} else {
			System.out.println("El libro no está disponible.");
		}
	}
	
	public Usuario buscarUsuarioPorId(int id) {
		for(Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				return usuario;
			}
		}
		return null;
	}
	
	public void devolverLibro(Usuario usuario, Libro libro) {
		if(usuario.getLibros_prestados().contains(libro)) {
			usuario.getLibros_prestados().remove(libro);
			libro.setDisponible(true);
		} else {
			System.out.println("El usuario no posee ese libro.");
		}
	}
	
	public ArrayList<Libro> obtenerLibrosPrestados(Usuario usuario){
		return usuario.getLibros_prestados(); 
	}
}
