package application.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class StorageRack {

    // Simple attributes

    private String id;
    private Warehouse warehouse;

    // Constructor

    public StorageRack(String id, int row, int col, Warehouse warehouse) {
        this.id = id;
        this.warehouse = warehouse;
        createShelfs(row, col);
    }

    // Link attribute 0..1 - > 0..* to Cask

    private Map<String, Cask> shelfs = new LinkedHashMap<>();

    public Map<String, Cask> getShelfs() {
        return new LinkedHashMap<>(shelfs);
    }

    public void putCask(Cask cask, int row, int col) {
        shelfs.put(row + "." + col, cask);
        cask.setStorageRack(this);
        warehouse.reduceSpace();
    }

    public void removeCask(Cask cask, int row, int col) {
        shelfs.put(row + "." + col, null);
        cask.setStorageRack(null);
        warehouse.increaseSpace();
    }

    // Simple methods

    public String getId() {
        return id;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }
    // Methods

    /**
     *
     * @param row < 100
     * @param col < 100
     */

    private void createShelfs(int row, int col) {
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                shelfs.put(i + "." + j, null);
            }
        }
    }

    @Override
    public String toString(){
        return this.id;
    }

}
