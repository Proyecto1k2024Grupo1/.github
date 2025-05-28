package Service;

import DAO.IngredienteDAO;
import Model.Ingrediente;

import java.sql.SQLException;
import java.util.List;

public class IngredienteService {

    private final IngredienteDAO ingredienteDAO = IngredienteDAO.getInstance();

    public void insertarIngrediente(Ingrediente ingrediente) throws SQLException {
        ingredienteDAO.insertIngrediente(ingrediente);
    }

    public List<Ingrediente> obtenerTodosIngredientes() throws SQLException {
        return ingredienteDAO.getAllIngredientes();
    }

    public void actualizarIngrediente(Ingrediente ingrediente) throws SQLException {
        ingredienteDAO.updateIngrediente(ingrediente);
    }

    public void eliminarIngredientePorId(String id) throws SQLException {
        ingredienteDAO.deleteIngredienteById(id);
    }
}
