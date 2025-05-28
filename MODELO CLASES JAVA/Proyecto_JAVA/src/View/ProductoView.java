package view;

import java.util.Scanner;

public class ProductoView {

    private final Scanner scanner = new Scanner(System.in);

    public int mostrarMenuYLeerOpcion() {
        System.out.println("********* Menú de Productos *********");
        System.out.println("1. Agregar producto propio");
        System.out.println("2. Agregar producto ajeno");
        System.out.println("3. Mostrar todos los productos");
        System.out.println("4. Modificar producto propio");
        System.out.println("5. Modificar producto ajeno");
        System.out.println("6. Eliminar producto propio");
        System.out.println("7. Eliminar producto ajeno");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");
        return leerIntValido();
    }

    public String leerString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public int leerInt(String mensaje) {
        System.out.print(mensaje);
        return leerIntValido();
    }

    public double leerDouble(String mensaje) {
        System.out.print(mensaje);
        return leerDoubleValido();
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

    private double leerDoubleValido() {
        while (true) {
            try {
                double val = Double.parseDouble(scanner.nextLine());
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Introduce un número válido: ");
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
