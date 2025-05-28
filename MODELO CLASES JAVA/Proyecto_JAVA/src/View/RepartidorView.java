package View;

import Model.Repartidor;

import java.util.List;
import java.util.Scanner;

public class RepartidorView {

    private final Scanner scanner = new Scanner(System.in);

    public int mostrarMenuYLeerOpcion() {
        System.out.println("\n----- Menú Repartidor -----");
        System.out.println("1. Insertar repartidor");
        System.out.println("2. Actualizar repartidor");
        System.out.println("3. Eliminar repartidor");
        System.out.println("4. Ver todos los repartidores");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
        return leerIntValido();
    }

    public String leerString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public double leerDouble(String mensaje) {
        System.out.print(mensaje);
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor ingrese un número válido: ");
            }
        }
    }

    public void mostrarRepartidores(List<Repartidor> repartidores) {
        if (repartidores.isEmpty()) {
            System.out.println("No hay repartidores registrados.");
        } else {
            System.out.println("\nLista de repartidores:");
            for (Repartidor r : repartidores) {
                System.out.printf("DNI: %s, Nombre: %s, Salario: %.2f, Matrícula: %s, Fecha Nac.: %s%n",
                        r.getDni(), r.getNombre(), r.getSalario(), r.getMatricula(), r.getFnac());
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    private int leerIntValido() {
        while (true) {
            try {
                int val = Integer.parseInt(scanner.nextLine());
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Introduce un número válido: ");
            }
        }
    }
}
