package application.model;

public class WhiskyBottle {
    private String id;
    private double volume = 70;
    private static int bottleCount = 0;

    public WhiskyBottle(String id) {
        this.id = id;
    }
}
