package application.model;

public class Cask {
    private String id;
    private CaskType caskType;
    private String countryOfOrigin;
    private String supplier;
    private double volume;
    private StorageRack storageRack;
    private static int caskCounter = 0;

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

    public void setStorageRack(StorageRack storageRack) {
        this.storageRack = storageRack;
    }

    @Override
    public String toString(){
        if(this.storageRack != null)
            return this.id + " " + this.caskType.toString() + " " + this.storageRack.getWarehouse().getName() + " " + this.storageRack + " " + this.volume;
        else
            return this.id + " " + this.caskType.toString() + " " + null + " " + this.storageRack + " " + this.volume;

    }
}
