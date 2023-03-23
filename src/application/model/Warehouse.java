package application.model;

import java.util.ArrayList;

public class Warehouse {
    private final String name;
    private final String address;
    ArrayList<StorageRack> storageRacks = new ArrayList<>();
    private int availableSpaces;

    public Warehouse(String name, String address) {
        this.name = name;
        this.address = address;
        this.availableSpaces = 0;
    }

    public StorageRack createStorageRack(int row, int col) {
        StorageRack sr = new StorageRack(name + "-" + (storageRacks.size() + 1), row, col, this);
        storageRacks.add(sr);
        availableSpaces += row*col;
        return sr;
    }

    public void removeStorageRack(StorageRack s) {
        storageRacks.remove(s);
    }

    public void reduceSpace() {
        availableSpaces--;
    }

    public void increaseSpace() {
        availableSpaces++;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<StorageRack> getStorageRacks() {
        return new ArrayList<>(storageRacks);
    }

    @Override
    public String toString(){
        return this.name + " " + this.address + " Ledige pladser: " + this.availableSpaces;
    }
}
