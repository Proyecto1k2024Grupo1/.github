package View;

import Model.Ingrediente;

import java.util.List;
import java.util.Scanner;

public class IngredienteView {

    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n------ Menú Ingredientes ------");
        System.out.println("1. Insertar Ingrediente");
        System.out.println("2. Mostrar Todos los Ingredientes");
        System.out.println("3. Actualizar Ingrediente");
        System.out.println("4. Eliminar Ingrediente");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public int leerOpcion() {
        return Integer.parseInt(scanner.nextLine());
    }

    public Ingrediente leerIngredienteParaInsertar() {
        System.out.print("Ingrese el nombre del ingrediente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la descripción del ingrediente: ");
        String descripcion = scanner.nextLine();
        return new Ingrediente(0, nombre, descripcion);
    }

    public Ingrediente leerIngredienteParaActualizar() {
        System.out.print("Ingrese el código del ingrediente a actualizar: ");
        int cod = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el nuevo nombre del ingrediente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la nueva descripción del ingrediente: ");
        String descripcion = scanner.nextLine();
        return new Ingrediente(cod, nombre, descripcion);
    }

    public int leerCodigoParaEliminar() {
        System.out.print("Ingrese el código del ingrediente a eliminar: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void mostrarIngredientes(List<Ingrediente> ingredientes) {
        if (ingredientes.isEmpty()) {
            System.out.println("No hay ingredientes registrados.");
        } else {
            System.out.println("------ Lista de Ingredientes ------");
            for (Ingrediente ingrediente : ingredientes) {
                System.out.println("Código: " + ingrediente.getCodIngrediente() +
                        " | Nombre: " + ingrediente.getNombre() +
                        " | Descripción: " + ingrediente.getDescripcion());
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
