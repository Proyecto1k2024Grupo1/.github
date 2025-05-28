package view;

import java.util.List;
import java.util.Scanner;
import Model.Proveedor;

public class ProveedorView {

    private final Scanner scanner = new Scanner(System.in);

    public int mostrarMenuYLeerOpcion() {
        System.out.println("\n--- Menú de Proveedores ---");
        System.out.println("1. Insertar nuevo proveedor");
        System.out.println("2. Ver todos los proveedores");
        System.out.println("3. Actualizar proveedor");
        System.out.println("4. Eliminar proveedor");
        System.out.println("5. Salir");
        System.out.print("Selecciona una opción: ");
        return leerIntValido();
    }

    public String leerString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public int leerInt(String mensaje) {
        System.out.print(mensaje);
        return leerIntValido();
    }

    public void mostrarProveedores(List<Proveedor> proveedores) {
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            System.out.println("\n--- Proveedores ---");
            for (Proveedor p : proveedores) {
                System.out.println("ID: " + p.getCodProveedor() + " | Nombre: " + p.getNombre());
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    private int leerIntValido() {
        while (true) {
            try {
                int val = Integer.parseInt(scanner.nextLine());
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Introduce un número válido: ");
            }
        }
    }
}
