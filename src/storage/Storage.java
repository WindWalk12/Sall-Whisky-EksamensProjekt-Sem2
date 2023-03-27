package storage;

import application.model.Cask;
import application.model.Distillation;
import application.model.Maltbatch;
import application.model.Warehouse;

import java.util.ArrayList;

public class Storage {

    private static ArrayList<Warehouse> warehouses = new ArrayList<>();

    private static ArrayList<Cask> casks = new ArrayList<>();

    private static ArrayList<Maltbatch> maltbatches = new ArrayList<>();

    private static ArrayList<Distillation> distillations = new ArrayList<>();

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

    //--------------------------------------------------------------------------------
    // Maltbatches
    public static ArrayList<Maltbatch> getMaltbatches() {
        return new ArrayList<>(maltbatches);
    }

    public static void addMaltbatch(Maltbatch maltbatch) {
        maltbatches.add(maltbatch);
    }

    public static void removeMaltbatch(Maltbatch maltbatch) {
        maltbatches.remove(maltbatch);
    }

    //--------------------------------------------------------------------------------
    // distillations
    public static ArrayList<Distillation> getDistillations() {
        return new ArrayList<>(distillations);
    }

    public static void addDistillation(Distillation distillation) {
        distillations.add(distillation);
    }

    public static void removeDistillation(Distillation distillation) {
        distillations.remove(distillation);
    }
}