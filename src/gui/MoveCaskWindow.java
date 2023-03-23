package gui;

import application.controller.Controller;
import application.model.Cask;
import application.model.StorageRack;
import application.model.Warehouse;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import storage.Storage;

import java.util.*;
import java.util.Map;

public class MoveCaskWindow extends Stage {

    public MoveCaskWindow(String title, Cask cask) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.cask = cask;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene =  new Scene(pane);
        this.setScene(scene);
    }

    private Cask cask;

    private ComboBox<Warehouse> cbxWarehouse;

    private ComboBox<StorageRack> cbxStorageRacks;

    private ListView<String> lvwShelfs;
    private Label lblError = new Label();


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Textfields, labels and combobox where information about the cask and warehouse is

        Label lblWarehouse = new Label("Lager: ");
        pane.add(lblWarehouse, 1, 0);

        cbxWarehouse = new ComboBox<>();
        pane.add(cbxWarehouse, 2, 0);
        cbxWarehouse.getItems().setAll(Storage.getWarehouses());

        ChangeListener<Warehouse> listener = (ov, oldKonference, newKonference) -> this.selectedWarehouseChanged();
        cbxWarehouse.getSelectionModel().selectedItemProperty().addListener(listener);

        Label lblStorageRacks = new Label("Reol: ");
        pane.add(lblStorageRacks, 1, 1);

        cbxStorageRacks = new ComboBox<>();
        pane.add(cbxStorageRacks, 2, 1);

        ChangeListener<StorageRack> listener2 = (ov, oldKonference, newKonference) -> this.selectedStorageRackChanged();
        cbxStorageRacks.getSelectionModel().selectedItemProperty().addListener(listener2);


        Label lblShelfs = new Label("Hylder og pladser");
        pane.add(lblShelfs, 1, 2);

        lvwShelfs  = new ListView<>();
        pane.add(lvwShelfs, 2, 2);


        // Buttons which makes it possible to save or exit

        Button btnMove = new Button("Flyt til lager");
        pane.add(btnMove, 1, 4);
        btnMove.setOnAction(event -> this.moveAction());

        Button btnExit = new Button("Luk");
        pane.add(btnExit, 2, 4);
        btnExit.setOnAction(event -> this.exitAction());

        lblError.setTextFill(Color.RED);
        pane.add(lblError, 1, 5, 2, 1);
    }

    private void moveAction() {
        Warehouse warehouse = cbxWarehouse.getSelectionModel().getSelectedItem();
        StorageRack storageRack = cbxStorageRacks.getSelectionModel().getSelectedItem();
        String shelf = lvwShelfs.getSelectionModel().getSelectedItem();
        if (warehouse != null && storageRack != null && shelf != null) {
            String[] splitShelf = shelf.split(" ");
            if (splitShelf[1].equals("Ledig")) {
                if (cask.getStorageRack() != null) {
                    String key = "";
                    for (Map.Entry<String, Cask> map :cask.getStorageRack().getShelfs().entrySet()) {
                        if (Objects.equals(cask, map.getValue())) {
                            key = map.getKey();
                        }
                    }
                    String row = key.split("\\.")[0];
                    String col = key.split("\\.")[1];
                    Controller.removeCask(cask.getStorageRack(), Integer.parseInt(row), Integer.parseInt(col), cask);
                }
                String[] key = splitShelf[0].split("\\.");
                String row = key[0];
                String col = key[1];
                Controller.putCask(storageRack, Integer.parseInt(row), Integer.parseInt(col), cask);
                this.hide();
            } else {
                lblError.setText("Den valgte hylde er ikke ledig");
            }
        } else {
            lblError.setText("Der er ikke valgt noget lager, reol eller hylde");
        }
    }

    private void exitAction() {
        this.hide();
    }

    private void selectedWarehouseChanged() {
        Warehouse warehouse = cbxWarehouse.getSelectionModel().getSelectedItem();
        if (warehouse != null) {
            cbxStorageRacks.getItems().setAll(warehouse.getStorageRacks());
        } else {
            cbxStorageRacks.getItems().clear();
        }
    }

    private void selectedStorageRackChanged() {
        StorageRack storageRack = cbxStorageRacks.getSelectionModel().getSelectedItem();
        if (storageRack != null) {
            ArrayList<String> listOfKeys = new ArrayList<>(storageRack.getShelfs().keySet());
            ArrayList<Cask> listOfValues = new ArrayList<>(storageRack.getShelfs().values());
            ArrayList<String> shelfs = new ArrayList<>();
            for (int i = 0; i < listOfKeys.size(); i++) {
                if (listOfValues.get(i) == null) {
                    shelfs.add(listOfKeys.get(i) + " Ledig");
                } else {
                    shelfs.add(listOfKeys.get(i) + " " + listOfValues.get(i));
                }
            }
            lvwShelfs.getItems().setAll(shelfs);
        } else {
            lvwShelfs.getItems().clear();
        }
    }
}
