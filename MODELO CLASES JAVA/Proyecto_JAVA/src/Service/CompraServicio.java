package Service;

import DAO.*;
import Model.*;

import java.sql.SQLException;
import java.util.*;

public class CompraServicio {

    private final CompraDAO compraDAO = CompraDAO.getInstance();
    private final LineaDeTicketDAO lineaDAO = LineaDeTicketDAO.getInstance();
    private final ClienteDAO clienteDAO = ClienteDAO.getInstance();
    private final DependienteDAO dependienteDAO = DependienteDAO.getInstance();


    public void listarCompras() throws SQLException {
        List<Compra> compras = compraDAO.getAllCompras();
        if (compras.isEmpty()) {
            System.out.println("No hay compras registradas.");
        } else {
            compras.forEach(compra -> {
                System.out.println("Compra #" + compra.getNumCompra() + ", Cliente: " +
                        (compra.getCliente() != null ? compra.getCliente().getNombre() : "No asignado"));
            });
        }
    }

    public void crearCompra(Scanner scanner) throws SQLException {
        Compra compra = new Compra();

        System.out.print("ID del cliente: ");
        int clienteId = leerEntero(scanner);
        Cliente cliente = clienteDAO.getClienteById(clienteId);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.print("DNI del dependiente: ");
        String dni = scanner.nextLine();
        Dependiente dependiente = dependienteDAO.getDependienteByDni(dni);
        if (dependiente == null) {
            System.out.println("Dependiente no encontrado.");
            return;
        }

        compra.setCliente(cliente);
        compra.setDependiente(dependiente);

        List<LineaDeTicket> lineas = new ArrayList<>();
        boolean agregar = true;

        while (agregar) {
            System.out.print("ID del producto: ");
            int productoId = leerEntero(scanner);
            Producto producto = buscarProducto(productoId);

            if (producto != null) {
                System.out.print("Cantidad: ");
                int cantidad = leerEntero(scanner);
                lineas.add(new LineaDeTicket(compra, producto, cantidad, lineas.size() + 1));
                System.out.print("¿Agregar otro producto? (sí/no): ");
                agregar = scanner.nextLine().equalsIgnoreCase("sí");
            } else {
                System.out.println("Producto no encontrado.");
            }
        }

        int numCompra = compraDAO.insertCompra(compra);
        compra.setNumCompra(numCompra);

        for (LineaDeTicket linea : lineas) {
            lineaDAO.insertLineaDeTicket(linea);
        }
        System.out.println("Compra registrada con éxito.");
    }

    public void mostrarDetalles(Scanner scanner) throws SQLException {
        System.out.print("Número de compra: ");
        int numCompra = leerEntero(scanner);
        Compra compra = compraDAO.getCompraByNumCompra(numCompra);

        if (compra != null) {
            System.out.println("Cliente: " + compra.getCliente().getNombre());
            for (LineaDeTicket linea : lineaDAO.getAllLineasDeTicketByNumCompra(numCompra)) {
                System.out.println("Producto: " + linea.getProducto().getNombre() + ", Cantidad: " + linea.getCantidad());
            }
        } else {
            System.out.println("Compra no encontrada.");
        }
    }

    public void eliminarCompra(Scanner scanner) throws SQLException {
        System.out.print("Número de compra a eliminar: ");
        int numCompra = leerEntero(scanner);
        lineaDAO.deleteAllLineasDeCompra(numCompra);
        System.out.println("Compra eliminada.");
    }

    public void modificarCompra(Scanner scanner) throws SQLException {
        System.out.print("Número de compra a modificar: ");
        int numCompra = leerEntero(scanner);
        Compra compra = compraDAO.getCompraByNumCompra(numCompra);
        if (compra == null) {
            System.out.println("Compra no encontrada.");
            return;
        }

        List<LineaDeTicket> lineas = lineaDAO.getAllLineasDeTicketByNumCompra(numCompra);
        if (lineas.isEmpty()) {
            System.out.println("No hay líneas en esta compra.");
            return;
        }

        for (LineaDeTicket l : lineas) {
            System.out.println("Línea: " + l.getNumLinea() + " - " + l.getProducto().getNombre() + " x " + l.getCantidad());
        }

        System.out.print("Número de línea a modificar: ");
        int numLinea = leerEntero(scanner);
        LineaDeTicket linea = lineas.stream().filter(l -> l.getNumLinea() == numLinea).findFirst().orElse(null);

        if (linea != null) {
            System.out.print("Nueva cantidad (0 para eliminar): ");
            int cantidad = leerEntero(scanner);
            if (cantidad == 0) {
                lineaDAO.deleteLineaDeTicket(numCompra, numLinea);
            } else {
                linea.setCantidad(cantidad);
                lineaDAO.updateLineaDeTicket(linea);
            }
        } else {
            System.out.println("Línea no encontrada.");
        }
    }

    private Producto buscarProducto(int id) throws SQLException {
        Producto p = PropioDAO.getInstance().getPropioByCodigo(id);
        return (p != null) ? p : AjenoDAO.getInstance().getAjenoByCodigo(id);
    }

    private int leerEntero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Número inválido.");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }
}
