package Service;

import DAO.RepartidorDAO;
import Model.Repartidor;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RepartidorService {

    private final RepartidorDAO repartidorDAO = RepartidorDAO.getInstance();

    public void insertarRepartidor(String dni, double salario, LocalDate fnac, String nombre, String matricula) throws SQLException {
        Repartidor repartidor = new Repartidor(dni, salario, fnac, nombre, matricula);
        repartidorDAO.insertRepartidor(repartidor);
    }

    public List<Repartidor> obtenerTodosRepartidores() throws SQLException {
        return repartidorDAO.getAllRepartidores();
    }

    public Repartidor buscarPorDni(String dni) throws SQLException {
        List<Repartidor> repartidores = repartidorDAO.getAllRepartidores();
        for (Repartidor r : repartidores) {
            if (r.getDni().equals(dni)) {
                return r;
            }
        }
        return null;
    }

    public void actualizarRepartidor(Repartidor repartidor) throws SQLException {
        repartidorDAO.updateRepartidor(repartidor);
    }

    public void eliminarRepartidorPorDni(String dni) throws SQLException {
        repartidorDAO.deleteRepartidorByDni(dni);
    }
}
