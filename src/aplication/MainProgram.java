package aplication;

import java.util.Scanner;
import listModule.ListExercise;
import stackModule.StackExercise;
import queueModule.QueueExercise;

public class MainProgram {
    private boolean running = true;
    private Exercise exercise;

    public static void main(String[] args) {
        new MainProgram().run();
    }

    private void run (){
        Scanner scanner = new Scanner(System.in);
        while (running){
            selectExercise(scanner);
            if (exercise != null) exercise.run();
        }
        scanner.close();
        System.out.println("Program terminated");
    }

    private void selectExercise(Scanner scanner) {
        System.out.println("\nSelect exercise type: "
                + "\n0: Terminate program."
                + "\n1: Test exercise."
                + "\n2: List exercise."
                + "\n3: Stack exercise."
                + "\n4: Queue exercise.");

        String userInput = scanner.nextLine();

        switch (userInput) {
            case "0":
                running = false;
                exercise = null;
                break;
            case "1":
                exercise = new TestExercise(scanner);
                break;
            case "2":
                exercise = new ListExercise(scanner);
                break;
            case "3":
                exercise = new StackExercise(scanner);
                break;
            case "4":
                exercise = new QueueExercise(scanner);
                break;
            default:
                System.out.println("\n Invalid input, try again...");
                selectExercise(scanner);
                break;
        }
    }
}