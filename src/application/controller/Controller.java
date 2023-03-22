package application.controller;

import application.model.Cask;
import application.model.CaskType;
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
}
