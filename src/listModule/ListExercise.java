package listModule;
import aplication.Exercise;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class  ListExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleList<String> list;

    public ListExercise(Scanner scnr) {
        super(scnr);
        //Elemento a cambiar para implementar estatica o dinamica+
        list = new SimpleLinkedList<String>();
    }
    @Override
    protected void exerciseLogic() {
        switch(currentPhase){
            case 0:
                menuLogic();
                break;
            case 1:
                addLogic();
                break;
            case 2:
                removeByIndexLogic();
                break;
            case 3:
                removeByRefLogic();
                break;
            case 4:
                clearLogic();
                break;
        }
    }

    private void menuLogic(){
        if (firstTime)
        {
            System.out.println("Welcome to the List exercise");
            firstTime = false;
        }
        else{
            //mostrar todos los elementos separados por coma
            //ej: hola , 33, chau
            String fullList = "";
            for(int i = 0; i < list.size(); i++)
            {
             //Imprimimos los elemetos de la lista
             fullList += list.get(i);

             //arreglamos el display
                if (i < list.size() - 1) {
                    fullList += ", ";
                }
            }

            //mostrar cantidad de elementos (ej:3)
            //mostrar si esta vacia (ej: false)

            System.out.println("\nList contents: " + fullList
                    + "\nList size: " + list.size()
                    + "\nList is empty: " + list.isEmpty());

        }

        System.out.println("\nChoose an Option;"
        + "\n add: add Element."
        +"\nrem ind: Remove by index"
        +"\nclear: clear"
        +"\nrem ref: Remove by reference");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput){
            case "add":
                currentPhase = 1;
                break;
            case "rem ind":
                currentPhase = 2;
                break;
            case "rem ref":
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

    private void addLogic(){
        boolean repeat = true;

        while (repeat) {
            System.out.println("\nEnter an element to add:");
            String element = scanner.nextLine();
            list.add(element);

            String fullList = "";
            for (int i = 0; i < list.size(); i++) {
                fullList += list.get(i);
                if (i < list.size() - 1) {
                    fullList += ", ";
                }
            }

            System.out.println("\nList contents: " + fullList);

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
    private void removeByIndexLogic(){
        boolean repeat = true;

        while (repeat) {
            if (list.isEmpty()) {
                System.out.println("\nThe list is empty. Operation not possible.");
                break;
            }

            System.out.println("\nEnter index to remove:");
            String input = scanner.nextLine();

            try {
                int index = Integer.parseInt(input);

                if (index < 0 || index >= list.size()) {
                    System.out.println("\nInvalid index.");
                } else {
                    list.remove(index);

                    String fullList = "";
                    for (int i = 0; i < list.size(); i++) {
                        fullList += list.get(i);
                        if (i < list.size() - 1) {
                            fullList += ", ";
                        }
                    }

                    System.out.println("\nList contents: " + fullList);
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input, index must be a number.");
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
    private void removeByRefLogic(){
        boolean repeat = true;

        while (repeat) {
            if (list.isEmpty()) {
                System.out.println("\nThe list is empty. Operation not possible.");
                break;
            }

            System.out.println("\nEnter element to remove:");
            String element = scanner.nextLine();

            boolean removed = list.remove(element);

            if (!removed) {
                System.out.println("\nElement not found.");
            }

            String fullList = "";
            for (int i = 0; i < list.size(); i++) {
                fullList += list.get(i);
                if (i < list.size() - 1) {
                    fullList += ", ";
                }
            }

            System.out.println("\nList contents: " + fullList);

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
    private void clearLogic(){
        list.clear();
        System.out.println("\nList contents: ");
        currentPhase = 0;
    }
}
