package aplication;
import java.util.Scanner;

public class MainProgram {
    public boolean running = true;
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

    private void selectExercise (Scanner Scanner){
        System.out.println("\nSelect exercise type: "
        + "\n0: Terminate program."
        + "\n1: Test exercise");

        String userInput = Scanner.nextLine();

        switch (userInput){
            case "0":
                running = false;
                break;
            case "1":
                exercise = new TestExercise(Scanner);
                break;

            default:
                System.out.println("\n Invalid input, try again...");
                selectExercise(Scanner);
                break;
        }
    }
}
