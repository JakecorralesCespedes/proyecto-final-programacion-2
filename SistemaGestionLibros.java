import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SistemaGestionLibros {
    private static final String CONTRASENA_ADMIN = "admin123"; // Contraseña sencilla para el administrador
    private static List<Libro> libros = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("¿Eres 'Administrador' o 'Invitado'? (Ingresa 'Salir' para cerrar el sistema)");
            String tipoUsuario = scanner.nextLine();

            if ("Administrador".equalsIgnoreCase(tipoUsuario)) {
                System.out.println("Por favor, introduce la contraseña de administrador:");
                String contrasena = scanner.nextLine();
                if (!CONTRASENA_ADMIN.equals(contrasena)) {
                    System.out.println("Contraseña incorrecta.");
                    continue;
                }
                boolean sesionAdmin = true;
                while (sesionAdmin) {
                    System.out.println("Elige una opción: \n1. Agregar Libro \n2. Modificar Libro \n3. Eliminar Libro \n0. Salir");
                    int eleccionAdmin = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea restante
                    switch (eleccionAdmin) {
                        case 1:
                            agregarLibro(scanner);
                            break;
                        case 2:
                            modificarLibro(scanner);
                            break;
                        case 3:
                            eliminarLibro(scanner);
                            break;
                        case 0:
                            sesionAdmin = false;
                            break;
                        default:
                            System.out.println("Opción inválida.");
                    }
                }
            } else if ("Invitado".equalsIgnoreCase(tipoUsuario)) {
                boolean sesionInvitado = true;
                while (sesionInvitado) {
                    System.out.println("Elige una opción: \n1. Buscar Libro \n2. Salir");
                    int eleccionInvitado = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea restante
                    switch (eleccionInvitado) {
                        case 1:
                            buscarLibro(scanner);
                            break;
                        case 2:
                            sesionInvitado = false;
                            break;
                        default:
                            System.out.println("Opción inválida.");
                    }
                }
            } else if ("Salir".equalsIgnoreCase(tipoUsuario)) {
                break;
            } else {
                System.out.println("Tipo de usuario no válido.");
            }
        }
        scanner.close();
        System.out.println("Sistema cerrado.");
    }

    private static void agregarLibro(Scanner scanner) {
        System.out.println("Introduce la categoría del libro:");
        String categoria = scanner.nextLine();
        System.out.println("Introduce el nombre del libro:");
        String nombre = scanner.nextLine();
        System.out.println("Introduce el autor:");
        String autor = scanner.nextLine();
        System.out.println("Introduce la fecha de creación (ej., 2023-11-08):");
        String fechaCreacion = scanner.nextLine();
        System.out.println("Introduce el ISAN:");
        String isan = scanner.nextLine();

        Libro nuevoLibro = new Libro(categoria, nombre, autor, fechaCreacion, isan);
        libros.add(nuevoLibro);
        System.out.println("¡Libro agregado con éxito!");
    }

    private static void modificarLibro(Scanner scanner) {
        System.out.println("Introduce el nombre del libro que deseas modificar:");
        String nombre = scanner.nextLine();
        Libro libroParaModificar = encontrarLibroPorNombre(nombre);
        if (libroParaModificar == null) {
            System.out.println("Libro no encontrado.");
            return;
        }
        System.out.println("Introduce la nueva categoría (o presiona Enter para omitir):");
        String categoria = scanner.nextLine();
        if (!categoria.isEmpty()) libroParaModificar.setCategoria(categoria);

        System.out.println("Introduce el nuevo autor (o presiona Enter para omitir):");
        String autor = scanner.nextLine();
        if (!autor.isEmpty()) libroParaModificar.setAutor(autor);

        System.out.println("Introduce la nueva fecha de creación (o presiona Enter para omitir):");
        String fecha = scanner.nextLine();
        if (!fecha.isEmpty()) libroParaModificar.setFechaCreacion(fecha);

        System.out.println("Introduce el nuevo ISAN (o presiona Enter para omitir):");
        String isan = scanner.nextLine();
        if (!isan.isEmpty()) libroParaModificar.setIsan(isan);

        System.out.println("¡Libro modificado con éxito!");
    }

    private static void eliminarLibro(Scanner scanner) {
        System.out.println("Introduce el nombre del libro a eliminar:");
        String nombre = scanner.nextLine();
        Libro libroParaEliminar = encontrarLibroPorNombre(nombre);
        if (libroParaEliminar == null) {
            System.out.println("Libro inexistente.");
            return;
        }
        libros.remove(libroParaEliminar);
        System.out.println("¡Libro eliminado con éxito!");
    }

    private static void buscarLibro(Scanner scanner) {
        System.out.println("Elige la opción de búsqueda: \n1. Buscar por Categoría \n2. Buscar por Nombre del Libro \n0. Salir");
        int opcionBusqueda = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea restante
        switch (opcionBusqueda) {
            case 1:
                System.out.println("Introduce la categoría:");
                String categoria = scanner.nextLine();
                List<Libro> librosEncontrados = buscarPorCategoria(categoria);
                if (librosEncontrados.isEmpty()) {
                    System.out.println("No se encontraron libros en esta categoría.");
                } else {
                    for (Libro libro : librosEncontrados) {
                        System.out.println(libro);
                    }
                }
                break;
            case 2:
                System.out.println("Introduce el nombre del libro:");
                String nombre = scanner.nextLine();
                Libro libro = encontrarLibroPorNombre(nombre);
                if (libro == null) {
                    System.out.println("Libro no encontrado.");
                } else {
                    System.out.println(libro);
                }
                break;
            case 0:
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private static Libro encontrarLibroPorNombre(String nombre) {
        for (Libro libro : libros) {
            if (libro.getNombre().equalsIgnoreCase(nombre)) {
                return libro;
            }
        }
        return null;
    }

    private static List<Libro> buscarPorCategoria(String categoria) {
        return libros.stream()
                .filter(libro -> libro.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    // Clase Libro sencilla para mantener los datos del libro
    static class Libro {
        private String categoria;
        private String nombre;
        private String autor;
        private String fechaCreacion;
        private String isan;

        public Libro(String categoria, String nombre, String autor, String fechaCreacion, String isan) {
            this.categoria = categoria;
            this.nombre = nombre;
            this.autor = autor;
            this.fechaCreacion = fechaCreacion;
            this.isan = isan;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getAutor() {
            return autor;
        }

        public void setAutor(String autor) {
            this.autor = autor;
        }

        public String getFechaCreacion() {
            return fechaCreacion;
        }

        public void setFechaCreacion(String fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
        }

        public String getIsan() {
            return isan;
        }

        public void setIsan(String isan) {
            this.isan = isan;
        }

        @Override
        public String toString() {
            return "Libro{" +
                    "categoría='" + categoria + '\'' +
                    ", nombre='" + nombre + '\'' +
                    ", autor='" + autor + '\'' +
                    ", fecha de creación='" + fechaCreacion + '\'' +
                    ", ISAN='" + isan + '\'' +
                    '}';
        }
    }
}
