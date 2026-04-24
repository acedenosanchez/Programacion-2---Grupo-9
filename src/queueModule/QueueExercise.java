package queueModule;

import aplication.Exercise;
import java.util.Scanner;


public class QueueExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleQueue<String> queue;

    public QueueExercise(Scanner scanner) {
        super(scanner);
        queue = new SimpleArrayQueue<String>();
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                enqueueLogic();
                break;
            case 2:
                dequeueLogic();
            case 3:
                peekLogic();
            case 4:
                clearLogic();
                break;
        }
    }

    private void menuLogic() {
        if (firstTime) {
            System.out.println("Welcome to the Queue exercise.");
            firstTime = false;
        } else {
            System.out.println("\nQueue size: " + queue.size()
                    + "\nQueue is empty: " + queue.isEmpty());
        }

        System.out.println("\nChoose an option:"
                + "\nenqueue: Enqueue element."
                + "\ndequeue: Dequeue element."
                + "\npeek: Peek element."
                + "\nclear: Clear queue."
                + "\nmm: Main menu.");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {
            case "enqueue":
                currentPhase = 1;
                break;
            case "dequeue":
                currentPhase = 2;
                break;
            case "peek":
                currentPhase = 3;
                break;
            case "clear":
                currentPhase = 4;
                break;
            case "mm":
                running = false;
                break;
            default:
                System.out.println("\nInvalid input, try again...");
                break;
        }
    }

    // Metodos de cola
    private void enqueueLogic() {
        boolean repeat = true;

        while (repeat) {
            System.out.print("\nEnter an element to enqueue:");
            String element = scanner.nextLine();

            queue.enqueue(element);

            System.out.println("\nElement enqueued correctly.");

            boolean validAnswer = false;
            while (!validAnswer) {
                System.out.println("\nRepeat operation? (yes/no)");
                String answer = scanner.nextLine().toLowerCase();

                if (answer.equals("yes")) {
                    validAnswer = true;
                } else if (answer.equals("no")) {
                    validAnswer = true;
                    repeat = false;
                } else {
                    System.out.println("\nInvalid input, try again...");
                }
            }
        }

        currentPhase = 0;
    }

    private void dequeueLogic() {
        boolean repeat = true;

        while (repeat) {
            if (queue.isEmpty()) { // Validación
                System.out.println("\nThe queue is empty. Operation not possible.");
                break;
            }

            String removedElement = queue.dequeue();
            System.out.println("\nRemoved element: " + removedElement);

            boolean validAnswer = false;
            while (!validAnswer) {
                System.out.println("\nRepeat operation? (yes/no)");
                String answer = scanner.nextLine().toLowerCase();

                if (answer.equals("yes")) {
                    validAnswer = true;
                } else if (answer.equals("no")) {
                    validAnswer = true;
                    repeat = false;
                } else {
                    System.out.println("\nInvalid input, try again...");
                }
            }
        }

        currentPhase = 0;
    }

    private void peekLogic() {
        if (queue.isEmpty()) {
            System.out.println("\nThe queue is empty. Operation not possible.");
        } else {
            System.out.println("\nFirst element: " + queue.peek());
        }

        currentPhase = 0;
    }

    private void clearLogic() {
        if (queue.isEmpty()) {
            System.out.println("\nThe queue is already empty.");
        } else {
            queue.clear();
            System.out.println("\nQueue cleared.");
        }

        currentPhase = 0;
    }
}