package Controller;

import Model.Ajeno;
import Model.Propio;
import service.ProductoService;
import view.ProductoView;

import java.sql.SQLException;
import java.util.List;

public class ProductoController {

    private final ProductoView view = new ProductoView();
    private final ProductoService service = new ProductoService();

    public void iniciar() {
        int opcion;
        do {
            opcion = view.mostrarMenuYLeerOpcion();
            try {
                switch (opcion) {
                    case 1 -> agregarPropio();
                    case 2 -> agregarAjeno();
                    case 3 -> mostrarTodos();
                    case 4 -> modificarPropio();
                    case 5 -> modificarAjeno();
                    case 6 -> eliminarPropio();
                    case 7 -> eliminarAjeno();
                    case 8 -> view.mostrarMensaje("¡Hasta luego!");
                    default -> view.mostrarMensaje("Opción inválida.");
                }
            } catch (SQLException e) {
                view.mostrarMensaje("Error de base de datos: " + e.getMessage());
            }
        } while (opcion != 8);
    }

    private void agregarPropio() throws SQLException {
        String nombre = view.leerString("Nombre: ");
        String tipo = view.leerString("Tipo: ");
        double precio = view.leerDouble("Precio: ");
        service.agregarProductoPropio(nombre, tipo, precio);
        view.mostrarMensaje("Producto propio agregado.");
    }

    private void agregarAjeno() throws SQLException {
        String nombre = view.leerString("Nombre: ");
        String tipo = view.leerString("Tipo: ");
        double precio = view.leerDouble("Precio: ");
        service.agregarProductoAjeno(nombre, tipo, precio);
        view.mostrarMensaje("Producto ajeno agregado.");
    }

    private void mostrarTodos() throws SQLException {
        List<Propio> propios = service.obtenerTodosPropios();
        List<Ajeno> ajenos = service.obtenerTodosAjenos();

        view.mostrarMensaje("------ Productos Propios ------");
        if (propios.isEmpty()) view.mostrarMensaje("No hay productos propios registrados.");
        else propios.forEach(p -> view.mostrarMensaje(p.toString()));

        view.mostrarMensaje("------ Productos Ajenos ------");
        if (ajenos.isEmpty()) view.mostrarMensaje("No hay productos ajenos registrados.");
        else ajenos.forEach(a -> view.mostrarMensaje(a.toString()));
    }

    private void modificarPropio() throws SQLException {
        int id = view.leerInt("ID del producto propio a modificar: ");
        Propio propio = service.obtenerPropioPorId(id);
        if (propio == null) {
            view.mostrarMensaje("Producto no encontrado.");
            return;
        }

        String nombre = view.leerString("Nuevo nombre (actual: " + propio.getNombre() + "): ");
        if (!nombre.isEmpty()) propio.setNombre(nombre);

        String tipo = view.leerString("Nuevo tipo (actual: " + propio.getTipo() + "): ");
        if (!tipo.isEmpty()) propio.setTipo(tipo);

        String precioStr = view.leerString("Nuevo precio (actual: " + propio.getPrecio() + "): ");
        if (!precioStr.isEmpty()) {
            try {
                double precio = Double.parseDouble(precioStr);
                if (precio >= 0) propio.setPrecio(precio);
                else view.mostrarMensaje("Precio no puede ser negativo. Se mantiene el actual.");
            } catch (NumberFormatException e) {
                view.mostrarMensaje("Precio inválido. Se mantiene el actual.");
            }
        }

        service.actualizarPropio(propio);
        view.mostrarMensaje("Producto propio modificado.");
    }

    private void modificarAjeno() throws SQLException {
        int id = view.leerInt("ID del producto ajeno a modificar: ");
        Ajeno ajeno = service.obtenerAjenoPorId(id);
        if (ajeno == null) {
            view.mostrarMensaje("Producto no encontrado.");
            return;
        }

        String nombre = view.leerString("Nuevo nombre (actual: " + ajeno.getNombre() + "): ");
        if (!nombre.isEmpty()) ajeno.setNombre(nombre);

        String tipo = view.leerString("Nuevo tipo (actual: " + ajeno.getTipo() + "): ");
        if (!tipo.isEmpty()) ajeno.setTipo(tipo);

        String precioStr = view.leerString("Nuevo precio (actual: " + ajeno.getPrecio() + "): ");
        if (!precioStr.isEmpty()) {
            try {
                double precio = Double.parseDouble(precioStr);
                if (precio >= 0) ajeno.setPrecio(precio);
                else view.mostrarMensaje("Precio no puede ser negativo. Se mantiene el actual.");
            } catch (NumberFormatException e) {
                view.mostrarMensaje("Precio inválido. Se mantiene el actual.");
            }
        }

        service.actualizarAjeno(ajeno);
        view.mostrarMensaje("Producto ajeno modificado.");
    }

    private void eliminarPropio() throws SQLException {
        int id = view.leerInt("ID del producto propio a eliminar: ");
        Propio propio = service.obtenerPropioPorId(id);
        if (propio == null) {
            view.mostrarMensaje("Producto no encontrado.");
            return;
        }
        service.eliminarPropio(id);
        view.mostrarMensaje("Producto propio eliminado.");
    }

    private void eliminarAjeno() throws SQLException {
        int id = view.leerInt("ID del producto ajeno a eliminar: ");
        Ajeno ajeno = service.obtenerAjenoPorId(id);
        if (ajeno == null) {
            view.mostrarMensaje("Producto no encontrado.");
            return;
        }
        service.eliminarAjeno(id);
        view.mostrarMensaje("Producto ajeno eliminado.");
    }
}
