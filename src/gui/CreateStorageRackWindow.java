package gui;

import application.controller.Controller;
import application.model.Warehouse;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CreateStorageRackWindow extends Stage {

    public CreateStorageRackWindow(String title, Warehouse warehouse) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.warehouse = warehouse;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene =  new Scene(pane);
        this.setScene(scene);
    }

    private Warehouse warehouse;
    private TextField txfId, txfRow, txfCol, txfSelectedWarehouse;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Textfields and labels where information about the StorageRack can be inserted

        Label lblSelectedWarehouse = new Label("Lager");
        pane.add(lblSelectedWarehouse, 1, 0);

        txfSelectedWarehouse = new TextField();
        pane.add(txfSelectedWarehouse, 2, 0);
        txfSelectedWarehouse.setText(warehouse.getName());
        txfSelectedWarehouse.setEditable(false);

        Label lblId = new Label("ID: ");
        pane.add(lblId, 1, 1);

        txfId = new TextField();
        pane.add(txfId, 2, 1);
        txfId.setEditable(true);

        Label lblRow = new Label("Antal hylder: ");
        pane.add(lblRow, 1, 2);

        txfRow = new TextField();
        pane.add(txfRow, 2, 2);
        txfRow.setEditable(true);

        Label lblCol = new Label("Antal pladser pr hylde: ");
        pane.add(lblCol, 1, 3);

        txfCol = new TextField();
        pane.add(txfCol, 2, 3);
        txfCol.setEditable(true);

        // Buttons where you can save or exit

        Button btnSave = new Button("Gem reol");
        pane.add(btnSave, 1, 4);
        btnSave.setOnAction(event -> this.saveAction());

        Button btnExit = new Button("Luk");
        pane.add(btnExit, 2, 4);
        btnExit.setOnAction(event -> this.exitAction());

    }
    private void saveAction() {

        String id = txfId.getText().trim();
        int row = Integer.parseInt(txfRow.getText().trim());
        int col = Integer.parseInt(txfCol.getText().trim());

        //Controller.createStorageRack - TODO
    }
    private void exitAction() {
        this.hide();
    }
}
