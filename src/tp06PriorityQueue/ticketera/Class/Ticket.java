package tp06PriorityQueue.ticketera.Class;

public class Ticket {

    private String title;
    private String description;
    private UrgencyLevel urgency;
    private int number;
    private String state;

    public enum UrgencyLevel {
        CRITICO("Crítico"),
        ALTO("Alto"),
        MEDIO("Medio"),
        BAJO("Bajo");

        private final String displayName;

        //Constructor
        UrgencyLevel(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public static UrgencyLevel fromOption(String option) {
            if (option == null) {
                throw new IllegalArgumentException("La opción de urgencia no puede ser nula.");
            }

            //setter
            return switch (option.trim()) {
                case "1" -> CRITICO;
                case "2" -> ALTO;
                case "3" -> MEDIO;
                case "4" -> BAJO;
                default -> throw new IllegalArgumentException("Opción de urgencia inválida: " + option);
            };
        }
    }

    //Constructor de ticket
    public Ticket(String title, String description, UrgencyLevel urgency, int number, String state) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
        if (urgency == null) {
            throw new IllegalArgumentException("La urgencia no puede estar vacía.");
        }
        if (number <= 0) {
            throw new IllegalArgumentException("El número de ticket debe ser positivo.");
        }

        this.title = title;
        this.description = description;
        this.urgency = urgency;
        this.number = number;
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    // Se mantiene este getter para no romper código que ya usaba el nombre anterior.
    public String getTittle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public UrgencyLevel getUrgency() {
        return urgency;
    }

    public int getNumber() {
        return number;
    }


    public String getState() {
        return state;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        this.title = title;
    }


    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
        this.description = description;
    }

    public void setUrgency(UrgencyLevel urgency) {
        if (urgency == null) {
            throw new IllegalArgumentException("La urgencia no puede estar vacía.");
        }
        this.urgency = urgency;
    }

    public void setNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("El número de ticket debe ser positivo.");
        }
        this.number = number;
    }


    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Ticket #" + number
                + " | Título: " + title
                + " | Urgencia: " + urgency.getDisplayName()
                + " | Estado: " + state
                + "\nDescripción: " + description;
    }
}
