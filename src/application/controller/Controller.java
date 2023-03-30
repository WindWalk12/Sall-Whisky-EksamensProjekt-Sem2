package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        if (row < 1 || col < 1) {
            throw new IllegalArgumentException("Hylder og pladser skal være over 0 og mindre end 100");
        } else {
            storageRack.putCask(cask,row,col);
        }
    }

    public static void removeCask(StorageRack storageRack, int row, int col, Cask cask){
        if (row < 1 || col < 1) {
            throw new IllegalArgumentException("Hylder og pladser skal være over 0 og mindre end 100");
        } else {
            storageRack.removeCask(cask, row, col);
        }
    }

    public static ArrayList<Cask> getEmptyCasks() {
        ArrayList<Cask> emptyCasks = new ArrayList<>();
        for (Cask c :Storage.getCasks()) {
            if (c.getContentVolume() < c.getVolume()) {
                emptyCasks.add(c);
            }
        }
        return emptyCasks;
    }

    public static Distilate fillCask(LocalDate fillingDate, Distillation distillation, Cask cask, double volume) {
        if (distillation.getVolumen() - volume < 0) {
            throw new RuntimeException("Der er ikke nok distilation til at fylde fadet op");
        } else if (volume + cask.getContentVolume() > cask.getVolume()) {
            throw new IllegalArgumentException("Der kan kun fyldes " + (cask.getVolume() - cask.getContentVolume()) + "L på fadet");
        } else {
            return distillation.fillCask(fillingDate, distillation, cask, volume);
        }
    }

    public static StorageRack createStorageRack(Warehouse warehouse, int row, int col) {
        if (row < 1 || col < 1) {
            throw new IllegalArgumentException("Hylder og pladser skal være over 0 og mindre end 100");
        } else {
            return warehouse.createStorageRack(row, col);
        }
    }

    public static Distillation createDistillation(double volumen, double alcPercentage, LocalDate startDate, LocalDate endDate, String employeeName, String smokingMaterial, String comment, Maltbatch maltBatch, String spiritBatchNr){
        if (volumen < 1) {
            throw new IllegalArgumentException("Volumen skal være 1 eller mere");
        } else if (alcPercentage < 0 || alcPercentage > 100) {
            throw new IllegalArgumentException("Alkohol procenten kan ikke være under 0 eller over 100");
        } else {
            Distillation distillation = new Distillation(volumen, alcPercentage, startDate, endDate, employeeName, smokingMaterial, comment, maltBatch, spiritBatchNr);
            Storage.addDistillation(distillation);
            return distillation;
        }
    }

    public static ArrayList<Distillation> getDistillations(){
        return Storage.getDistillations();
    }

    public static Maltbatch createMaltbatch(String cornField, String grainType, LocalDate harvestDate, String farmer, LocalDate maltDate){
        Maltbatch maltbatch = new Maltbatch(cornField,grainType,harvestDate,farmer,maltDate);
        Storage.addMaltbatch(maltbatch);
        return maltbatch;
    }

    public static WhiskyBatch createWhiskyBatch(double waterVolume, boolean caskStrength, double alcPrercntage, String whiskyType, LocalDate tapped, Map<Cask, Double> casks) {
        WhiskyBatch whiskyBatch = new WhiskyBatch(waterVolume, caskStrength, alcPrercntage, whiskyType, tapped, casks);
        Storage.addWhiskyBatch(whiskyBatch);
        for (Map.Entry<Cask, Double> map : casks.entrySet()) {
            map.getKey().removeFromContentVolume(map.getValue());
        }

        return whiskyBatch;
    }

    public static void createWhiskyBottles(WhiskyBatch whiskyBatch) {
        whiskyBatch.createWhiskyBottles();
    }

    public static ArrayList<Maltbatch> getMaltbatches(){
        return Storage.getMaltbatches();
    }

    public static ArrayList<WhiskyBatch> getWhiskyBatch() {
        return Storage.getWhiskyBatches();
    }

    public static String createHistoryString(WhiskyBatch whiskyBatch) {
        return whiskyBatch.createHistoryString();
    }


    public static void initStorage(){
        Warehouse WH1 = Controller.createWarehouse("Stalden hos Lars","Morbærhaven 30");
        Warehouse WH2 = Controller.createWarehouse("Containeren", "Mortensmindevej 21");

        Cask C1 = Controller.createCask("Rusland","Destilleri",70,CaskType.BOURBON);
        Cask C2 = Controller.createCask("Spanien","Destilleri 2",40,CaskType.SHERRY);
        Cask C3 = Controller.createCask("Portugal","Desilleri 609",15,CaskType.REDWINE);

        StorageRack SR1 = WH1.createStorageRack(3,3);
        StorageRack SR2 = WH2.createStorageRack(6,5);

        putCask(SR1,2,2,C1);
        removeCask(SR1,2,2,C1);
        putCask(SR1,2,2,C1);

        Maltbatch mb1 = Controller.createMaltbatch("Mosevang", "Evergreen", LocalDate.now(), "Lars", LocalDate.now().plusDays(5));

        Distillation dt1 = Controller.createDistillation(1000.5, 86.6, LocalDate.now(), LocalDate.now().plusDays(5), "Jørgen", "Ingen", "Smager allerede godt", mb1, "NM77P");

        Controller.fillCask(LocalDate.now(), dt1, C1, 10.0);
        Controller.fillCask(LocalDate.now(), dt1, C1, 40.0);

        Map<Cask, Double> casks1 = new HashMap<>();
        casks1.put(C1, 30.0);

        Map<Cask, Double> casks2 = new HashMap<>();


        WhiskyBatch wb1 = Controller.createWhiskyBatch(10, false, 78, "Single cask", LocalDate.now(), casks1);
        WhiskyBatch wb2 = Controller.createWhiskyBatch(0, true, 65, "Single malt", LocalDate.now(), casks2);

        System.out.println(wb1.createHistoryString());

    }
}
