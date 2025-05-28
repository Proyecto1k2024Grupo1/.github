// Controller/DependienteController.java
package Controller;

import Model.Dependiente;
import Service.DependienteService;
import View.DependienteView;

import java.sql.SQLException;

public class DependienteController {
    private final DependienteView vista = new DependienteView();
    private final DependienteService servicio = new DependienteService();



    public void iniciar() {
        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerOpcion();

            switch (opcion) {
                case 1 -> insertar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> mostrarTodos();
                case 5 -> vista.mostrarMensaje("Saliendo...");
                default -> vista.mostrarMensaje("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private void insertar() {
        try {
            Dependiente d = vista.leerDatosDependiente();
            servicio.insertar(d);
            vista.mostrarMensaje("Dependiente insertado correctamente.");
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al insertar: " + e.getMessage());
        }
    }

    private void actualizar() {
        try {
            String dni = vista.leerDni();
            Dependiente d = servicio.buscarPorDni(dni);
            if (d == null) {
                vista.mostrarMensaje("No se encontró el dependiente.");
                return;
            }
            Dependiente nuevosDatos = vista.leerDatosDependiente();
            nuevosDatos.setDni(dni); // Mantener el mismo DNI
            servicio.actualizar(nuevosDatos);
            vista.mostrarMensaje("Dependiente actualizado.");
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al actualizar: " + e.getMessage());
        }
    }

    private void eliminar() {
        try {
            String dni = vista.leerDni();
            servicio.eliminarPorDni(dni);
            vista.mostrarMensaje("Dependiente eliminado.");
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al eliminar: " + e.getMessage());
        }
    }

    private void mostrarTodos() {
        try {
            vista.mostrarDependientes(servicio.obtenerTodos());
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al mostrar dependientes: " + e.getMessage());
        }
    }
}
