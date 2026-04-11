package listModule;
import aplication.Exercise;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class  ListExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private List<String> list;

    public ListExercise(Scanner scnr) {
        super(scnr);
        list = new ArrayList<String>();
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
             if (i < list.size()-1) fullList += ",";
             fullList += " ,";
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
        +"\nclear: clear");
    }

    private void addLogic(){

    }
    private void removeByIndexLogic(){
    }
    private void removeByRefLogic(){
    }
    private void clearLogic(){

    }
}
