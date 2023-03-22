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

public class CreateWarehouseWindow extends Stage {

    public CreateWarehouseWindow(String title) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene =  new Scene(pane);
        this.setScene(scene);
    }

    private TextField txfName, txfAddress;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Textfields and labels where information about the new warehuse can be inserted

        Label lblName = new Label("Navn på lager: ");
        pane.add(lblName,  1, 0);

        txfName = new TextField();
        pane.add(txfName, 2, 0);
        txfName.setEditable(true);

        Label lblAddress = new Label("Adresse: ");
        pane.add(lblAddress, 1, 1);

        txfAddress = new TextField();
        pane.add(txfAddress, 2, 1);
        txfAddress.setEditable(true);

        // Buttons which makes it possible to save or exit

        Button btnSave = new Button("Gem lager");
        pane.add(btnSave, 1, 2);
        btnSave.setOnAction(event -> this.saveAction());

        Button btnExit = new Button("Luk");
        pane.add(btnExit, 2, 2);
        btnExit.setOnAction(event -> this.exitAction());
    }

    private void saveAction() {

        String name = txfName.getText().trim();
        String address = txfAddress.getText().trim();

        Controller.createWarehouse(name, address);
        this.hide();
    }
    private void exitAction() {
        this.hide();
    }
}
