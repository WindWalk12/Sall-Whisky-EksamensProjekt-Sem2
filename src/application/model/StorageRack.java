package application.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StorageRack {

    // Simple attributes

    private String id;


    // Constructor

    public StorageRack(String id, int row, int col) {
        this.id = id;
        createShelfs(row, col);
    }

    // Link attribute 0..1 - > 0..* to Cask

    private Map<String, Cask> shelfs = new HashMap<>();

    public Map<String, Cask> getShelfs() {
        return new HashMap<>(shelfs);
    }

    public void putCask(Cask cask, int row, int col) {
        shelfs.put(row + "." + col, cask);
    }

    public void removeCask(int row, int col) {
        shelfs.put(row + "." + col, null);
    }

    // Methods

    public String getId() {
        return id;
    }


    private void createShelfs(int row, int col) {
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                shelfs.put(i + "." + j, null);            }
        }
    }

}
