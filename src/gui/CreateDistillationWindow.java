package gui;

import application.controller.Controller;
import application.model.Maltbatch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;


public class CreateDistillationWindow extends Stage {

    public CreateDistillationWindow(String title) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene =  new Scene(pane);
        this.setScene(scene);
    }

    private TextField txfSpiritBatchNr, txfVolume, txfAlcPercentage, txfEmployeeName, txfSmokingMaterial;

    private DatePicker dpStartDate, dpEndDate;

    private javafx.scene.control.TextArea txaComment;

    private Button btnCreateDistillation, btnExit;

    private ComboBox<Maltbatch> cbxMaltBatches;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Labels and textfields, textareas and datepickers

        Label lblSpiritBatchNr = new Label("Batch nummer");
        pane.add(lblSpiritBatchNr, 0, 0);

        txfSpiritBatchNr = new TextField();
        pane.add(txfSpiritBatchNr, 1, 0);
        txfSpiritBatchNr.setEditable(true);

        Label lblVolume = new Label("Volume");
        pane.add(lblVolume, 0, 1);

        txfVolume = new TextField();
        pane.add(txfVolume, 1, 1);
        txfVolume.setEditable(true);

        Label lblAlcPercentage = new Label("Alkoholprocent");
        pane.add(lblAlcPercentage, 0, 2);

        txfAlcPercentage = new TextField();
        pane.add(txfAlcPercentage, 1, 2);
        txfAlcPercentage.setEditable(true);

        Label lblEmployeeName = new Label("Medarbejder");
        pane.add(lblEmployeeName, 0, 3);

        txfEmployeeName = new TextField();
        pane.add(txfEmployeeName, 1, 3);
        txfEmployeeName.setEditable(true);

        Label lblSmokingMaterial = new Label("Røgmateriale");
        pane.add(lblSmokingMaterial, 0, 4);

        txfSmokingMaterial = new TextField();
        pane.add(txfSmokingMaterial, 1, 4);

        Label lblStartDate = new Label("Start dato");
        pane.add(lblStartDate, 0, 5);

        dpStartDate = new DatePicker();
        pane.add(dpStartDate, 1, 5);

        Label lblEndDate = new Label("Slut dato");
        pane.add(lblEndDate, 0, 6);

        dpEndDate = new DatePicker();
        pane.add(dpEndDate, 1, 6);
        dpEndDate.setValue(LocalDate.now());

        Label lblMaltBatch = new Label("Malt batch");
        pane.add(lblMaltBatch, 0, 7);

        cbxMaltBatches = new ComboBox<>();
        pane.add(cbxMaltBatches, 1, 7);
        cbxMaltBatches.getItems().setAll(Controller.getMaltbatches());

        Label lblComment = new Label("Kommentar");
        pane.add(lblComment, 0, 8);

        txaComment = new TextArea();
        pane.add(txaComment, 1, 8);
        txaComment.setEditable(true);

        // Buttons

        btnCreateDistillation = new Button("Opret destillering");
        pane.add(btnCreateDistillation, 0, 9);
        btnCreateDistillation.setOnAction(event -> this.createDistillationAction());

        btnExit = new Button("Luk");
        pane.add(btnExit, 1, 9);
        btnExit.setOnAction(event -> this.exitAction());

    }

    private void createDistillationAction() {

        double volume = Double.parseDouble(txfVolume.getText().trim());
        double alcPercentage = Double.parseDouble(txfAlcPercentage.getText().trim());
        LocalDate startDate = dpStartDate.getValue();
        LocalDate endDate = dpEndDate.getValue();
        String employeeName = txfEmployeeName.getText().trim();
        String smokingMaterial = txfSmokingMaterial.getText().trim();
        String spiritbatch = txfSpiritBatchNr.getText().trim();
        String comment = txaComment.getText().trim();
        Maltbatch maltbatch = cbxMaltBatches.getValue();

        Controller.createDistillation(volume, alcPercentage, startDate, endDate, employeeName, smokingMaterial, comment, maltbatch, spiritbatch);
        this.hide();
    }

    private void exitAction() {
        this.hide();
    }
}
