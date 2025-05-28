package View;

import Model.Cliente;
import java.util.List;
import java.util.Scanner;

public class ClienteView {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("********* Menú *********");
        System.out.println("1. Agregar cliente");
        System.out.println("2. Mostrar clientes");
        System.out.println("3. Modificar cliente");
        System.out.println("4. Eliminar cliente");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public int leerOpcion() {
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return opcion;
    }

    public Cliente leerDatosCliente() {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el primer apellido: ");
        String apellido1 = scanner.nextLine();
        System.out.print("Ingrese el segundo apellido: ");
        String apellido2 = scanner.nextLine();
        System.out.print("Ingrese la dirección: ");
        String direccion = scanner.nextLine();

        List<String> telefonos = new java.util.ArrayList<>();
        System.out.print("Ingrese el número de teléfono (o 'fin' para terminar): ");
        String telefono = scanner.nextLine();
        while (!telefono.equalsIgnoreCase("fin")) {
            telefonos.add(telefono);
            System.out.print("Ingrese otro teléfono (o 'fin' para terminar): ");
            telefono = scanner.nextLine();
        }

        return new Cliente(0, direccion, nombre, apellido1, apellido2, telefonos);
    }

    public int leerIdCliente() {
        System.out.print("Ingrese el ID del cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public String leerNuevaDireccion() {
        System.out.print("Ingrese la nueva dirección (o dejar en blanco): ");
        return scanner.nextLine();
    }

    public boolean deseaModificarTelefonos() {
        System.out.println("¿Desea modificar los teléfonos? (sí/no)");
        return scanner.nextLine().equalsIgnoreCase("sí");
    }

    public void mostrarClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println("-------------");
                System.out.println(cliente.getIdCliente());
                System.out.println(cliente.obtenerNombreCompleto() + " - " + cliente.getDireccion());
                System.out.println("Teléfonos: " + String.join(", ", cliente.getTelefonos()));
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
