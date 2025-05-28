package View;

import Controller.CompraController;

import java.util.Scanner;

public class VistaCompra {

    private final CompraController controlador;
    private final Scanner scanner = new Scanner(System.in);

    public VistaCompra(CompraController controlador) {
        this.controlador = controlador;
    }

    public void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Mostrar todas las compras");
            System.out.println("2. Crear nueva compra");
            System.out.println("3. Ver detalles de la compra");
            System.out.println("4. Eliminar compra");
            System.out.println("5. Modificar compra");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = leerEntero();

            switch (opcion) {
                case 1 -> controlador.mostrarCompras();
                case 2 -> controlador.crearCompra(scanner);
                case 3 -> controlador.verDetalles(scanner);
                case 4 -> controlador.eliminarCompra(scanner);
                case 5 -> controlador.modificarCompra(scanner);
                case 6 -> {
                    salir = true;
                    System.out.println("¡Hasta luego!");
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingresa un número válido.");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpiar salto de línea
        return valor;
    }

    public static void main(String[] args) {
        VistaCompra vista = new VistaCompra(new CompraController());
        vista.mostrarMenu();
    }
}
