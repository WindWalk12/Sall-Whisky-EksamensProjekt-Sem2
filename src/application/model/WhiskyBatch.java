package application.model;

import java.time.LocalDate;
import java.util.*;

public class WhiskyBatch {
    private String id;
    private double waterVolume;
    private double totalVolume;
    private boolean caskStrength;
    private double alcPrercntage;
    private String whiskyType;
    private LocalDate tapped;
    private static int batchCounter = 0;
    ArrayList<Distilate> distilates = new ArrayList<>();
    ArrayList<WhiskyBottle> whiskyBottles = new ArrayList<>();

    public WhiskyBatch(double waterVolume, boolean caskStrength, double alcPercentage, String whiskyType, LocalDate tapped, Map<Cask, Double> casks) {
        batchCounter++;
        this.id = String.valueOf(batchCounter);
        this.waterVolume = waterVolume;
        addDistilates(casks);
        this.totalVolume = calcTotalVolume(casks);
        this.caskStrength = caskStrength;
        this.alcPrercntage = alcPercentage;
        this.whiskyType = whiskyType;
        this.tapped = tapped;
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
            distilates.addAll(map.getKey().getDistilates());
        }

    }

    public void createWhiskyBottles() {
        int numOfBottles = (int)Math.floor(this.totalVolume*100/70);
        for (int i = 0; i < numOfBottles; i++) {
            WhiskyBottle wb = new WhiskyBottle(this.id + "-" + i);
            whiskyBottles.add(wb);
        }
    }

    public String createHistoryString() {
        // Maltbacth info
        Set<Maltbatch> malthbatchInfoSet = new HashSet<>();
        for (Distilate d : distilates) {
            malthbatchInfoSet.add(d.getDistillation().getMaltBatch());
        }
        ArrayList<Maltbatch> maltbatchInfo = new ArrayList<>(malthbatchInfoSet);
        String field = maltbatchInfo.get(0).getCornField();
        String grainType = maltbatchInfo.get(0).getGrainType();
        String farmer = maltbatchInfo.get(0).getFarmer();
        String harvestet = String.valueOf(maltbatchInfo.get(0).getHarvestDate());
        String maltDate = String.valueOf(maltbatchInfo.get(0).getMaltDate());

        // Distillation info
        Set<Distillation> distillationInfoSet = new HashSet<>();
        for (Distilate d : distilates) {
            distillationInfoSet.add(d.getDistillation());
        }
        ArrayList<Distillation> distillationInfo = new ArrayList<>(distillationInfoSet);
        String distillationStartDate = String.valueOf(distillationInfo.get(0).getStartDate());
        String distillationEndDate = String.valueOf(distillationInfo.get(0).getStartDate());
        String distillationEmployee = distillationInfo.get(0).getEmployeeName();

        // Warehouse info
        Set<Warehouse> WarehouseInfoSet = new HashSet<>();
        for (Distilate d : distilates) {
            for (Cask c : d.getCasks()) {
                WarehouseInfoSet.add(c.getStorageRack().getWarehouse());
            }
        }
        ArrayList<Warehouse> warehouseInfo = new ArrayList<>(WarehouseInfoSet);
        String warehouseName = warehouseInfo.get(0).getName();

        // Cask info
        Set<Cask> caskInfoSet = new HashSet<>();
        for (Distilate d : distilates) {
            caskInfoSet.addAll(d.getCasks());
        }
        ArrayList<Cask> caskInfo = new ArrayList<>(caskInfoSet);

        // Distilate info
        String distilateFillingDate = String.valueOf(distilates.get(0).getFillingDate());

        return "Whiskyen her startede ude på den økologiske mark " + field + " som " + grainType + " Byg hos landmanden "
                + farmer + ".\nDer er blevet " + "høstet d. " + harvestet + " og maltet d. " + maltDate +
                ".\nHerefter er det blevet distilleret d. " + distillationStartDate + " til d. " + distillationEndDate +
                " af " + distillationEmployee + ".\nDen har så efterfølgene lagt på lageret: " + warehouseName + " fra d. " +
                distilateFillingDate + " til d. " + this.tapped + ".\nTil sidst har vi så med en " + this.whiskyType +
                " at gøre på " + this.alcPrercntage + "% alkohol.\nDer er lavet " + (int)Math.floor(this.totalVolume*100/70) + " flasker whisky.";
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
