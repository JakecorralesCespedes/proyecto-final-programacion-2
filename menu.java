import java.util.ArrayList;
import java.util.Scanner;

class Libro {
    String titulo;
    String autor;
    String categoria;

    public Libro(String titulo, String autor, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
    }
}

public class menu {
    private ArrayList<Libro> libros = new ArrayList<>();

    public void agregarLibro(String titulo, String autor, String categoria) {
        Libro libro = new Libro(titulo, autor, categoria);
        libros.add(libro);
        System.out.println("Libro agregado correctamente.");
    }

    public void buscarLibroPorCategoria(String categoria) {
        System.out.println("Libros en la categoría " + categoria + ":");
        for (Libro libro : libros) {
            if (libro.categoria.equalsIgnoreCase(categoria)) {
                System.out.println("Título: " + libro.titulo + ", Autor: " + libro.autor);
            }
        }
    }

    public static void main(String[] args) {
        menu biblioteca = new menu();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú de Biblioteca");
            System.out.println("1. Agregar libro");
            System.out.println("2. Buscar libros por categoría");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpia el salto de línea después de leer el número

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese el autor del libro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Ingrese la categoría del libro: ");
                    String categoria = scanner.nextLine();
                    biblioteca.agregarLibro(titulo, autor, categoria);
                    break;
                case 2:
                    System.out.print("Ingrese la categoría a buscar: ");
                    String categoriaBusqueda = scanner.nextLine();
                    biblioteca.buscarLibroPorCategoria(categoriaBusqueda);
                    break;
                case 3:
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }
}


