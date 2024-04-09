package com.miapp.biblioteca;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import com.miapp.biblioteca.service.LibroServicio;
import com.miapp.biblioteca.service.UsuarioServicio;

public class Main {

	public static void main(String[] args) {
		ArrayList<Libro> biblioteca = new ArrayList();
		ArrayList<Usuario> usuarios = new ArrayList();
		LibroServicio libroServicio = new LibroServicio(biblioteca);
		UsuarioServicio usuarioServicio = new UsuarioServicio(usuarios);
		Scanner scan = new Scanner(System.in);
		
		int opcion;
		do {
			System.out.println("=== Biblioteca Virtual ===");
			System.out.println("1. Crear Libro");
			System.out.println("2. Actualizar Libro");
			System.out.println("3. Buscar Libro por ISBN");
			System.out.println("4. Buscar Libro por Título");
			System.out.println("5. Listar Libros");
			System.out.println("6. Eliminar Libro");
			System.out.println("7. Prestamos");
			System.out.println("8. Devoluciones");
			System.out.println("9. Crear Usuario");
			System.out.println("10. Actualizar usuario");
			System.out.println("11. Listar usuarios");
			System.out.println("12. Eliminar usuario");
			System.out.println("13. Salir");
			System.out.println("Seleccione una opción: ");
			opcion = scan.nextInt();
			scan.nextLine(); // Para consumir el salto de línea
			
			switch (opcion) {
			case 1: {
				// Crear Libro
				Libro libroISBN;
				String ISBN;
				do {
					System.out.println("Ingrese el ISBN: ");
					ISBN = scan.nextLine();
					libroISBN = libroServicio.buscarLibroPorISBN(ISBN);
					if (libroISBN != null) {
						System.out.println("Ya existe un libro con ese ISBN, ingrese uno nuevo: ");
					}
					
				} while (libroISBN != null);
				System.out.println("Ingrese el título: ");
				String titulo = scan.nextLine();
				System.out.println("Ingrese el autor: ");
				String autor = scan.nextLine();
				System.out.println("Ingrese el género: ");
				String genero = scan.nextLine();
				int prestamo = 0;
				boolean disponible = false;
				do {
					System.out.println("¿Está disponible para préstamo?: ");
					System.out.println("Ingrese 1 para SÍ o 2 para NO");
					prestamo = scan.nextInt();
					scan.nextLine(); // Para consumir el salto de línea
					
					if (prestamo == 1) {
						disponible = true;
					} else if (prestamo == 2) {
						disponible = false;
					} else {
						System.out.println("Ingrese una opción válida.");
					}
				} while (prestamo != 1 && prestamo != 2);
				
				libroServicio.crearLibro(titulo, autor, ISBN, genero, disponible);
				System.out.println("Se ha cargado el Libro.");
				break;
			}
			case 2: {
				// Actualizar Libro
				System.out.println("Ingrese el ISBN del libro a actualizar: ");
				String isbnActualizar = scan.nextLine();
				System.out.println("Ingrese el nuevo título: ");
				String nuevoTitulo = scan.nextLine();
				System.out.println("Ingrese el nuevo autor: ");
				String nuevoAutor = scan.nextLine();
				System.out.println("Ingrese el nuevo género: ");
				String nuevoGenero = scan.nextLine();
				int nuevoPrestamo = 0;
				boolean nuevoDisponible = false;
				do {
					System.out.println("¿Está disponible para préstamo?: ");
					System.out.println("Ingrese 1 para SÍ o 2 para NO");
					nuevoPrestamo = scan.nextInt();
					scan.nextLine(); // Para consumir el salto de línea
					
					if (nuevoPrestamo == 1) {
						nuevoDisponible = true;
					} else if (nuevoPrestamo == 2) {
						nuevoDisponible = false;
					} else {
						System.out.println("Ingrese una opción válida.");
					}
				} while (nuevoPrestamo != 1 && nuevoPrestamo != 2);
				libroServicio.actualizarLibro(isbnActualizar, nuevoTitulo, nuevoAutor, nuevoGenero, nuevoDisponible);
				break;
			}
			case 3: {
				// Buscar Libro por ISBN
				System.out.println("Ingrese el ISBN del libro a buscar: ");
				String isbnBuscar = scan.nextLine();
				Libro libroISBN = libroServicio.buscarLibroPorISBN(isbnBuscar);
				if (libroISBN != null) {
					System.out.println("Libro encontrado: " + libroISBN.getTitulo());
				} else {
					System.out.println("Libro no encontrado");
				}
				break;
			}
			case 4: {
				// Buscar Libro por Título
				System.out.println("Ingrese el título del libro a buscar: ");
				String tituloBuscar = scan.nextLine();
				ArrayList<Libro> librosPorTitulo = libroServicio.buscarLibrosPorTitulo(tituloBuscar);
				if (!librosPorTitulo.isEmpty()) {
					System.out.println("Libros encontrados:");
					for (Libro libro : librosPorTitulo) {
						System.out.println(libro.getTitulo() + " (" + libro.getISBN() + ")");
					}
				} else {
					System.out.println("Ningún libro encontrado con ese título");
				}
				break;
			}
			case 5: {
				// Listar Libros
				ArrayList<Libro> listaLibros = libroServicio.obtenerTodosLosLibros();
				if (!listaLibros.isEmpty()) {
					for(Libro libro: listaLibros) {
						System.out.println(libro.getTitulo() + " (" + libro.getISBN() + ")");
					}
				} else {
					System.out.println("No se encontraron libros.");
				}
				
				break;
			}
			case 6: {
				// Eliminar Libro
				System.out.println("Ingrese el ISBN del libro a eliminar: ");
				String isbnEliminar = scan.nextLine();
				libroServicio.eliminarLibro(isbnEliminar);
				System.out.println("Se ha eliminado el Libro.");
				break;
			}
			case 7: {
				// Prestamos
				System.out.println("Ingrese el número de identificación del usuario: ");
				int idUsuario = scan.nextInt();
				scan.nextLine(); // Para consumir el salto de línea
				Usuario usuarioPrestamo = usuarioServicio.buscarUsuarioPorId(idUsuario);
				if (usuarioPrestamo != null) {
					System.out.println("Ingrese el ISBN del libro a prestar: ");
					String isbnPrestamo = scan.nextLine();
					Libro libroPrestamo = libroServicio.buscarLibroPorISBN(isbnPrestamo);
					if (libroPrestamo != null) {
						if (libroServicio.verificarDisponibilidad(libroPrestamo)) {
							usuarioServicio.prestarLibro(libroPrestamo, usuarioPrestamo);
							System.out.println("Préstamo exitoso. Libro prestado a " + usuarioPrestamo.getNombre());
						} else {
							System.out.println("El libro no está disponible para préstamo.");
						}
					} else {
						System.out.println("Libro no encontrado.");
					}
				} else {
					System.out.println("Usuario no encontrado.");
				}
				break;
			}
			case 8: {
			    // Devoluciones
			    System.out.println("Ingrese el número de identificación del usuario: ");
			    int idUsuario = scan.nextInt();
			    scan.nextLine(); // Para consumir el salto de línea
			    Usuario usuarioDevolucion = usuarioServicio.buscarUsuarioPorId(idUsuario);
			    if (usuarioDevolucion != null) {
			        ArrayList<Libro> librosPrestados = usuarioServicio.obtenerLibrosPrestados(usuarioDevolucion);
			        if (!librosPrestados.isEmpty()) {
			        	System.out.println("Libros prestados por el usuario:");
				        for (Libro libro : librosPrestados) {
				            System.out.println(libro.getTitulo() + " (" + libro.getISBN() + ")");
				        }
				        System.out.println("Ingrese el ISBN del libro a devolver: ");
				        String isbnDevolucion = scan.nextLine();
				        Libro libroDevolucion = libroServicio.buscarLibroPorISBN(isbnDevolucion);
				        if (libroDevolucion != null) {
				            usuarioServicio.devolverLibro(usuarioDevolucion, libroDevolucion);
				            System.out.println("Devolución exitosa. Libro devuelto por " + usuarioDevolucion.getNombre());
				        } else {
				            System.out.println("Libro no encontrado.");
				        }
					} else {
						System.out.println("El usuario no posee libros prestados.");
					}
			        
			    } else {
			        System.out.println("Usuario no encontrado.");
			    }
			    break;
			}
			case 9: {
			    // Crear Usuario
			    int idUsuario;
			    Usuario usuario;
			    do {
			    	System.out.println("Ingrese el ID del usuario: ");
			    	idUsuario = scan.nextInt();
			    	scan.nextLine(); // Para consumir el salto de línea
			    	usuario = usuarioServicio.buscarUsuarioPorId(idUsuario);
			    	if (usuario != null) {
			    		System.out.println("Ya existe un Usuario con ese ID, elija otro.");
					}
				} while (usuario != null);
			    System.out.println("Ingrese el nombre del usuario: ");
			    String nombreUsuario = scan.nextLine();
			    
			    
			    usuarioServicio.crearUsuario(nombreUsuario, idUsuario);
			    System.out.println("Usuario creado correctamente.");
			    break;
			}
			case 10: {
			    // Actualizar Usuario
			    System.out.println("Ingrese el ID del usuario a actualizar: ");
			    int idActualizar = scan.nextInt();
			    scan.nextLine(); // Para consumir el salto de línea
			    System.out.println("Ingrese el nuevo nombre del usuario: ");
			    String nuevoNombreUsuario = scan.nextLine();
			    usuarioServicio.actualizarUsuario(idActualizar, nuevoNombreUsuario);
			    break;
			}
			case 11: {
			    // Listar Usuarios
			    ArrayList<Usuario> listaUsuarios = usuarioServicio.obtenerTodosLosUsuarios();
			    if (!listaUsuarios.isEmpty()) {
			    	for(Usuario usuario : listaUsuarios) {
				        System.out.println(usuario.getNombre() + " (ID: " + usuario.getId() + ")");
				    }
				} else {
					System.out.println("No se encontraron usuarios.");
				}
			    
			    break;
			}
			case 12: {
			    // Eliminar Usuario
			    System.out.println("Ingrese el ID del usuario a eliminar: ");
			    int idEliminar = scan.nextInt();
			    scan.nextLine(); // Para consumir el salto de línea
			    usuarioServicio.eliminarUsuario(idEliminar);
			    break;
			}

			case 13: {
				// Salir
				System.out.println("Saliendo del programa...");
				break;
			}
			default: {
				System.out.println("Opción no válida. Inténtelo de nuevo.");
			}			
			
			/* CRUD
			 * 		Crear usuario
			 * 		Actualizar usuario
			 * 		Listar usuarios
			 * 		Eliminar usuario
			 * */
			}
		} while (opcion != 13);

	}

}
