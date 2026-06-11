package treeContactos;

import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ContactosExercise contactosExercise = new ContactosExercise(scanner);
        contactosExercise.run();

        scanner.close();
    }
}