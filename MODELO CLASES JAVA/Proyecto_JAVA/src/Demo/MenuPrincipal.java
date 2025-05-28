package Demo;

import Controller.ClienteController;
import Controller.CompraController;
import Controller.EmpleadoController;
import Controller.ProductoController;
import Controller.ProveedorController;
import Controller.IngredienteController;

import java.util.Scanner;

/**
 * Clase que representa el menú principal del sistema.
 * Desde este menú se puede acceder a los distintos módulos: Cliente, Compra, Empleado, Producto, Proveedor e Ingrediente.
 * Cada opción redirige al submenú correspondiente para gestionar esa sección del sistema.
 *
 * @author Vanesa, Silvia, Jessica
 * @version 1.2
 * @since 10/04/2025
 */
public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 7) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Cliente");
            System.out.println("2. Compra");
            System.out.println("3. Empleado");
            System.out.println("4. Producto");
            System.out.println("5. Proveedor");
            System.out.println("6. Ingrediente");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    new ClienteController().iniciar();
                    break;
                case 2:
                    new CompraController().iniciar();
                    break;
                case 3:
                    new EmpleadoController().iniciar();
                    break;
                case 4:
                    new ProductoController().iniciar();
                    break;
                case 5:
                    new ProveedorController().iniciar();
                    break;
                case 6:
                    new IngredienteController().iniciar();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        scanner.close();
    }
}
