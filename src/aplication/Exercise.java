package aplication;
import java.util.Scanner;

public abstract class Exercise {
    protected boolean running = true;
    protected Scanner scanner = new Scanner(System.in);

    //CONSTRUCTOR
    public Exercise(Scanner scnr)
    {
        scanner= scnr;
    }

    //Métodos
    public void run(){
        while (running)
            exerciseLogic();
        }

    protected abstract void exerciseLogic();

}
