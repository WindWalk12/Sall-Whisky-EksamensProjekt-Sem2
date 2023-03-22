package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CreateStorageRackWindow extends Stage {

    public CreateStorageRackWindow(String title) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene =  new Scene(pane);
        this.setScene(scene);
    }

    private TextField txfId, txfRow, txfCol, txfWarehouse;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Textfields and labels where information about the StorageRack can be inserted

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


    }
}
