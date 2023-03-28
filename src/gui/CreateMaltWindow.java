package gui;

import application.controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;


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

    private Button btnCreateMalt, btnExit;
    private HBox hbButtons;

    private Label lblError = new Label();

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Textfields and labels

        Label lblCornField = new Label("Mark");
        pane.add(lblCornField, 0, 0);

        txfCornField = new TextField();
        pane.add(txfCornField, 1, 0);
        txfCornField.setEditable(true);

        Label lblGrainType = new Label("Kornsort");
        pane.add(lblGrainType, 0, 1);

        txfGrainType = new TextField();
        pane.add(txfGrainType, 1, 1);
        txfGrainType.setEditable(true);

        Label lblFarmer = new Label("Bondemand");
        pane.add(lblFarmer, 0, 2);

        txfFarmer = new TextField();
        pane.add(txfFarmer, 1, 2);
        txfFarmer.setEditable(true);

        Label lblHarvestDate = new Label("Høstdato");
        pane.add(lblHarvestDate, 0, 3);

        dpHarvestDate = new DatePicker();
        pane.add(dpHarvestDate, 1, 3);

        Label lblMaltDate = new Label("Maltningsdato");
        pane.add(lblMaltDate, 0, 4);

        dpMaltDate = new DatePicker();
        pane.add(dpMaltDate, 1, 4);

        // Buttons

        btnCreateMalt = new Button("Opret malt batch");
        btnCreateMalt.setOnAction(event -> this.createMaltAction());

        btnExit = new Button("Luk");
        btnExit.setOnAction(event -> this.exitAction());

        hbButtons = new HBox(btnCreateMalt, btnExit);
        pane.add(hbButtons,0, 6, 2, 1);
        hbButtons.setSpacing(10);

        lblError.setTextFill(Color.RED);
        pane.add(lblError, 0, 7, 2, 1);

    }

    private void createMaltAction() {
        if (!txfCornField.getText().isEmpty() && !txfGrainType.getText().isEmpty() && !txfFarmer.getText().isEmpty() && dpHarvestDate.getValue() != null && dpMaltDate.getValue() != null) {
            String cornfield = txfCornField.getText().trim();
            String graintype = txfGrainType.getText().trim();
            String farmer = txfFarmer.getText().trim();
            LocalDate harvestdate = dpHarvestDate.getValue();
            LocalDate maltdate = dpMaltDate.getValue();
            Controller.createMaltbatch(cornfield, graintype, harvestdate, farmer, maltdate);
            this.hide();
        } else {
            lblError.setText("Der må ikke være nogen tomme felter");
        }
    }
    private void exitAction() {
        this.hide();
    }
}
