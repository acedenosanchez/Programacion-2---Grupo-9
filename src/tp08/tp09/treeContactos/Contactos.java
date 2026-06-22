package tp08.tp09.treeContactos;

public class Contactos implements Comparable<Contactos> {

    private String name;
    private String phoneNumber;
    private String email;

    public Contactos(String name, String phoneNumber, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("El número no puede estar vacío.");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El mail no puede estar vacío.");
        }

        this.name = name.trim();
        this.phoneNumber = phoneNumber.trim();
        this.email = email.trim();
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int compareTo(Contactos other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return "Nombre: " + name +
                " | Teléfono: " + phoneNumber +
                " | Mail: " + email;
    }
}