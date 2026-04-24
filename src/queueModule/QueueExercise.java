package queueModule;


import aplication.Exercise;
import java.util.Scanner;


public class QueueExercise extends Exercise {

    private SimpleQueue<String> queue;
    private boolean firstTime = true;

    public QueueExercise(Scanner scanner) {
        super(scanner);
        queue = new SimpleArrayQueue<>();
    }

    @Override
    protected void exerciseLogic() {
        if (firstTime) {
            System.out.println("¡Bienvenido al módulo de gestión de Colas!");
            firstTime = false;
        } else {

            System.out.println("\n- Estado de la Queue-");
            System.out.println("¿Está vacía?: " + queue.isEmpty());
            System.out.println("Cantidad de elementos (size): " + queue.size());
        }

        menuLogic();
    }
    // Menu
    private void menuLogic() {
        System.out.println("\nSeleccione una operación:"
                + "\n1. Enqueue (Agregar elemento)"
                + "\n2. Dequeue (Remover primer elemento)"
                + "\n3. Peek (Ver frente)"
                + "\n4. Clear (Vaciar cola)"
                + "\n0. Volver al menú principal");

        String option = scanner.nextLine();

        switch (option) {
            case "1": runEnqueue(); break;
            case "2": runDequeue(); break;
            case "3": runPeek(); break;
            case "4": runClear(); break;
            case "0": running = false; break;
            default:
                System.out.println("Entrada inválida, intente nuevamente.");
                menuLogic();
                break;
        }
    }
    // Metodos de cola
    private void runEnqueue() {
        String respuesta = "si";
        while (respuesta.equalsIgnoreCase("si")) {
            System.out.print("Ingrese el elemento: ");
            String dato = scanner.nextLine();
            queue.enqueue(dato);
            System.out.println("Elemento '" + dato + "' agregado.");

            System.out.print("¿Desea agregar otro? (si/no): ");
            respuesta = scanner.nextLine();
        }
    }

    private void runDequeue() {
        String respuesta = "si";
        while (respuesta.equalsIgnoreCase("si")) {
            if (queue.isEmpty()) { // Validación
                System.out.println("No hay elementos para remover.");
                break;
            }
            String removido = queue.dequeue();
            System.out.println("Se eliminó el frente: " + removido);

            if (queue.isEmpty()) break;

            System.out.print("¿Desea desencolar otro? (si/no): ");
            respuesta = scanner.nextLine();
        }
    }

    private void runPeek() {
        if (queue.isEmpty()) { //
            System.out.println("La cola está vacía. No hay elementos al frente.");
        } else {
            System.out.println("Elemento al frente: " + queue.peek());
        }
    }

    private void runClear() {
        if (queue.isEmpty()) {
            System.out.println("La estructura ya se encuentra vacía.");
        } else {
            queue.clear();
            System.out.println("Cola vaciada correctamente.");
        }
    }

    private boolean confirmarRepeticion() {
        System.out.print("¿Desea repetir la operación? (si/no): ");
        String respuesta = scanner.nextLine().toLowerCase();
        while (!respuesta.equals("si") && !respuesta.equals("no")) {
            System.out.print("Entrada inválida. Escriba 'si' o 'no': ");
            respuesta = scanner.nextLine().toLowerCase();
        }
        return respuesta.equals("si");
    }
}