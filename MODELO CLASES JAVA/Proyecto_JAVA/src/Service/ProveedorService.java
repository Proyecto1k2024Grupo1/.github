package service;

import DAO.ProveedorDAO;
import Model.Proveedor;

import java.sql.SQLException;
import java.util.List;

public class ProveedorService {

    private final ProveedorDAO proveedorDAO = ProveedorDAO.getInstance();

    public void insertarProveedor(String nombre) throws SQLException {
        Proveedor proveedor = new Proveedor(0, nombre);
        proveedorDAO.insertProveedor(proveedor);
    }

    public List<Proveedor> obtenerTodosProveedores() throws SQLException {
        return proveedorDAO.getAllProveedores();
    }

    public void actualizarProveedor(int id, String nuevoNombre) throws SQLException {
        Proveedor proveedor = new Proveedor(id, nuevoNombre);
        proveedorDAO.updateProveedor(proveedor);
    }

    public void eliminarProveedor(int id) throws SQLException {
        proveedorDAO.deleteProveedorById(id);
    }
}
