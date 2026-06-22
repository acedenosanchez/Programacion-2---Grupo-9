package tp08.tp09.treeContactos;

import tp01.application.Exercise;
import tp02.tp03.listModule.SimpleList;
import tp08.tp09.treeContactos.treeModule.AVL;

import java.util.Scanner;

public class ContactosExercise extends Exercise {

    private AVL<Contactos> contacts = new AVL<Contactos>();
    private boolean firstTime = true;

    public ContactosExercise(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected void exerciseLogic() {
        if (firstTime) {
            System.out.println("Bienvenido a la aplicación de contactos con AVL.");
            firstTime = false;
        }

        showMenu();
        int option = readIntOption("Seleccione una opción: ");

        switch (option) {
            case 1:
                addContact();
                break;

            case 2:
                editContact();
                break;

            case 3:
                removeContact();
                break;

            case 4:
                showContacts();
                break;

            case 5:
                loadSampleData();
                break;

            case 6:
                clearContacts();
                break;

            case 0:
                running = false;
                System.out.println("Volviendo al menú principal...");
                break;

            default:
                System.out.println("Opción inválida. Intente nuevamente.");
                break;
        }
    }

    private void showMenu() {
        System.out.println();
        System.out.println("===== CONTACTOS =====");
        System.out.println("Cantidad de contactos: " + contacts.size());
        System.out.println("¿Agenda vacía?: " + (contacts.isEmpty() ? "Sí" : "No"));
        System.out.println("Altura del árbol: " + contacts.height());
        System.out.println("¿Árbol balanceado?: " + (contacts.isBalanced() ? "Sí" : "No"));
        System.out.println();
        System.out.println("1. Agregar contacto");
        System.out.println("2. Editar contacto");
        System.out.println("3. Borrar contacto");
        System.out.println("4. Mostrar todos los contactos");
        System.out.println("5. Cargar base de datos preprogramada");
        System.out.println("6. Vaciar agenda");
        System.out.println("0. Volver al menú principal");
        System.out.println();
    }

    private void addContact() {
        System.out.println();
        System.out.println("=== AGREGAR CONTACTO ===");

        String name = readNonEmptyText("Ingrese nombre: ");
        String phoneNumber = readNonEmptyText("Ingrese número: ");
        String email = readNonEmptyText("Ingrese mail: ");

        Contactos newContact = new Contactos(name, phoneNumber, email);

        if (contacts.contains(newContact)) {
            System.out.println("No se pudo agregar. Ya existe un contacto con ese nombre.");
            return;
        }

        contacts.insert(newContact);
        System.out.println("Contacto agregado correctamente.");
    }

    private void editContact() {
        System.out.println();
        System.out.println("=== EDITAR CONTACTO ===");

        if (contacts.isEmpty()) {
            System.out.println("No hay contactos para editar.");
            return;
        }

        String name = readNonEmptyText("Ingrese el nombre del contacto a editar: ");
        Contactos currentContact = findContactByName(name);

        if (currentContact == null) {
            System.out.println("No existe un contacto con ese nombre.");
            return;
        }

        System.out.println("Contacto encontrado:");
        System.out.println(currentContact);
        System.out.println();
        System.out.println("¿Qué dato desea editar?");
        System.out.println("1. Nombre");
        System.out.println("2. Número");
        System.out.println("3. Mail");
        System.out.println("0. Cancelar");

        int option = readIntOption("Seleccione una opción: ");

        switch (option) {
            case 1:
                editName(currentContact);
                break;

            case 2:
                editPhoneNumber(currentContact);
                break;

            case 3:
                editEmail(currentContact);
                break;

            case 0:
                System.out.println("Edición cancelada.");
                break;

            default:
                System.out.println("Opción inválida.");
                break;
        }
    }

    private void editName(Contactos currentContact) {
        String newName = readNonEmptyText("Ingrese el nuevo nombre: ");

        Contactos newContact = new Contactos(
                newName,
                currentContact.getPhoneNumber(),
                currentContact.getEmail()
        );

        if (contacts.contains(newContact)) {
            System.out.println("No se puede editar. Ya existe un contacto con ese nombre.");
            return;
        }

        contacts.remove(currentContact);
        contacts.insert(newContact);

        System.out.println("Nombre editado correctamente.");
    }

    private void editPhoneNumber(Contactos currentContact) {
        String newPhoneNumber = readNonEmptyText("Ingrese el nuevo número: ");

        Contactos updatedContact = new Contactos(
                currentContact.getName(),
                newPhoneNumber,
                currentContact.getEmail()
        );

        contacts.remove(currentContact);
        contacts.insert(updatedContact);

        System.out.println("Número editado correctamente.");
    }

    private void editEmail(Contactos currentContact) {
        String newEmail = readNonEmptyText("Ingrese el nuevo mail: ");

        Contactos updatedContact = new Contactos(
                currentContact.getName(),
                currentContact.getPhoneNumber(),
                newEmail
        );

        contacts.remove(currentContact);
        contacts.insert(updatedContact);

        System.out.println("Mail editado correctamente.");
    }

    private void removeContact() {
        System.out.println();
        System.out.println("=== BORRAR CONTACTO ===");

        if (contacts.isEmpty()) {
            System.out.println("No hay contactos para borrar.");
            return;
        }

        String name = readNonEmptyText("Ingrese el nombre del contacto a borrar: ");
        Contactos contactToRemove = findContactByName(name);

        if (contactToRemove == null) {
            System.out.println("No existe un contacto con ese nombre.");
            return;
        }

        contacts.remove(contactToRemove);
        System.out.println("Contacto borrado correctamente.");
    }

    private void showContacts() {
        System.out.println();
        System.out.println("=== LISTA DE CONTACTOS ===");

        if (contacts.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return;
        }

        SimpleList<Contactos> orderedContacts = contacts.inOrder();

        for (int i = 0; i < orderedContacts.size(); i++) {
            System.out.println((i + 1) + ". " + orderedContacts.get(i));
        }
    }

    private void loadSampleData() {
        System.out.println();
        System.out.println("=== CARGAR BASE DE DATOS PREPROGRAMADA ===");

        insertSampleContact(new Contactos("Ana Pérez", "1122334455", "ana@email.com"));
        insertSampleContact(new Contactos("Bruno Gómez", "1133445566", "bruno@email.com"));
        insertSampleContact(new Contactos("Carla Díaz", "1144556677", "carla@email.com"));
        insertSampleContact(new Contactos("Diego López", "1155667788", "diego@email.com"));
        insertSampleContact(new Contactos("Elena Torres", "1166778899", "elena@email.com"));

        System.out.println("Base de datos cargada correctamente.");
    }

    private void insertSampleContact(Contactos contact) {
        if (!contacts.contains(contact)) {
            contacts.insert(contact);
        }
    }

    private void clearContacts() {
        if (contacts.isEmpty()) {
            System.out.println("La agenda ya está vacía.");
            return;
        }

        contacts.clear();
        System.out.println("Agenda vaciada correctamente.");
    }

    private Contactos findContactByName(String name) {
        Contactos searchContact = new Contactos(name, "0", "placeholder@email.com");
        return contacts.find(searchContact);
    }
    private String readNonEmptyText(String message) {
        String input;

        do {
            System.out.print(message);
            input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("El dato no puede estar vacío.");
            }

        } while (input.isEmpty());

        return input;
    }

    private int readIntOption(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }
}