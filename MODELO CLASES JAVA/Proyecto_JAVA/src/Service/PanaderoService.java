package Service;

import DAO.PanaderoDAO;
import Model.Panadero;

import java.sql.SQLException;
import java.util.List;

public class PanaderoService {

    private final PanaderoDAO panaderoDAO = PanaderoDAO.getInstance();

    public void insertarPanadero(Panadero panadero) throws SQLException {
        panaderoDAO.insertPanadero(panadero);
    }

    public List<Panadero> obtenerTodosPanaderos() throws SQLException {
        return panaderoDAO.getAllPanaderos();
    }

    public Panadero obtenerPanaderoPorDni(String dni) throws SQLException {
        return panaderoDAO.getPanaderoByDni(dni);
    }

    public void actualizarPanadero(Panadero panadero) throws SQLException {
        panaderoDAO.updatePanadero(panadero);
    }

    public void eliminarPanaderoPorDni(String dni) throws SQLException {
        panaderoDAO.deletePanaderoByDni(dni);
    }
}
