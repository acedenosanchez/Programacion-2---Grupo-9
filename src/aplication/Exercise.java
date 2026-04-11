package aplication;
import java.util.Scanner;

public abstract class Exercise {
    protected boolean runnig = true;
    protected Scanner scanner = new Scanner(System.in);

    //CONSTRUCTOR
    public Exercise(Scanner scnr)
    {
        scanner= scnr;
    }

    //Métodos
    public void run(){
        while (runnig)
            exerciseLogic();
        }

    protected abstract void exerciseLogic();

}
