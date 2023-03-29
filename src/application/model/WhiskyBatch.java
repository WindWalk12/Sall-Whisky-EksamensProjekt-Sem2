package application.model;

import java.util.ArrayList;

public class WhiskyBatch {
    private String id;
    private double waterVolume;
    private double totalVolume;
    private boolean caskStrength;
    private double alcPrercntage;
    private String whiskyType;
    private static int batchCounter = 0;
    ArrayList<Distilate> distilates = new ArrayList<>();

    public WhiskyBatch(double waterVolume, boolean caskStrength, double alcPrercntage, String whiskyType) {
        batchCounter++;
        this.id = String.valueOf(batchCounter);
        this.waterVolume = waterVolume;
        this.totalVolume = calcTotalVolume();
        this.caskStrength = caskStrength;
        this.alcPrercntage = alcPrercntage;
        this.whiskyType = whiskyType;
    }

    private double calcTotalVolume() {
        double res = 0.0;
        for (Distilate d :distilates) {
            res += d.getVolume();
        }
        return res + waterVolume;
    }

    public String getId() {
        return id;
    }

    public double getWaterVolume() {
        return waterVolume;
    }

    public double getTotalVolume() {
        return totalVolume;
    }

    public boolean isCaskStrength() {
        return caskStrength;
    }

    public double getAlcPrercntage() {
        return alcPrercntage;
    }

    public String getWhiskyType() {
        return whiskyType;
    }
}
