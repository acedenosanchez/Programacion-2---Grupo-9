package tp07Dictionary.login;

import tp07Dictionary.dictionary.SimpleLinkedDictionary;
import tp02.tp03.listModule.SimpleList;

public class Logic {

    private static final int MAX_FAILED_ATTEMPTS = 3;

    //Diccionarios para llevar el listado de usuarios
    private SimpleLinkedDictionary<String, String> users;
    //Diccionarios para llevar el listado de intentos de ingreso por user
    private SimpleLinkedDictionary<String, Integer> failedAttempts;
    //Diccionarios para llevar el listado de usuarios bloqueados
    private SimpleLinkedDictionary<String, Boolean> blockedUsers;

    public Logic() {
        users = new SimpleLinkedDictionary<>();
        failedAttempts = new SimpleLinkedDictionary<>();
        blockedUsers = new SimpleLinkedDictionary<>();
    }

    public boolean registerUser(String username, String password) {
        //Validación de los inputs
        validateUsername(username);
        validatePassword(password);

        if (users.containsKey(username)) {
            return false;
        }

        users.put(username, password);
        failedAttempts.put(username, 0);
        blockedUsers.put(username, false);
        return true;
    }


    public LoginResult login(String username, String password) {
        validateUsername(username);
        validatePassword(password);

        if (!users.containsKey(username)) {
            return LoginResult.USER_NOT_FOUND;
        }

        if (isBlocked(username)) {
            return LoginResult.BLOCKED_USER;
        }

        String savedPassword = users.get(username);

        if (savedPassword.equals(password)) {
            resetFailedAttempts(username);
            return LoginResult.SUCCESS;
        }

        increaseFailedAttempts(username);

        if (isBlocked(username)) {
            return LoginResult.BLOCKED_BY_ATTEMPTS;
        }

        return LoginResult.WRONG_PASSWORD;
    }

    public boolean userExists(String username) {
        validateUsername(username);
        return users.containsKey(username);
    }

    public boolean isBlocked(String username) {
        validateUsername(username);

        if (!users.containsKey(username)) {
            return false;
        }

        return blockedUsers.get(username);
    }

    public int getFailedAttempts(String username) {
        validateUsername(username);

        if (!users.containsKey(username)) {
            return -1;
        }

        return failedAttempts.get(username);
    }

    public int getRemainingAttempts(String username) {
        validateUsername(username);

        if (!users.containsKey(username)) {
            return -1;
        }

        return MAX_FAILED_ATTEMPTS - failedAttempts.get(username);
    }

    public int amountOfUsers() {
        return users.size();
    }

    public SimpleList<String> getUsers() {
        return users.keys();
    }


    //Métodos de servicio
    private void increaseFailedAttempts(String username) {
        int currentAttempts = failedAttempts.get(username);
        int newAttempts = currentAttempts + 1;

        failedAttempts.put(username, newAttempts);

        if (newAttempts >= MAX_FAILED_ATTEMPTS) {
            blockedUsers.put(username, true);
        }
    }

    private void resetFailedAttempts(String username) {
        failedAttempts.put(username, 0);
    }

    private void validateUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("El usuario no puede ser null.");
        }

        if (username.trim().isEmpty()) {
            throw new IllegalArgumentException("El usuario no puede estar vacio.");
        }
    }

    private void validatePassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("La contraseña no puede ser null.");
        }

        if (password.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacia.");
        }
    }
}
