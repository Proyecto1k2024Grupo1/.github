package DAO;

import Model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO (Data Access Object) que permite interactuar con la tabla CLIENTE y TELEFONO en la base de datos.
 * Proporciona métodos para insertar, actualizar, eliminar y recuperar clientes.
 * La clase gestiona las operaciones relacionadas con los clientes y sus números de teléfono en la base de datos.
 *
 * Patrón: Singleton
 *
 * Autor: Vanesa, Silvia, Jessica
 * @version 1.0
 * @since 10/04/2025
 */
public class ClienteDAO {

    private static ClienteDAO instance = ClienteDAO.getInstance();
    private Connection connection;

    // Queries definidas como constantes finales
    private static final String INSERT_QUERY = "INSERT INTO CLIENTE (direccion, nombre, apellido1, apellido2) VALUES (?, ?, ?, ?)";
    private static final String INSERT_TELEFONO_QUERY = "INSERT INTO TELEFONO (idCliente, numTelefono) VALUES (?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM CLIENTE";
    private static final String SELECT_CLIENTE_BY_ID_QUERY = "SELECT * FROM CLIENTE WHERE idCliente = ?";
    private static final String UPDATE_QUERY = "UPDATE CLIENTE SET direccion = ?, nombre = ?, apellido1 = ?, apellido2 = ? WHERE idCliente = ?";
    private static final String DELETE_QUERY = "DELETE FROM CLIENTE WHERE idCliente = ?";
    private static final String DELETE_TELEFONOS_QUERY = "DELETE FROM TELEFONO WHERE idCliente = ?";
    private static final String SELECT_TELEFONOS_CLIENTE = "SELECT numTelefono FROM TELEFONO WHERE idCliente = ?";

    /**
     * Constructor privado para seguir el patrón Singleton.
     * Inicializa la conexión a la base de datos.
     */
    private ClienteDAO() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Método estático para obtener una única instancia de ClienteDAO.
     * @return la única instancia de ClienteDAO.
     */
    public static synchronized ClienteDAO getInstance() {
        if (instance == null) {
            instance = new ClienteDAO();
        }
        return instance;
    }

    /**
     * Inserta un nuevo cliente y sus teléfonos en la base de datos.
     * Utiliza transacciones para asegurar consistencia.
     *
     * @param cliente Cliente a insertar.
     * @throws SQLException si ocurre un error en la inserción.
     */
    public void insertCliente(Cliente cliente) throws SQLException {
        connection.setAutoCommit(false);

        try (PreparedStatement clienteStmt = connection.prepareStatement(INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            clienteStmt.setString(1, cliente.getDireccion());
            clienteStmt.setString(2, cliente.getNombre());
            clienteStmt.setString(3, cliente.getApellido1());
            clienteStmt.setString(4, cliente.getApellido2());

            clienteStmt.executeUpdate();

            ResultSet rs = clienteStmt.getGeneratedKeys();
            if (rs.next()) {
                int idCliente = rs.getInt(1);
                insertTelefonos(idCliente, cliente.getTelefonos());
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    /**
     * Inserta una lista de teléfonos para un cliente dado.
     *
     * @param idCliente ID del cliente al que se asocian los teléfonos.
     * @param telefonos Lista de números de teléfono.
     * @throws SQLException si ocurre un error al insertar.
     */
    private void insertTelefonos(int idCliente, List<String> telefonos) throws SQLException {
        try (PreparedStatement telefonoStmt = connection.prepareStatement(INSERT_TELEFONO_QUERY)) {
            for (String telefono : telefonos) {
                telefonoStmt.setInt(1, idCliente);
                telefonoStmt.setString(2, telefono);
                telefonoStmt.addBatch();
            }
            telefonoStmt.executeBatch();
        }
    }

    /**
     * Recupera todos los clientes registrados junto con sus teléfonos.
     *
     * @return Lista de clientes con sus datos completos.
     * @throws SQLException si ocurre un error al acceder a los datos.
     */
    public List<Cliente> getAllClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = resultSetToCliente(resultSet);
                cliente.setTelefonos(getTelefonosCliente(cliente.getIdCliente()));
                clientes.add(cliente);
            }
        }

        return clientes;
    }

    /**
     * Recupera un cliente por su ID, incluyendo sus teléfonos.
     *
     * @param idCliente ID del cliente a recuperar.
     * @return Cliente con todos sus datos o null si no existe.
     * @throws SQLException si ocurre un error durante la operación.
     */
    public Cliente getClienteById(int idCliente) throws SQLException {
        Cliente cliente = null;

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_CLIENTE_BY_ID_QUERY)) {
            stmt.setInt(1, idCliente);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                cliente = resultSetToCliente(resultSet);
                cliente.setTelefonos(getTelefonosCliente(idCliente));
            }
        }

        return cliente;
    }

    /**
     * Recupera la lista de teléfonos asociada a un cliente específico.
     *
     * @param idCliente ID del cliente.
     * @return Lista de números de teléfono.
     * @throws SQLException si ocurre un error en la consulta.
     */
    private List<String> getTelefonosCliente(int idCliente) throws SQLException {
        List<String> telefonos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_TELEFONOS_CLIENTE)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                telefonos.add(rs.getString("numTelefono"));
            }
        }

        return telefonos;
    }

    /**
     * Convierte un ResultSet en un objeto Cliente.
     *
     * @param resultSet Conjunto de resultados de una consulta.
     * @return Objeto Cliente.
     * @throws SQLException si ocurre un error al acceder al ResultSet.
     */
    private Cliente resultSetToCliente(ResultSet resultSet) throws SQLException {
        return new Cliente(
                resultSet.getInt("idCliente"),
                resultSet.getString("direccion"),
                resultSet.getString("nombre"),
                resultSet.getString("apellido1"),
                resultSet.getString("apellido2")
        );
    }

    /**
     * Actualiza los datos de un cliente y reemplaza sus teléfonos.
     * Utiliza transacciones para mantener la integridad de los datos.
     *
     * @param cliente Cliente con los datos actualizados.
     * @throws SQLException si ocurre un error durante la operación.
     */
    public void updateCliente(Cliente cliente) throws SQLException {
        connection.setAutoCommit(false);

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, cliente.getDireccion());
            statement.setString(2, cliente.getNombre());
            statement.setString(3, cliente.getApellido1());
            statement.setString(4, cliente.getApellido2());
            statement.setInt(5, cliente.getIdCliente());

            statement.executeUpdate();

            deleteTelefonos(cliente.getIdCliente());
            insertTelefonos(cliente.getIdCliente(), cliente.getTelefonos());

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    /**
     * Elimina los teléfonos de un cliente según su ID.
     *
     * @param idCliente ID del cliente.
     * @throws SQLException si ocurre un error al eliminar.
     */
    private void deleteTelefonos(int idCliente) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_TELEFONOS_QUERY)) {
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina un cliente y sus teléfonos de la base de datos.
     *
     * @param id ID del cliente a eliminar.
     * @throws SQLException si ocurre un error en el proceso.
     */
    public void deleteClienteById(int id) throws SQLException {
        connection.setAutoCommit(false);

        try {
            deleteTelefonos(id);

            try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
