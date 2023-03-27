package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class CreateMaltWindow extends Stage {

    public CreateMaltWindow(String title) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene =  new Scene(pane);
        this.setScene(scene);
    }

    private TextField txfCornField, txfGrainType, txfFarmer;

    private DatePicker dpHarvestDate, dpMaltDate;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Textfields and labels

        Label lblCornField = new Label("Mark");
        pane.add(lblCornField, 0, 0);

        txfCornField = new TextField();
        pane.add(txfCornField, 0, 1);
        txfCornField.setEditable(true);

        Label lblGrainType = new Label("Kornsort");
        pane.add(lblGrainType, 0, 2);

        txfGrainType = new TextField();
        pane.add(txfGrainType, 0, 3);
        txfGrainType.setEditable(true);


    }
}
