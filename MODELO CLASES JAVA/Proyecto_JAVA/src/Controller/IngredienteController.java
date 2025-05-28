package Controller;

import Model.Ingrediente;
import Service.IngredienteService;
import View.IngredienteView;

import java.sql.SQLException;
import java.util.List;

public class IngredienteController {

    private final IngredienteView view = new IngredienteView();
    private final IngredienteService service = new IngredienteService();



    public void iniciar() {
        boolean salir = false;

        while (!salir) {
            view.mostrarMenu();
            int opcion = view.leerOpcion();

            try {
                switch (opcion) {
                    case 1 -> insertarIngrediente();
                    case 2 -> mostrarTodosIngredientes();
                    case 3 -> actualizarIngrediente();
                    case 4 -> eliminarIngrediente();
                    case 5 -> salir = true;
                    default -> view.mostrarMensaje("Opción no válida, intente de nuevo.");
                }
            } catch (SQLException e) {
                view.mostrarMensaje("Error en la operación: " + e.getMessage());
            }
        }
    }

    private void insertarIngrediente() throws SQLException {
        Ingrediente ingrediente = view.leerIngredienteParaInsertar();
        service.insertarIngrediente(ingrediente);
        view.mostrarMensaje("Ingrediente insertado con éxito.");
    }

    private void mostrarTodosIngredientes() throws SQLException {
        List<Ingrediente> ingredientes = service.obtenerTodosIngredientes();
        view.mostrarIngredientes(ingredientes);
    }

    private void actualizarIngrediente() throws SQLException {
        Ingrediente ingrediente = view.leerIngredienteParaActualizar();
        service.actualizarIngrediente(ingrediente);
        view.mostrarMensaje("Ingrediente actualizado con éxito.");
    }

    private void eliminarIngrediente() throws SQLException {
        int cod = view.leerCodigoParaEliminar();
        service.eliminarIngredientePorId(String.valueOf(cod));
        view.mostrarMensaje("Ingrediente eliminado con éxito.");
    }
}
