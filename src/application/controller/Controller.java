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

    public static Cask createCask(String id, String countryOfOrigin, String supplier, double volume, CaskType caskType){
        Cask cask = new Cask(id, countryOfOrigin,supplier,volume,caskType);
        Storage.addCask(cask);
        return cask;
    }

    public static ArrayList<Cask> getCasks(){
        return Storage.getCasks();
    }

    public static void putCask(StorageRack storageRack, int row, int col, Cask cask){
        storageRack.putCask(cask,row,col);
    }

    public static void initStorage(){
        Warehouse WH1 = Controller.createWarehouse("WH1","Morbærhaven 30");
        Warehouse WH2 = Controller.createWarehouse("WH2", "Mortensmindevej 21");

        Cask C1 = Controller.createCask("01","Rusland","Destilleri",10,CaskType.BOURBON);
        Cask C2 = Controller.createCask("02","Spanien","Destilleri 2",40,CaskType.SHERRY);
        Cask C3 = Controller.createCask("03", "Portugal","Desilleri 609",15,CaskType.REDWINE);

        StorageRack SR1 = WH1.createStorageRack(3,3);
        StorageRack SR2 = WH2.createStorageRack(6,5);

        putCask(SR1,2,2,C1);

        System.out.println(SR1.getShelfs().containsValue(C1));

    }
}
