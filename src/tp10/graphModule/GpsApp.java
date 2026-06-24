package tp10.graphModule;

import tp10.graphModule.ListGraph;
import tp02.tp03.listModule.SimpleList;

import java.util.Scanner;

public class GpsApp {

    private ListGraph<String> map;
    private Scanner scanner;
    private Dijkstra dijkstra;

    public GpsApp() {
        map = new ListGraph<String>();
        scanner = new Scanner(System.in);
        dijkstra = new Dijkstra();
        loadInitialMap();
    }

    public void start() {
        int option = -1;

        System.out.println("Mapa cargado inicialmente:");
        printMap();

        while (option != 0) {
            printMenu();
            option = readInteger();

            switch (option) {
                case 1:
                    printMap();
                    break;

                case 2:
                    calculateShortestPath();
                    break;

                case 3:
                    addStreet();
                    break;

                case 4:
                    removeStreet();
                    break;

                case 0:
                    System.out.println("Saliendo del GPS...");
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("===== GPS =====");
        System.out.println("1. Ver mapa");
        System.out.println("2. Calcular camino más corto");
        System.out.println("3. Agregar calle");
        System.out.println("4. Eliminar calle");
        System.out.println("0. Salir");
        System.out.print("Elegí una opción: ");
    }

    private void loadInitialMap() {
        map.addEdge("UADE", "Obelisco", 3);
        map.addEdge("UADE", "Retiro", 7);
        map.addEdge("Obelisco", "Retiro", 4);
        map.addEdge("Obelisco", "Palermo", 8);
        map.addEdge("Retiro", "Recoleta", 3);
        map.addEdge("Recoleta", "Palermo", 4);
        map.addEdge("Palermo", "Belgrano", 6);
        map.addEdge("Retiro", "Belgrano", 10);
        map.addEdge("Belgrano", "Nuñez", 4);
        map.addEdge("Palermo", "Nuñez", 9);
    }

    private void printMap() {
        System.out.println();
        System.out.println("Mapa actual:");
        map.printGraph();
    }

    private void calculateShortestPath() {
        System.out.print("Ingresá origen: ");
        String origin = scanner.nextLine();

        System.out.print("Ingresá destino: ");
        String destination = scanner.nextLine();

        if (!map.containsVertex(origin)) {
            System.out.println("El origen no existe en el mapa.");
            return;
        }

        if (!map.containsVertex(destination)) {
            System.out.println("El destino no existe en el mapa.");
            return;
        }

        Gps result = dijkstra.shortestPath(map, origin, destination);

        if (result.getTotalDistance() == -1) {
            System.out.println("No existe camino entre " + origin + " y " + destination + ".");
            return;
        }

        System.out.println("Camino más corto:");
        printPath(result.getPath());
        System.out.println("Distancia total: " + result.getTotalDistance());
    }

    private void printPath(SimpleList<String> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));

            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
    }

    private void addStreet() {
        System.out.print("Desde: ");
        String from = scanner.nextLine();

        System.out.print("Hasta: ");
        String to = scanner.nextLine();

        System.out.print("Distancia/costo: ");
        int weight = readInteger();

        if (weight <= 0) {
            System.out.println("La distancia debe ser mayor a 0.");
            return;
        }

        boolean added = map.addEdge(from, to, weight);

        if (added) {
            System.out.println("Calle agregada o actualizada.");
        } else {
            System.out.println("No hubo cambios.");
        }

        printMap();
    }

    private void removeStreet() {
        System.out.print("Desde: ");
        String from = scanner.nextLine();

        System.out.print("Hasta: ");
        String to = scanner.nextLine();

        boolean removed = map.removeEdge(from, to);

        if (removed) {
            System.out.println("Calle eliminada.");
        } else {
            System.out.println("No se encontró esa calle.");
        }

        printMap();
    }

    private int readInteger() {
        try {
            int number = Integer.parseInt(scanner.nextLine());
            return number;
        } catch (Exception e) {
            return -1;
        }
    }
}