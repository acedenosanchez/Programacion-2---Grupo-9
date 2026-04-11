package aplication;

import java.util.Scanner;

public class TestExercise extends Exercise{

    public TestExercise(Scanner scnr) {
        super(scnr);
    }

    @Override
    protected void exerciseLogic() {
        System.out.println("\nWelcome to the test Exercise."
        + "\nmm: Main menu. ");

        String userInput = scanner.nextLine().toLowerCase();

        if(userInput.equals("mm"))
            runnig = false; else System.out.println("\n Invalid input, try again...");
    }
}
