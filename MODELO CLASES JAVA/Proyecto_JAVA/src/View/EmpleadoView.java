package View;

import Controller.DependienteController;
import Controller.PanaderoController;
import Controller.RepartidorController;

import java.util.Scanner;

public class EmpleadoView {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n----- Menú Empleado -----");
            System.out.println("1. Menú Repartidor");
            System.out.println("2. Menú Panadero");
            System.out.println("3. Menú Dependiente");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> {
                    RepartidorView repartidorView = new RepartidorView();
                    RepartidorController repartidorController = new RepartidorController();
                    repartidorController.iniciar();
                }
                case 2 -> {
                    PanaderoView panaderoView = new PanaderoView();
                    PanaderoController panaderoController = new PanaderoController();
                    panaderoController.iniciar();
                }
                case 3 -> {
                    DependienteView dependienteView = new DependienteView();
                    DependienteController dependienteController = new DependienteController();
                    dependienteController.iniciar();
                }
                case 4 -> System.out.println("Saliendo del menú empleado...");
                default -> System.out.println("Opción no válida. Inténtelo nuevamente.");
            }

        } while (opcion != 4);
    }
}
