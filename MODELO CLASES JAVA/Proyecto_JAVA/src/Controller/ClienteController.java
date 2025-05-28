package Controller;

import Model.Cliente;
import Service.ClienteService;
import View.ClienteView;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {
    private ClienteView vista = new ClienteView();
    private ClienteService servicio = new ClienteService();



    public void iniciar() {
        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerOpcion();

            switch (opcion) {
                case 1 -> agregarCliente();
                case 2 -> mostrarClientes();
                case 3 -> modificarCliente();
                case 4 -> eliminarCliente();
                case 5 -> vista.mostrarMensaje("¡Hasta luego!");
                default -> vista.mostrarMensaje("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private void agregarCliente() {
        Cliente cliente = vista.leerDatosCliente();
        try {
            servicio.agregarCliente(cliente);
            vista.mostrarMensaje("Cliente agregado.");
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al agregar: " + e.getMessage());
        }
    }

    private void mostrarClientes() {
        try {
            List<Cliente> clientes = servicio.obtenerClientes();
            vista.mostrarClientes(clientes);
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al mostrar: " + e.getMessage());
        }
    }

    private void modificarCliente() {
        int id = vista.leerIdCliente();
        try {
            Cliente cliente = servicio.obtenerClientePorId(id);
            if (cliente == null) {
                vista.mostrarMensaje("Cliente no encontrado.");
                return;
            }

            String nuevaDireccion = vista.leerNuevaDireccion();
            if (!nuevaDireccion.isBlank()) cliente.setDireccion(nuevaDireccion);

            if (vista.deseaModificarTelefonos()) {
                cliente.setTelefonos(vista.leerDatosCliente().getTelefonos());
            }

            servicio.actualizarCliente(cliente);
            vista.mostrarMensaje("Cliente modificado.");
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al modificar: " + e.getMessage());
        }
    }

    private void eliminarCliente() {
        int id = vista.leerIdCliente();
        try {
            Cliente cliente = servicio.obtenerClientePorId(id);
            if (cliente == null) {
                vista.mostrarMensaje("Cliente no encontrado.");
                return;
            }

            servicio.eliminarCliente(id);
            vista.mostrarMensaje("Cliente eliminado.");
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al eliminar: " + e.getMessage());
        }
    }
}
