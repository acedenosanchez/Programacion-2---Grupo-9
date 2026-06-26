package tp07Dictionary.login;

import java.util.Scanner;
import tp02.tp03.listModule.SimpleList;

public class Menu {

    private Scanner scanner;
    private Logic logic;
    private boolean exit;

    public Menu() {
        scanner = new Scanner(System.in);
        logic = new Logic();
        exit = false;
    }

    public void start() {
        System.out.println("Bienvenido al sistema de Login");

        while (!exit) {
            showOptions();
            handleOption(readOption());
        }
    }


    // Implementación de la lógica del menú
    private void showOptions() {
        System.out.println("\nSeleccione una opción:");
        System.out.println("1: Iniciar sesión");
        System.out.println("2: Registrar usuario");
        System.out.println("3: Ver cantidad de usuarios registrados");
        System.out.println("4: Ver estado de usuarios");
        System.out.println("0: Salir");
        System.out.print("Opción: ");
    }

    private int readOption() {
        String input = scanner.nextLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void handleOption(int option) {
        switch (option) {
            case 1:
                login();
                break;
            case 2:
                registerUser();
                break;
            case 3:
                showAmountOfUsers();
                break;
            case 4:
                showUsersStatus();
                break;
            case 0:
                exit();
                break;
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
        }
    }

    private void login() {
        System.out.println("\nInicio de sesión");

        if (logic.amountOfUsers() == 0) {
            System.out.println("No hay usuarios registrados. Primero debe registrar un usuario.");
            return;
        }

        String username = readText("Ingrese usuario: ").trim();

        try {
            if (!logic.userExists(username)) {
                System.out.println("El usuario no existe.");
                return;
            }

            if (logic.isBlocked(username)) {
                System.out.println("La cuenta se encuentra bloqueada. No se puede ingresar.");
                return;
            }

            String password = readText("Ingrese contraseña: ").trim();

            LoginResult result = logic.login(username, password);

            switch (result) {
                case SUCCESS:
                    System.out.println("Login exitoso. Bienvenido, " + username + ".");
                    break;

                case WRONG_PASSWORD:
                    System.out.println("Contraseña incorrecta.");
                    System.out.println("Intentos restantes: " + logic.getRemainingAttempts(username));
                    break;

                case BLOCKED_BY_ATTEMPTS:
                    System.out.println("Contraseña incorrecta.");
                    System.out.println("La cuenta fue bloqueada por superar los 3 intentos incorrectos.");
                    break;

                case BLOCKED_USER:
                    System.out.println("La cuenta se encuentra bloqueada. No se puede ingresar.");
                    break;

                case USER_NOT_FOUND:
                    System.out.println("El usuario no existe.");
                    break;
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void registerUser() {
        System.out.println("\nRegistro de usuario");
        String username = readText("Ingrese nuevo usuario: ");
        String password = readText("Ingrese nueva contraseña: ");

        try {
            if (logic.registerUser(username, password)) {
                System.out.println("Usuario registrado correctamente.");
            } else {
                System.out.println("El usuario ya existe. Debe elegir otro nombre.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAmountOfUsers() {
        System.out.println("Usuarios registrados: " + logic.amountOfUsers());
    }

    private void showUsersStatus() {
        System.out.println("\nEstado de usuarios");

        if (logic.amountOfUsers() == 0) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        SimpleList<String> users = logic.getUsers();

        for (int i = 0; i < users.size(); i++) {
            String username = users.get(i);
            String status = logic.isBlocked(username) ? "BLOQUEADO" : "ACTIVO";
            int failedAttempts = logic.getFailedAttempts(username);

            System.out.println(username + " - " + status + " - intentos fallidos: " + failedAttempts);
        }
    }

    private void exit() {
        exit = true;
        System.out.println("Programa finalizado.");
    }


    // Métodos de servicio
    private String readText(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
