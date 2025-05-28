package Controller;

import Model.Repartidor;
import Service.RepartidorService;
import View.RepartidorView;

import java.sql.SQLException;
import java.time.LocalDate;

public class RepartidorController {

    private final RepartidorView view = new RepartidorView();
    private final RepartidorService service = new RepartidorService();

    public void iniciar() {
        int opcion;
        do {
            opcion = view.mostrarMenuYLeerOpcion();
            try {
                switch (opcion) {
                    case 1 -> insertarRepartidor();
                    case 2 -> actualizarRepartidor();
                    case 3 -> eliminarRepartidor();
                    case 4 -> verTodosRepartidores();
                    case 5 -> view.mostrarMensaje("Saliendo del menú...");
                    default -> view.mostrarMensaje("Opción no válida. Inténtelo nuevamente.");
                }
            } catch (SQLException e) {
                view.mostrarMensaje("Error en la base de datos: " + e.getMessage());
            } catch (Exception e) {
                view.mostrarMensaje("Error: " + e.getMessage());
            }
        } while (opcion != 5);
    }

    private void insertarRepartidor() throws SQLException {
        String dni = view.leerString("Ingrese el DNI del repartidor: ");
        double salario = view.leerDouble("Ingrese el salario del repartidor: ");
        String nombre = view.leerString("Ingrese el nombre del repartidor: ");
        String matricula = view.leerString("Ingrese la matrícula del repartidor: ");
        String fechaStr = view.leerString("Ingrese la fecha de nacimiento (yyyy-mm-dd): ");
        LocalDate fechaNacimiento = LocalDate.parse(fechaStr);

        service.insertarRepartidor(dni, salario, fechaNacimiento, nombre, matricula);
        view.mostrarMensaje("Repartidor insertado correctamente.");
    }

    private void actualizarRepartidor() throws SQLException {
        String dni = view.leerString("Ingrese el DNI del repartidor a actualizar: ");
        Repartidor repartidor = service.buscarPorDni(dni);

        if (repartidor == null) {
            view.mostrarMensaje("Repartidor no encontrado.");
            return;
        }

        double salario = view.leerDouble("Ingrese el nuevo salario: ");
        String nombre = view.leerString("Ingrese el nuevo nombre: ");
        String matricula = view.leerString("Ingrese la nueva matrícula: ");
        String fechaStr = view.leerString("Ingrese la nueva fecha de nacimiento (yyyy-mm-dd): ");
        LocalDate fechaNacimiento = LocalDate.parse(fechaStr);

        repartidor.setSalario(salario);
        repartidor.setNombre(nombre);
        repartidor.setMatricula(matricula);
        repartidor.setFnac(fechaNacimiento);

        service.actualizarRepartidor(repartidor);
        view.mostrarMensaje("Repartidor actualizado correctamente.");
    }

    private void eliminarRepartidor() throws SQLException {
        String dni = view.leerString("Ingrese el DNI del repartidor a eliminar: ");
        service.eliminarRepartidorPorDni(dni);
        view.mostrarMensaje("Repartidor eliminado correctamente.");
    }

    private void verTodosRepartidores() throws SQLException {
        var repartidores = service.obtenerTodosRepartidores();
        view.mostrarRepartidores(repartidores);
    }
}
