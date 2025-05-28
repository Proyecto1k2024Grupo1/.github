// View/DependienteView.java
package View;

import Model.Dependiente;

import java.util.List;
import java.util.Scanner;

public class DependienteView {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n----- Menú Dependiente -----");
        System.out.println("1. Insertar dependiente");
        System.out.println("2. Actualizar dependiente");
        System.out.println("3. Eliminar dependiente");
        System.out.println("4. Ver todos los dependientes");
        System.out.println("5. Salir");
        System.out.print("Selecciona una opción: ");
    }

    public int leerOpcion() {
        return Integer.parseInt(scanner.nextLine());
    }

    public Dependiente leerDatosDependiente() {
        System.out.print("Introduce el DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Introduce el salario: ");
        double salario = Double.parseDouble(scanner.nextLine());
        System.out.print("Introduce el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduce la fecha de nacimiento (yyyy-mm-dd): ");
        String fnac = scanner.nextLine();
        System.out.print("Introduce el horario: ");
        String horario = scanner.nextLine();
        return new Dependiente(dni, salario, java.time.LocalDate.parse(fnac), nombre, horario);
    }

    public String leerDni() {
        System.out.print("Introduce el DNI: ");
        return scanner.nextLine();
    }

    public void mostrarDependientes(List<Dependiente> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay dependientes registrados.");
        } else {
            for (Dependiente d : lista) {
                System.out.println("DNI: " + d.getDni() + ", Nombre: " + d.getNombre()
                        + ", Salario: " + d.getSalario() + ", Horario: " + d.getHorario());
            }
        }
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }
}
