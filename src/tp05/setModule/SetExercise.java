package tp05.setModule;

import tp01.application.Exercise;

import java.util.Scanner;

public class SetExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleSet<String> setA;
    private SimpleSet<String> setB;
    private SimpleSet<String> selectedSet;

    public SetExercise(Scanner scnr) {
        super(scnr);
        setA = new SimpleArraySet<String>();
        setB = new SimpleArraySet<String>();
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                selectSetLogic();
                break;
            case 2:
                addLogic();
                break;
            case 3:
                removeLogic();
                break;
            case 4:
                containsLogic();
                break;
        }
    }

    private String setToString(SimpleSet<String> set) {
        String result = "";
        Object[] elements = set.toArray();

        for (int i = 0; i < elements.length; i++) {
            result += elements[i];

            if (i < elements.length - 1) {
                result += ", ";
            }
        }

        return result;
    }

    private void menuLogic() {
        if (firstTime) {
            System.out.println("Welcome to the Set exercise.");
            firstTime = false;
        } else {
            System.out.println("\nSet A contents: " + setToString(setA)
                    + "\nSet A size: " + setA.size()
                    + "\nSet A is empty: " + setA.isEmpty()
                    + "\n\nSet B contents: " + setToString(setB)
                    + "\nSet B size: " + setB.size()
                    + "\nSet B is empty: " + setB.isEmpty());
        }

        System.out.println("\nChoose an option:"
                + "\nset: Select Set A or Set B."
                + "\nunion: Show A union B."
                + "\nintersect: Show A intersect B."
                + "\ndiff a: Show A difference B."
                + "\ndiff b: Show B difference A."
                + "\nmm: Main menu.");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {
            case "set":
                currentPhase = 1;
                break;
            case "union":
                System.out.println("\nA union B: " + setToString(setA.unionWith(setB)));
                break;
            case "intersect":
                System.out.println("\nA intersect B: " + setToString(setA.intersectWith(setB)));
                break;
            case "diff a":
                System.out.println("\nA difference B: " + setToString(setA.differenceWith(setB)));
                break;
            case "diff b":
                System.out.println("\nB difference A: " + setToString(setB.differenceWith(setA)));
                break;
            case "mm":
                running = false;
                break;
            default:
                System.out.println("\nInvalid input, try again...");
                break;
        }
    }

    private void selectSetLogic() {
        System.out.println("\nChoose a Set:"
                + "\na: Set A."
                + "\nb: Set B."
                + "\nback: Back to Set menu.");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {
            case "a":
                selectedSet = setA;
                System.out.println("\nSet A selected.");
                selectedSetMenuLogic();
                break;
            case "b":
                selectedSet = setB;
                System.out.println("\nSet B selected.");
                selectedSetMenuLogic();
                break;
            case "back":
                currentPhase = 0;
                break;
            default:
                System.out.println("\nInvalid input, try again...");
                break;
        }
    }

    private void selectedSetMenuLogic() {
        System.out.println("\nChoose an option:"
                + "\nadd: Add element."
                + "\nremove: Remove element."
                + "\ncontains: Check if elements exists."
                + "\nback: Back to main Set menu.");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {
            case "add":
                currentPhase = 2;
                break;
            case "remove":
                currentPhase = 3;
                break;
            case "contains":
                currentPhase = 4;
                break;
            case "back":
                currentPhase = 0;
                break;
            default:
                System.out.println("\nInvalid input, try again...");
                currentPhase = 1;
                break;
        }
    }

    private void addLogic() {
        boolean repeat = true;

        while (repeat) {
            System.out.println("\nEnter an element to add:");
            String element = scanner.nextLine();

            boolean added = selectedSet.add(element);

            if (added) {
                System.out.println("\nElement added correctly.");
            } else {
                System.out.println("\nElement already exists. It was not added.");
            }

            System.out.println("\nSelected Set contents: " + setToString(selectedSet));

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

    private void removeLogic() {
        boolean repeat = true;

        while (repeat) {
            if (selectedSet.isEmpty()) {
                System.out.println("\nThe selected Set is empty. Operation not possible.");
                break;
            }

            System.out.println("\nEnter an element to remove:");
            String element = scanner.nextLine();

            boolean removed = selectedSet.remove(element);

            if (removed) {
                System.out.println("\nElement removed correctly.");
            } else {
                System.out.println("\nElement not found. It was not removed.");
            }

            System.out.println("\nSelected Set contents: " + setToString(selectedSet));

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

    private void containsLogic() {
        boolean repeat = true;

        while (repeat) {
            if (selectedSet.isEmpty()) {
                System.out.println("\nThe selected Set is empty. Operation not possible.");
                break;
            }

            System.out.println("\nEnter an element to search:");
            String element = scanner.nextLine();

            boolean exists = selectedSet.contains(element);

            if (exists) {
                System.out.println("\nElement exists in the selected Set.");
            } else {
                System.out.println("\nElement does not exist in the selected Set.");
            }

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
}