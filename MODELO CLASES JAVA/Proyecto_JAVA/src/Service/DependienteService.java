// Service/DependienteService.java
package Service;

import DAO.DependienteDAO;
import Model.Dependiente;

import java.sql.SQLException;
import java.util.List;

public class DependienteService {
    private DependienteDAO dao = DependienteDAO.getInstance();

    public void insertar(Dependiente d) throws SQLException {
        dao.insertDependiente(d);
    }

    public void actualizar(Dependiente d) throws SQLException {
        dao.updateDependiente(d);
    }

    public void eliminarPorDni(String dni) throws SQLException {
        dao.deleteDependienteByDni(dni);
    }

    public List<Dependiente> obtenerTodos() throws SQLException {
        return dao.getAllDependiente();
    }

    public Dependiente buscarPorDni(String dni) throws SQLException {
        return dao.getAllDependiente().stream()
                .filter(d -> d.getDni().equals(dni))
                .findFirst()
                .orElse(null);
    }
}
