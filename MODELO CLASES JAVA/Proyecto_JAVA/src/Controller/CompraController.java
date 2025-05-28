package Controller;

import Service.CompraServicio;

import java.sql.SQLException;
import java.util.Scanner;

public class CompraController {

    private final CompraServicio servicio = new CompraServicio();
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n==== MENÚ DE COMPRAS ====");
            System.out.println("1. Crear compra");
            System.out.println("2. Mostrar compras");
            System.out.println("3. Ver detalles de compra");
            System.out.println("4. Modificar compra");
            System.out.println("5. Eliminar compra");
            System.out.println("6. Volver al menú principal / Salir");
            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                scanner.next();
            }

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    crearCompra(scanner);
                    break;
                case 2:
                    mostrarCompras();
                    break;
                case 3:
                    verDetalles(scanner);
                    break;
                case 4:
                    modificarCompra(scanner);
                    break;
                case 5:
                    eliminarCompra(scanner);
                    break;
                case 6:
                    System.out.println("Saliendo del menú de compras...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 6);
    }

    public void mostrarCompras() {
        try {
            servicio.listarCompras();
        } catch (SQLException e) {
            System.out.println("Error al mostrar compras: " + e.getMessage());
        }
    }

    public void crearCompra(Scanner scanner) {
        try {
            servicio.crearCompra(scanner);
        } catch (SQLException e) {
            System.out.println("Error al crear compra: " + e.getMessage());
        }
    }

    public void verDetalles(Scanner scanner) {
        try {
            servicio.mostrarDetalles(scanner);
        } catch (SQLException e) {
            System.out.println("Error al ver detalles: " + e.getMessage());
        }
    }

    public void eliminarCompra(Scanner scanner) {
        try {
            servicio.eliminarCompra(scanner);
        } catch (SQLException e) {
            System.out.println("Error al eliminar compra: " + e.getMessage());
        }
    }

    public void modificarCompra(Scanner scanner) {
        try {
            servicio.modificarCompra(scanner);
        } catch (SQLException e) {
            System.out.println("Error al modificar compra: " + e.getMessage());
        }
    }
}
