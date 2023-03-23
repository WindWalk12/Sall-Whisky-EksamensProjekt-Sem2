package gui;

import application.model.Cask;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MoveCaskWindow extends Stage {

    public MoveCaskWindow(String title, Cask cask) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene =  new Scene(pane);
        this.setScene(scene);
    }

    private TextField txf;

    private ComboBox cbxWarehouse, cbxStorageRacks;

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

        Label lblStorageRacks = new Label("Reol: ");
        pane.add(lblStorageRacks, 1, 1);

        cbxStorageRacks = new ComboBox<>();
        pane.add(cbxStorageRacks, 2, 1);

        // Buttons which makes it possible to save or exit

        Button btnMove = new Button("Flyt til lager");
        pane.add(btnMove, 1, 2);
        btnMove.setOnAction(event -> this.moveAction());

        Button btnExit = new Button("Luk");
        pane.add(btnExit, 2, 2);
        btnExit.setOnAction(event -> this.exitAction());
    }

    private void moveAction() {

        this.hide();
    }

    private void exitAction() {
        this.hide();
    }
}
