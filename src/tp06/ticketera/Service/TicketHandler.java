package tp06.ticketera.Service;

import tp06.ticketera.Class.Ticket;
import tp02.tp03.listModule.SimpleLinkedList;
import tp06.priorityQueue.SimpleLinkedPriorityQueue;
import tp04.queueModule.SimpleLinkedQueue;
import tp04.queueModule.SimpleQueue;

import java.util.Scanner;

public class TicketHandler {

    private final SimpleLinkedPriorityQueue<Ticket> queue;
    private final SimpleLinkedList<Ticket> resolvedTickets;
    private final Scanner scanner;
    private int nextTicketNumber;
    private boolean exit;

    //Constructor
    public TicketHandler(Scanner scanner) {
        this.scanner = scanner;
        this.queue = new SimpleLinkedPriorityQueue<>();
        this.resolvedTickets = new SimpleLinkedList<>();
        this.nextTicketNumber = 1;
        this.exit = false;
    }

    public void run() {
        System.out.println("Bienvenido a la ticketera de reclamos");

        while (!exit) {
            showMenu();
            String userInput = scanner.nextLine().trim().toLowerCase();
            menuOptions(userInput);
        }
    }

    private void showMenu() {
        System.out.println("\nTickets pendientes: " + queue.size());
        System.out.println("Elegí una opción:"
                + "\n1: Crear Ticket"
                + "\n2: Visualizar tickets pendientes"
                + "\n3: Visualizar tickets resueltos"
                + "\n4: Vaciar tickets pendientes"
                + "\nmm: Volver al menú principal");
    }

    private void menuOptions(String input) {
        switch (input) {
            case "1" -> addTicket();
            case "2" -> showPendingTickets();
            case "3"-> showResolvedTickets();
            case "4" -> clearTickets();
            case "mm" -> exit = true;
            default -> System.out.println("Opción inválida. Intentá nuevamente.");
        }
    }



    private void addTicket() {
        String title = UserInputText("Ingresá el título del reporte: ");
        String description = UserInputText("Ingresá la descripción del reporte: ");
        System.out.println("Ingresá la urgencia del reporte: ");
        Ticket.UrgencyLevel urgency = askUrgency();

        Ticket ticket = new Ticket(title, description, urgency, nextTicketNumber, "Abierto");
        nextTicketNumber++;

        queue.enqueue(ticket, priorityToNumber(urgency));
        System.out.println("Reporte creado correctamente.");
    }

    private void showPendingTickets() {
        if (queue.isEmpty()) {
            System.out.println("No hay tickets pendientes.");
            return;
        }

        //**
        // Creamos una cola para poder visualizar el resto de tickets sin necesidad de tener que resolverlo , es una estructura auxiliar.
        // */

        SimpleQueue<Ticket> unresolvedTickets = new SimpleLinkedQueue<>();

        boolean StillWatching = true;

        while (!queue.isEmpty() && StillWatching) {
            Ticket ticket = queue.dequeue();

            System.out.println("\nPróximo reporte según urgencia:");
            System.out.println(ticket);

            System.out.println("\nElegí una acción:"
                    + "\n1: Marcar como resuelto"
                    + "\n2: Modificar Criticidad"
                    + "\n3: Dejar en cola y ver el siguiente"
                    + "\n4: Dejar en cola y volver al menú");

            String option = scanner.nextLine().trim();

            switch (option) {
                case "1" -> {
                    ticket.setState("Resuelto");
                    System.out.println("Reporte #" + ticket.getNumber() + " marcado como resuelto.");
                    resolvedTickets.add(ticket);
                }
                case "2"->{
                    System.out.println("Ingrese la nueva criticidad del reporte: ");
                    Ticket.UrgencyLevel urgency = askUrgency();
                    System.out.println("La criticidad del reporte se modifico a : " + urgency);
                    ticket.setUrgency(urgency);
                    unresolvedTickets.enqueue(ticket);
                }
                case "3" -> {
                    //El ticket que se visualizó entra a la estructura auxiliar.
                    unresolvedTickets.enqueue(ticket);

                    if (queue.isEmpty()) {
                        System.out.println("No hay más tickets para visualizar. El reporte queda pendiente.");
                        StillWatching = false;
                    }
                }
                case "4" -> {
                    unresolvedTickets.enqueue(ticket);
                    StillWatching = false;
                }
                default -> {
                    System.out.println("Opción inválida. El reporte queda en la cola.");
                    unresolvedTickets.enqueue(ticket);
                    StillWatching = false;
                }
            }
        }

        restoreUnresolvedTickets(unresolvedTickets);

        if (queue.isEmpty()) {
            System.out.println("No quedan tickets pendientes.");
        }
    }

    private void showResolvedTickets() {
        if (resolvedTickets.isEmpty()) {
            System.out.println("No hay tickets resueltos.");
            return;
        }

        System.out.println("\nTickets resueltos: ");
        for (int i = 0; i < resolvedTickets.size(); i++ ) {
            System.out.println(resolvedTickets.get(i));
            System.out.println("--------------------------");
        }


    }


    private void clearTickets() {
        if (queue.isEmpty()) {
            System.out.println("No hay tickets para eliminar.");
            return;
        }

        System.out.println("¿Seguro que querés vaciar todos los tickets?"
                + "\n1: Sí"
                + "\n2: No");

        String option = scanner.nextLine().trim();

        if (option.equals("1")) {
            queue.clear();
            System.out.println("Todos los tickets pendientes fueron eliminados.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }


    //Metodos de servicio

    //**
    // Una vez visualizados los tickets a resolver se vacia la estructura auxiliar y se cargan nuevamente por el orden de prioridad en la cola de prioridad
    // */
    private void restoreUnresolvedTickets(SimpleQueue<Ticket> unresolvedTickets) {
        while (!unresolvedTickets.isEmpty()) {
            Ticket ticket = unresolvedTickets.dequeue();
            queue.enqueue(ticket, priorityToNumber(ticket.getUrgency()));
        }
    }

    //**
    // Esta función recibe los inputs de textos ingresados por los usuarios desde la consola
    // */
    private String UserInputText(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("El campo no puede estar vacío.");
        }
    }



    private Ticket.UrgencyLevel askUrgency() {
        while (true) {
            System.out.println(
                    "1: Crítico"
                    + "\n2: Alto"
                    + "\n3: Medio"
                    + "\n4: Bajo");

            String input = scanner.nextLine().trim();

            try {
                return Ticket.UrgencyLevel.fromOption(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Urgencia inválida. Ingresá un número del 1 al 4.");
            }
        }
    }

    private int priorityToNumber(Ticket.UrgencyLevel urgency) {
        return switch (urgency) {
            case CRITICO -> 0;
            case ALTO -> 1;
            case MEDIO -> 2;
            case BAJO -> 3;
        };
    }
}