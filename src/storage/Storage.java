package storage;

import application.model.Cask;
import application.model.Warehouse;

import java.util.ArrayList;

public class Storage {

    private static ArrayList<Warehouse> warehouses = new ArrayList<>();

    private static ArrayList<Cask> casks = new ArrayList<>();

    //--------------------------------------------------------------------------------
    // Warehouse
    public static ArrayList<Warehouse> getWarehouses() {
        return new ArrayList<>(warehouses);
    }

    public static void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
    }

    public static void removeWarehouse(Warehouse warehouse) {
        warehouses.remove(warehouse);
    }

    //--------------------------------------------------------------------------------
    // Cask

    public static ArrayList<Cask> getCasks() {
        return new ArrayList<>(casks);
    }

    public static void addCask(Cask cask) {
        casks.add(cask);
    }

    public static void removeCask(Cask cask) {
        casks.remove(cask);
    }
}