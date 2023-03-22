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

    public void createStorageRack(int width, int height) {
        StorageRack sr = new StorageRack(name + "-" + storageRacks.size() + 1, width, height);
        storageRacks.add(sr);
        availableSpaces += width*height;
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
}
