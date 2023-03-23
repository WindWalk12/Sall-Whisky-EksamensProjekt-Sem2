package application.controller;

import application.model.Cask;
import application.model.CaskType;
import application.model.StorageRack;
import application.model.Warehouse;
import storage.Storage;

import java.util.ArrayList;

public class Controller {

    public static Warehouse createWarehouse(String name, String address){
        Warehouse warehouse = new Warehouse(name, address);
        Storage.addWarehouse(warehouse);
        return warehouse;
    }

    public static ArrayList<Warehouse> getWarehouses(){
        return Storage.getWarehouses();
    }

    public static Cask createCask(String countryOfOrigin, String supplier, double volume, CaskType caskType){
        if (volume < 1) {
            throw new IllegalArgumentException("Volume skal være 1 eller højere");
        } else {
            Cask cask = new Cask(countryOfOrigin, supplier, volume, caskType);
            Storage.addCask(cask);
            return cask;
        }
    }


    public static ArrayList<Cask> getCasks(){
        return Storage.getCasks();
    }

    public static void putCask(StorageRack storageRack, int row, int col, Cask cask){
        if (row < 1 || row > 100 || col < 1 || col > 100) {
            throw new IllegalArgumentException("Hylder og pladser skal være over 0 og mindre end 100");
        } else {
            storageRack.putCask(cask,row,col);
        }
    }

    public static void removeCask(StorageRack storageRack, int row, int col, Cask cask){
        if (row < 1 || row > 100 || col < 1 || col > 100) {
            throw new IllegalArgumentException("Hylder og pladser skal være over 0 og mindre end 100");
        } else {
            storageRack.removeCask(cask, row, col);
        }
    }

    public static StorageRack createStorageRack(Warehouse warehouse, int row, int col) {
        if (row < 1 || row > 100 || col < 1 || col > 100) {
            throw new IllegalArgumentException("Hylder og pladser skal være over 0 og mindre end 100");
        } else {
            return warehouse.createStorageRack(row, col);
        }
    }

    public static void initStorage(){
        Warehouse WH1 = Controller.createWarehouse("WH1","Morbærhaven 30");
        Warehouse WH2 = Controller.createWarehouse("WH2", "Mortensmindevej 21");

        Cask C1 = Controller.createCask("Rusland","Destilleri",10,CaskType.BOURBON);
        Cask C2 = Controller.createCask("Spanien","Destilleri 2",40,CaskType.SHERRY);
        Cask C3 = Controller.createCask("Portugal","Desilleri 609",15,CaskType.REDWINE);

        StorageRack SR1 = WH1.createStorageRack(3,3);
        StorageRack SR2 = WH2.createStorageRack(6,5);

        putCask(SR1,2,2,C1);
        removeCask(SR1,2,2,C1);
        putCask(SR1,2,2,C1);

    }
}
