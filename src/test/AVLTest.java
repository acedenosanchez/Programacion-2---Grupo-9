package test;

import listModule.SimpleList;
import treeContactos.Contactos;
import treeModule.AVL;

public class AVLTest {

    public static void main(String[] args) {
        testRotacionSimpleIzquierda();
        testRotacionSimpleDerecha();
        testRotacionDobleIzquierdaDerecha();
        testRotacionDobleDerechaIzquierda();
        testRemocionConRebalanceo();

        System.out.println();
        System.out.println("======================================");
        System.out.println("TODOS LOS TESTS FINALIZARON CORRECTAMENTE");
        System.out.println("======================================");
    }

    private static void testRotacionSimpleIzquierda() {
        System.out.println();
        System.out.println("TEST 1 - Rotación simple izquierda");

        AVL<Contactos> contacts = new AVL<>();

        contacts.insert(new Contactos("Ana", "111", "ana@mail.com"));
        contacts.insert(new Contactos("Bruno", "222", "bruno@mail.com"));
        contacts.insert(new Contactos("Carla", "333", "carla@mail.com"));

        validateTree(contacts, 3, 2);
        validateInOrder(contacts, "Ana", "Bruno", "Carla");
        printInOrder(contacts);
    }

    private static void testRotacionSimpleDerecha() {
        System.out.println();
        System.out.println("TEST 2 - Rotación simple derecha");

        AVL<Contactos> contacts = new AVL<>();

        contacts.insert(new Contactos("Carla", "333", "carla@mail.com"));
        contacts.insert(new Contactos("Bruno", "222", "bruno@mail.com"));
        contacts.insert(new Contactos("Ana", "111", "ana@mail.com"));

        validateTree(contacts, 3, 2);
        validateInOrder(contacts, "Ana", "Bruno", "Carla");
        printInOrder(contacts);
    }

    private static void testRotacionDobleIzquierdaDerecha() {
        System.out.println();
        System.out.println("TEST 3 - Rotación doble izquierda-derecha");

        AVL<Contactos> contacts = new AVL<>();

        contacts.insert(new Contactos("Carla", "333", "carla@mail.com"));
        contacts.insert(new Contactos("Ana", "111", "ana@mail.com"));
        contacts.insert(new Contactos("Bruno", "222", "bruno@mail.com"));

        validateTree(contacts, 3, 2);
        validateInOrder(contacts, "Ana", "Bruno", "Carla");
        printInOrder(contacts);
    }

    private static void testRotacionDobleDerechaIzquierda() {
        System.out.println();
        System.out.println("TEST 4 - Rotación doble derecha-izquierda");

        AVL<Contactos> contacts = new AVL<>();

        contacts.insert(new Contactos("Ana", "111", "ana@mail.com"));
        contacts.insert(new Contactos("Carla", "333", "carla@mail.com"));
        contacts.insert(new Contactos("Bruno", "222", "bruno@mail.com"));

        validateTree(contacts, 3, 2);
        validateInOrder(contacts, "Ana", "Bruno", "Carla");
        printInOrder(contacts);
    }

    private static void testRemocionConRebalanceo() {
        System.out.println();
        System.out.println("TEST 5 - Remoción con rebalanceo");

        AVL<Contactos> contacts = new AVL<>();

        contacts.insert(new Contactos("Ana", "111", "ana@mail.com"));
        contacts.insert(new Contactos("Bruno", "222", "bruno@mail.com"));
        contacts.insert(new Contactos("Carla", "333", "carla@mail.com"));
        contacts.insert(new Contactos("Diego", "444", "diego@mail.com"));
        contacts.insert(new Contactos("Elena", "555", "elena@mail.com"));
        contacts.insert(new Contactos("Federico", "666", "federico@mail.com"));
        contacts.insert(new Contactos("Gabriela", "777", "gabriela@mail.com"));

        validateTree(contacts, 7);

        contacts.remove(new Contactos("Ana", "0", "placeholder@mail.com"));
        validateTree(contacts, 6);

        contacts.remove(new Contactos("Bruno", "0", "placeholder@mail.com"));
        validateTree(contacts, 5);

        contacts.remove(new Contactos("Carla", "0", "placeholder@mail.com"));
        validateTree(contacts, 4);

        validateInOrder(contacts, "Diego", "Elena", "Federico", "Gabriela");
        printInOrder(contacts);
    }

    private static void validateTree(AVL<Contactos> contacts, int expectedSize, int expectedHeight) {
        if (contacts.size() != expectedSize) {
            throw new RuntimeException(
                    "Error: se esperaba size " + expectedSize + " pero se obtuvo " + contacts.size()
            );
        }

        if (contacts.height() != expectedHeight) {
            throw new RuntimeException(
                    "Error: se esperaba altura " + expectedHeight + " pero se obtuvo " + contacts.height()
            );
        }

        if (!contacts.isBalanced()) {
            throw new RuntimeException("Error: el árbol no está balanceado.");
        }

        System.out.println("OK - Size: " + contacts.size());
        System.out.println("OK - Altura: " + contacts.height());
        System.out.println("OK - Balanceado: Sí");
    }

    private static void validateTree(AVL<Contactos> contacts, int expectedSize) {
        if (contacts.size() != expectedSize) {
            throw new RuntimeException(
                    "Error: se esperaba size " + expectedSize + " pero se obtuvo " + contacts.size()
            );
        }

        if (!contacts.isBalanced()) {
            throw new RuntimeException("Error: el árbol no está balanceado.");
        }

        System.out.println("OK - Size: " + contacts.size());
        System.out.println("OK - Altura actual: " + contacts.height());
        System.out.println("OK - Balanceado: Sí");
    }

    private static void validateInOrder(AVL<Contactos> contacts, String... expectedNames) {
        SimpleList<Contactos> orderedContacts = contacts.inOrder();

        if (orderedContacts.size() != expectedNames.length) {
            throw new RuntimeException(
                    "Error: se esperaban " + expectedNames.length +
                            " contactos en inOrder pero se obtuvieron " + orderedContacts.size()
            );
        }

        for (int i = 0; i < expectedNames.length; i++) {
            String currentName = orderedContacts.get(i).getName();

            if (!currentName.equals(expectedNames[i])) {
                throw new RuntimeException(
                        "Error en inOrder. Posición " + i +
                                ": se esperaba " + expectedNames[i] +
                                " pero se obtuvo " + currentName
                );
            }
        }

        System.out.println("OK - Recorrido inOrder correcto");
    }

    private static void printInOrder(AVL<Contactos> contacts) {
        System.out.println("Listado inOrder:");

        SimpleList<Contactos> orderedContacts = contacts.inOrder();

        for (int i = 0; i < orderedContacts.size(); i++) {
            System.out.println((i + 1) + ". " + orderedContacts.get(i));
        }
    }
}