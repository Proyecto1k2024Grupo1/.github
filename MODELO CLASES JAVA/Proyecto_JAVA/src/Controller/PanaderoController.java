package Controller;

import Model.Panadero;
import Service.PanaderoService;
import View.PanaderoView;

import java.sql.SQLException;
import java.util.List;

public class PanaderoController {

    private final PanaderoView view = new PanaderoView();
    private final PanaderoService service = new PanaderoService();


    public void iniciar() {
        boolean salir = false;

        while (!salir) {
            view.mostrarMenu();
            int opcion = view.leerOpcion();

            try {
                switch (opcion) {
                    case 1 -> anadirPanadero();
                    case 2 -> mostrarPanaderos();
                    case 3 -> modificarPanadero();
                    case 4 -> eliminarPanadero();
                    case 5 -> salir = true;
                    default -> view.mostrarMensaje("Opción inválida. Intente de nuevo.");
                }
            } catch (SQLException e) {
                view.mostrarMensaje("Error en la operación: " + e.getMessage());
            }
        }
    }

    private void anadirPanadero() throws SQLException {
        Panadero panadero = view.leerPanaderoParaInsertar();
        service.insertarPanadero(panadero);
        view.mostrarMensaje("Panadero añadido correctamente.");
    }

    private void mostrarPanaderos() throws SQLException {
        List<Panadero> panaderos = service.obtenerTodosPanaderos();
        view.mostrarPanaderos(panaderos);
    }

    private void modificarPanadero() throws SQLException {
        String dni = view.leerDni("Ingrese el DNI del panadero a modificar: ");
        Panadero panadero = service.obtenerPanaderoPorDni(dni);
        if (panadero == null) {
            view.mostrarMensaje("Panadero no encontrado.");
            return;
        }

        panadero = view.leerDatosParaModificar(panadero);
        service.actualizarPanadero(panadero);
        view.mostrarMensaje("Panadero modificado correctamente.");
    }

    private void eliminarPanadero() throws SQLException {
        String dni = view.leerDni("Ingrese el DNI del panadero a eliminar: ");
        Panadero panadero = service.obtenerPanaderoPorDni(dni);
        if (panadero == null) {
            view.mostrarMensaje("Panadero no encontrado.");
            return;
        }

        service.eliminarPanaderoPorDni(dni);
        view.mostrarMensaje("Panadero eliminado correctamente.");
    }
}
