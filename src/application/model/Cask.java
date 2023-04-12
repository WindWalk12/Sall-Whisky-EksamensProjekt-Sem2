package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cask {
    private String id;
    private CaskType caskType;
    private String countryOfOrigin;
    private String supplier;
    private double volume;
    private StorageRack storageRack;
    private static int caskCounter = 0;
    private ArrayList<Distillate> distillates = new ArrayList<>();
    private String tbvWarehouseName = "Ikke på lager";
    private String tbvStorageRackId = "Ikke på hylde";

    public Cask(String countryOfOrigin, String supplier, double volume, CaskType caskType) {
        caskCounter ++;
        this.id = String.valueOf(caskCounter);
        this.countryOfOrigin = countryOfOrigin;
        this.supplier = supplier;
        this.volume = volume;
        this.caskType = caskType;
    }

    public String getId(){
        return this.id;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public StorageRack getStorageRack() {
        return storageRack;
    }

    public String getTbvWarehouseName() {
        return tbvWarehouseName;
    }

    public String getTbvStorageRackId() {
        return tbvStorageRackId;
    }

    public String getCaskType() {
        return caskType.toString().charAt(0) + caskType.toString().substring(1).toLowerCase();
    }

    public double getContentVolume() {
        double currentVolume = 0.0;
        for (Distillate d : distillates) {
            currentVolume += d.getVolume();
        }
        return currentVolume;
    }

    public void removeFromContentVolume(double amount) {
    double toBeRemoved = amount;
        for (Distillate d : distillates) {
            if (toBeRemoved > 0) {
                toBeRemoved -= d.removeFromVolume(toBeRemoved);
            } else {
                break;
            }
        }
    }

    public LocalDate getNewestDate() {
        LocalDate newestDate = LocalDate.MAX;
        if (distillates.size() > 0) {
            newestDate = LocalDate.MIN;
            for (Distillate d : distillates) {
                if (newestDate.isBefore(d.getFillingDate())) {
                    newestDate = d.getFillingDate();
                }
            }
        }
        return newestDate;
    }

    public void setStorageRack(StorageRack storageRack) {
        this.storageRack = storageRack;
        if (storageRack != null) {
            this.tbvWarehouseName = storageRack.getWarehouse().getName();
            this.tbvStorageRackId = "Reol-" + storageRack.getId() + " " + storageRack.getKeyFromValue(this);
        }
    }

    public ArrayList<Distillate> getDistilates() {
        return new ArrayList<>(distillates);
    }

    public void addDistilate(Distillate distillate) {
        if (!distillates.contains(distillate)) {
            distillates.add(distillate);
        }
    }

    @Override
    public String toString(){
        return this.id + ", " + this.caskType.toString() + ", " + this.tbvWarehouseName + ", " + this.tbvStorageRackId + ", " + this.volume + "L";
    }
}
