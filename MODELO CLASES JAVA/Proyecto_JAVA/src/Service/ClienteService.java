package Service;

import DAO.ClienteDAO;
import Model.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteService {
    private ClienteDAO dao = ClienteDAO.getInstance();

    public void agregarCliente(Cliente cliente) throws SQLException {
        dao.insertCliente(cliente);
    }

    public List<Cliente> obtenerClientes() throws SQLException {
        return dao.getAllClientes();
    }

    public Cliente obtenerClientePorId(int id) throws SQLException {
        return dao.getClienteById(id);
    }

    public void actualizarCliente(Cliente cliente) throws SQLException {
        dao.updateCliente(cliente);
    }

    public void eliminarCliente(int id) throws SQLException {
        dao.deleteClienteById(id);
    }
}
