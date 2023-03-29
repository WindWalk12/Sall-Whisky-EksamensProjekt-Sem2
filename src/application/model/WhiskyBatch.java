package application.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class WhiskyBatch {
    private String id;
    private double waterVolume;
    private double totalVolume;
    private boolean caskStrength;
    private double alcPrercntage;
    private String whiskyType;
    private static int batchCounter = 0;
    ArrayList<Distilate> distilates = new ArrayList<>();
    ArrayList<WhiskyBottle> whiskyBottles = new ArrayList<>();

    public WhiskyBatch(double waterVolume, boolean caskStrength, double alcPercentage, String whiskyType, Map<Cask, Double> casks) {
        batchCounter++;
        this.id = String.valueOf(batchCounter);
        this.waterVolume = waterVolume;
        addDistilates(casks);
        this.totalVolume = calcTotalVolume(casks);
        this.caskStrength = caskStrength;
        this.alcPrercntage = alcPercentage;
        this.whiskyType = whiskyType;
    }

    private double calcTotalVolume(Map<Cask, Double> casks) {
        double res = 0.0;
        for (Double d : casks.values()) {
            res += d;
        }
        return res + waterVolume;
    }

    private void addDistilates(Map<Cask, Double> casks) {
        for (Map.Entry<Cask, Double> map : casks.entrySet()) {
            Collections.addAll(map.getKey().getDistilates());
        }

    }

    public void createWhiskyBottles() {
        int numOfBottles = (int)Math.floor(this.totalVolume*100/70);
        for (int i = 0; i < numOfBottles; i++) {
            WhiskyBottle wb = new WhiskyBottle(this.id + "-" + i);
            whiskyBottles.add(wb);
        }
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
