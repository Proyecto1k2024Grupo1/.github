package service;

import DAO.AjenoDAO;
import DAO.PropioDAO;
import Model.Ajeno;
import Model.Propio;

import java.sql.SQLException;
import java.util.List;

public class ProductoService {

    private final PropioDAO propioDAO = PropioDAO.getInstance();
    private final AjenoDAO ajenoDAO = AjenoDAO.getInstance();

    public void agregarProductoPropio(String nombre, String tipo, double precio) throws SQLException {
        Propio propio = new Propio(0, nombre, tipo, precio);
        propioDAO.insertPropio(propio);
    }

    public void agregarProductoAjeno(String nombre, String tipo, double precio) throws SQLException {
        Ajeno ajeno = new Ajeno(0, nombre, tipo, precio);
        ajenoDAO.insertAjeno(ajeno);
    }

    public List<Propio> obtenerTodosPropios() throws SQLException {
        return propioDAO.getAllPropio();
    }

    public List<Ajeno> obtenerTodosAjenos() throws SQLException {
        return ajenoDAO.getAllAjenos();
    }

    public Propio obtenerPropioPorId(int id) throws SQLException {
        return propioDAO.getPropioByCodigo(id);
    }

    public Ajeno obtenerAjenoPorId(int id) throws SQLException {
        return ajenoDAO.getAjenoByCodigo(id);
    }

    public void actualizarPropio(Propio propio) throws SQLException {
        propioDAO.updatePropio(propio);
    }

    public void actualizarAjeno(Ajeno ajeno) throws SQLException {
        ajenoDAO.updateAjeno(ajeno);
    }

    public void eliminarPropio(int id) throws SQLException {
        propioDAO.deletePropioByCodigo(id);
    }

    public void eliminarAjeno(int id) throws SQLException {
        ajenoDAO.deleteAjenoByCodigo(id);
    }
}
