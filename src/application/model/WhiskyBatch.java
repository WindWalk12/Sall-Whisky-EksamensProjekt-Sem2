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
    ArrayList<Distillate> distillates = new ArrayList<>();
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
            distillates.addAll(map.getKey().getDistilates());
        }

    }

    public void createWhiskyBottles() {
        int numOfBottles = (int)Math.floor(this.totalVolume*100/70);
        for (int i = 0; i < numOfBottles; i++) {
            WhiskyBottle wb = new WhiskyBottle("" + (i + 1));
            whiskyBottles.add(wb);
        }
    }

    public String createHistoryString() {
        // Maltbacth info
        Set<Maltbatch> malthbatchInfoSet = new HashSet<>();
        for (Distillate d : distillates) {
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
        for (Distillate d : distillates) {
            distillationInfoSet.add(d.getDistillation());
        }
        ArrayList<Distillation> distillationInfo = new ArrayList<>(distillationInfoSet);
        String distillationStartDate = String.valueOf(distillationInfo.get(0).getStartDate());
        String distillationEndDate = String.valueOf(distillationInfo.get(0).getStartDate());
        String distillationEmployee = distillationInfo.get(0).getEmployeeName();

        // Warehouse info
        Set<Warehouse> WarehouseInfoSet = new HashSet<>();
        for (Distillate d : distillates) {
            for (Cask c : d.getCasks()) {
                WarehouseInfoSet.add(c.getStorageRack().getWarehouse());
            }
        }
        ArrayList<Warehouse> warehouseInfo = new ArrayList<>(WarehouseInfoSet);
        String warehouseName = warehouseInfo.get(0).getName();

        // Cask info
        Set<Cask> caskInfoSet = new HashSet<>();
        for (Distillate d : distillates) {
            caskInfoSet.addAll(d.getCasks());
        }
        ArrayList<Cask> caskInfo = new ArrayList<>(caskInfoSet);
        String caskType = caskInfo.get(0).getCaskType();

        // Distilate info
        String distilateFillingDate = String.valueOf(distillates.get(0).getFillingDate());

        return "Whiskyen her startede som byg af sorten " + grainType + ", på den økologiske mark " + field + " hos landmanden "
                + farmer + ".\nByggen blev høstet d. " + harvestet + " og derefter maltet d. " + maltDate +
                ".\nHerefter blev det destilleret med vand, fra en begravet dal under Sall,  d. " + distillationStartDate + " til d. " + distillationEndDate +
                " af " + distillationEmployee + ".\nEfterfølgende har destillatet lagret i et fad af " + caskType + ", på lageret: " + warehouseName + " fra d. " +
                distilateFillingDate + " til d. " + this.tapped + ".\nVi har at gøre med en " + this.whiskyType +
                " på " + this.alcPrercntage + "% alkohol.\nWhiskyen er blevet hældt på " + (int)Math.floor(this.totalVolume*100/70) + " flasker.";
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

    public ArrayList<WhiskyBottle> getWhiskyBottles() {
        return new ArrayList<>(whiskyBottles);
    }

}
