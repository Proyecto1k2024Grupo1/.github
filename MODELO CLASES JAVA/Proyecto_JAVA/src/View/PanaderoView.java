package View;

import Model.Panadero;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PanaderoView {

    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n********* MENÚ PANADERO *********");
        System.out.println("1. Añadir panadero");
        System.out.println("2. Mostrar panaderos");
        System.out.println("3. Modificar panadero");
        System.out.println("4. Eliminar panadero");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public int leerOpcion() {
        return Integer.parseInt(scanner.nextLine());
    }

    public Panadero leerPanaderoParaInsertar() {
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Salario: ");
        double salario = Double.parseDouble(scanner.nextLine());
        System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
        LocalDate fnac = LocalDate.parse(scanner.nextLine());

        return new Panadero(dni, salario, fnac, nombre);
    }

    public void mostrarPanaderos(List<Panadero> panaderos) {
        if (panaderos.isEmpty()) {
            System.out.println("No hay panaderos registrados.");
        } else {
            System.out.println("\n--- Lista de panaderos ---");
            for (Panadero p : panaderos) {
                System.out.println("-----------------------------");
                System.out.println("DNI: " + p.getDni());
                System.out.println("Nombre: " + p.getNombre());
                System.out.println("Salario: " + p.getSalario());
                System.out.println("Fecha de nacimiento: " + p.getFnac());
            }
        }
    }

    public String leerDni(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public Panadero leerDatosParaModificar(Panadero panadero) {
        System.out.print("Nuevo nombre (" + panadero.getNombre() + "): ");
        String nombre = scanner.nextLine();
        if (!nombre.isBlank()) {
            panadero.setNombre(nombre);
        }

        System.out.print("Nuevo salario (" + panadero.getSalario() + "): ");
        String salarioStr = scanner.nextLine();
        if (!salarioStr.isBlank()) {
            panadero.setSalario(Double.parseDouble(salarioStr));
        }

        System.out.print("Nueva fecha de nacimiento (" + panadero.getFnac() + "): ");
        String fechaNacimiento = scanner.nextLine();
        if (!fechaNacimiento.isBlank()) {
            panadero.setFnac(LocalDate.parse(fechaNacimiento));
        }

        return panadero;
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
