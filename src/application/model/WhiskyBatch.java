package application.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WhiskyBatch {
    private String id;
    private double waterVolume;
    private double totalVolume;
    private boolean caskStrength;
    private double alcPrercntage;
    private String whiskyType;
    private static int batchCounter = 0;
    Map<Distilate, Double> distilates = new HashMap<>();
    ArrayList<WhiskyBottle> whiskyBottles = new ArrayList<>();

    public WhiskyBatch(double waterVolume, boolean caskStrength, double alcPrercntage, String whiskyType, Map<Cask, Double> casks) {
        batchCounter++;
        this.id = String.valueOf(batchCounter);
        this.waterVolume = waterVolume;
        addDistilates(casks);
        this.totalVolume = calcTotalVolume();
        this.caskStrength = caskStrength;
        this.alcPrercntage = alcPrercntage;
        this.whiskyType = whiskyType;
    }

    private double calcTotalVolume() {
        double res = 0.0;
        for (Map.Entry<Distilate, Double> map : distilates.entrySet()) {
            res += map.getValue();
        }
        return res + waterVolume;
    }

    private void addDistilates(Map<Cask, Double> casks) {
        for (Map.Entry<Cask, Double> map : casks.entrySet()) {
            for (Distilate d : map.getKey().getDistilates()) {
                distilates.put(d, map.getValue());
            }
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
