package sia.tacocloud.entities.enums;

public enum Type {

    WRAP("WRAP", 1), PROTEIN("PROTEIN", 2), VEGGIES("VEGGIES", 3), CHEESE("CHEESE", 4), SAUCE("SAUCE", 5);
    private final String type;
    private final int id;

    Type(String type, int id) {
        this.type = type;
        this.id = id;
    }

    public String getTypeName() {
        return type;
    }

    public int getId() {
        return id;
    }
}
