package gui;


import application.controller.Controller;
import application.model.StorageRack;
import application.model.Warehouse;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class WarehousePane extends GridPane {
    private ListView<Warehouse> lvwWarehouses;
    private ListView<StorageRack> lvwStorageRacks;

    public WarehousePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblWarehouses = new Label("Lagre");
        this.add(lblWarehouses, 0, 0);

        lvwWarehouses = new ListView<>();
        this.add(lvwWarehouses, 0, 1, 1, 7);
        lvwWarehouses.setPrefWidth(250);
        lvwWarehouses.setPrefHeight(300);
        lvwWarehouses.getItems().setAll(Controller.getWarehouses());
        ChangeListener<Warehouse> listener = (ov, oldKonference, newKonference) -> this.selectedWarehouseChanged();
        lvwWarehouses.getSelectionModel().selectedItemProperty().addListener(listener);

        lvwStorageRacks = new ListView<>();
        this.add(lvwStorageRacks, 1, 1, 1, 7);
        lvwStorageRacks.setPrefWidth(200);
        lvwStorageRacks.setPrefHeight(300);


        // Buttons

        Button btnCreateWarehouse = new Button("Opret nyt lager");
        this.add(btnCreateWarehouse, 2, 1);
        btnCreateWarehouse.setOnAction(event -> this.createWarehouseAction());

        Button btnCreateStorageRack = new Button("Opret ny reol");
        this.add(btnCreateStorageRack, 2, 2);
        btnCreateStorageRack.setOnAction(event -> this.createStorageRackAction());
    }

    // -------------------------------------------------------------------------

    private void selectedWarehouseChanged() {
        Warehouse warehouse = lvwWarehouses.getSelectionModel().getSelectedItem();
        if (warehouse != null) {
            lvwStorageRacks.getItems().setAll(warehouse.getStorageRacks());
        } else {
            lvwStorageRacks.getItems().clear();
        }
    }

    public void updateList() {
        lvwWarehouses.getItems().setAll(Controller.getWarehouses());
    }

    private void createWarehouseAction() {

    }

    private void createStorageRackAction() {

        CreateStorageRackWindow dia = new CreateStorageRackWindow("Opret reol");
        dia.showAndWait();
        updateList();

    }
}
