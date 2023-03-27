package gui;


import application.controller.Controller;
import application.model.Cask;
import application.model.StorageRack;
import application.model.Warehouse;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class WarehousePane extends GridPane {
    private ListView<Warehouse> lvwWarehouses;
    private final TableView<Warehouse> table = new TableView<>();
    private ListView<StorageRack> lvwStorageRacks;

    public WarehousePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblWarehouses = new Label("Lagre");
        this.add(lblWarehouses, 0, 0);

        TableColumn<Warehouse, String> name = new TableColumn<>("Navn");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn <Warehouse, String> address = new TableColumn<>("Adresse");
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        TableColumn<Warehouse, Integer> availableSpaces = new TableColumn<>("Ledige pladser");
        availableSpaces.setCellValueFactory(new PropertyValueFactory<>("availableSpaces"));
        table.getColumns().addAll(name, address, availableSpaces);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.add(table, 0, 1, 1, 7);
        ChangeListener<Warehouse> listener = (ov, oldWarehouse, newWarehouse) -> this.selectedWarehouseChanged();
        table.getSelectionModel().selectedItemProperty().addListener(listener);

        Label lblStorageRack = new Label("Reoler");
        this.add(lblStorageRack, 1, 0);

        lvwStorageRacks = new ListView<>();
        this.add(lvwStorageRacks, 1, 1, 1, 7);
        lvwStorageRacks.setPrefWidth(200);
        lvwStorageRacks.setPrefHeight(300);


        // Buttons

        Button btnCreateWarehouse = new Button("Opret nyt lager");
        this.add(btnCreateWarehouse, 2, 1);
        btnCreateWarehouse.setOnAction(event -> this.createWarehouseAction());
        btnCreateWarehouse.setMaxWidth(Double.MAX_VALUE);

        Button btnCreateStorageRack = new Button("Opret ny reol");
        this.add(btnCreateStorageRack, 2, 2);
        btnCreateStorageRack.setOnAction(event -> this.createStorageRackAction());
        btnCreateStorageRack.disableProperty().bind(Bindings.isNull(table.getSelectionModel().selectedItemProperty()));
        btnCreateStorageRack.setMaxWidth(Double.MAX_VALUE);

    }

    // -------------------------------------------------------------------------

    private void selectedWarehouseChanged() {
        Warehouse warehouse = table.getSelectionModel().getSelectedItem();
        if (warehouse != null) {
            lvwStorageRacks.getItems().setAll(warehouse.getStorageRacks());
        } else {
            lvwStorageRacks.getItems().clear();
        }
    }

    public void updateList() {
        table.getItems().setAll(Controller.getWarehouses());
    }

    // -------------------------------------------------------------------------


    private void createWarehouseAction() {
        CreateWarehouseWindow dia = new CreateWarehouseWindow("Opret nyt lager");
        dia.showAndWait();
        updateList();

    }

    private void createStorageRackAction() {
        CreateStorageRackWindow dia = new CreateStorageRackWindow("Opret reol", this.table.getSelectionModel().getSelectedItem());
        dia.showAndWait();
        updateList();

    }
}
