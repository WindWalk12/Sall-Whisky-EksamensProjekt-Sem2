package application.model;

public class Cask {
    private String id;
    //TODO CAST TYPE
    private String countryOfOrigin;
    private String supplier;
    private double volume;
    private StorageRack storageRack;

    public Cask(String id, String countryOfOrigin, String supplier, double volume) {
        this.id = id;
        this.countryOfOrigin = countryOfOrigin;
        this.supplier = supplier;
        this.volume = volume;
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

    public void setStorageRack(StorageRack storageRack) {
        this.storageRack = storageRack;
    }
}
