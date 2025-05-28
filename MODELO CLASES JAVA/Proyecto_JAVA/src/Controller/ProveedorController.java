package Controller;

import Model.Proveedor;
import service.ProveedorService;
import view.ProveedorView;

import java.sql.SQLException;
import java.util.List;

public class ProveedorController {

    private final ProveedorView view = new ProveedorView();
    private final ProveedorService service = new ProveedorService();

    public void iniciar() {
        int opcion;
        do {
            opcion = view.mostrarMenuYLeerOpcion();
            try {
                switch (opcion) {
                    case 1 -> insertarProveedor();
                    case 2 -> verProveedores();
                    case 3 -> actualizarProveedor();
                    case 4 -> eliminarProveedor();
                    case 5 -> view.mostrarMensaje("Saliendo...");
                    default -> view.mostrarMensaje("Opción no válida. Intenta nuevamente.");
                }
            } catch (SQLException e) {
                view.mostrarMensaje("Error de base de datos: " + e.getMessage());
            }
        } while (opcion != 5);
    }

    private void insertarProveedor() throws SQLException {
        String nombre = view.leerString("Ingrese el nombre del proveedor: ");
        service.insertarProveedor(nombre);
        view.mostrarMensaje("Proveedor insertado exitosamente.");
    }

    private void verProveedores() throws SQLException {
        List<Proveedor> proveedores = service.obtenerTodosProveedores();
        view.mostrarProveedores(proveedores);
    }

    private void actualizarProveedor() throws SQLException {
        int id = view.leerInt("Ingrese el ID del proveedor a actualizar: ");
        String nombre = view.leerString("Ingrese el nuevo nombre del proveedor: ");
        service.actualizarProveedor(id, nombre);
        view.mostrarMensaje("Proveedor actualizado exitosamente.");
    }

    private void eliminarProveedor() throws SQLException {
        int id = view.leerInt("Ingrese el ID del proveedor a eliminar: ");
        service.eliminarProveedor(id);
        view.mostrarMensaje("Proveedor eliminado exitosamente.");
    }
}
