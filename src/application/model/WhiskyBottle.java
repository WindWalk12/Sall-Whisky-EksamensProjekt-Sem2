package application.model;

public class WhiskyBottle {
    private String id;
    private double volume = 70;
    private static int bottleCount = 0;

    public WhiskyBottle(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getVolume() {
        return volume;
    }

    public static int getBottleCount() {
        return bottleCount;
    }
}
