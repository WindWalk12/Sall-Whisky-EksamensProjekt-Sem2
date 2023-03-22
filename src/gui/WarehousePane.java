package gui;


import application.controller.Controller;
import application.model.Cask;
import application.model.Warehouse;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class WarehousePane extends GridPane {
    private ListView<Warehouse> lvwWarehouses;

    public WarehousePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblWarehouses = new Label("Lagre");
        this.add(lblWarehouses, 0, 0);

        lvwWarehouses = new ListView<>();
        this.add(lvwWarehouses, 0, 1, 1, 7);
        lvwWarehouses.setPrefWidth(200);
        lvwWarehouses.setPrefHeight(300);
        lvwWarehouses.getItems().setAll(Controller.getWarehouses());
    }

    // -------------------------------------------------------------------------

    private void selectedCaskChanged() {
        this.updateView();
    }

    public void updateList() {
        lvwWarehouses.getItems().setAll(Controller.getWarehouses());
    }

    private void updateView() {}
}
