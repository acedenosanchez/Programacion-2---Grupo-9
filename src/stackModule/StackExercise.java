package stackModule;

import aplication.Exercise;
import java.util.Scanner;

public class StackExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleStack<String> stack;

    public StackExercise(Scanner scanner) {
        super(scanner);
        stack = new SimpleArrayStack<String>();
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                pushLogic();
                break;
            case 2:
                popLogic();
                break;
            case 3:
                peekLogic();
                break;
            case 4:
                clearLogic();
                break;
        }
    }
    private void menuLogic() {
        if (firstTime) {
            System.out.println("Welcome to the Stack exercise");
            firstTime = false;
        } else {
            System.out.println("\nStack size: " + stack.size()
                    + "\nStack is empty: " + stack.isEmpty());
        }

        System.out.println("\nChoose an option:"
                + "\npush: Push element."
                + "\npop: Pop element."
                + "\npeek: Peek element."
                + "\nclear: Clear stack."
                + "\nmm: Main menu.");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {
            case "push":
                currentPhase = 1;
                break;
            case "pop":
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

    private void pushLogic() {
        boolean repeat = true;

        while (repeat) {
            System.out.println("\nEnter an element to push:");
            String element = scanner.nextLine();

            stack.push(element);

            System.out.println("\nElement pushed correctly.");

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

    private void popLogic() {
        boolean repeat = true;

        while (repeat) {
            if (stack.isEmpty()) {
                System.out.println("\nThe stack is empty. Operation not possible.");
                break;
            }

            String removedElement = stack.pop();
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
        if (stack.isEmpty()) {
            System.out.println("\nThe stack is empty. Operation not possible.");
        } else {
            System.out.println("\nLast element: " + stack.peek());
        }

        currentPhase = 0;
    }

    private void clearLogic() {
        if (stack.isEmpty()) {
            System.out.println("\nThe stack is already empty.");
        } else {
            stack.clear();
            System.out.println("\nStack cleared.");
        }

        currentPhase = 0;
    }
}
