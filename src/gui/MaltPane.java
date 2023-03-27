package gui;

import application.controller.Controller;
import application.model.Maltbatch;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class MaltPane extends GridPane {

    private ListView<Maltbatch> lvwMaltBatches;

    public MaltPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // Label and listview

        Label lblMaltBatches = new Label("Malt Batches");
        this.add(lblMaltBatches, 0, 0);

        lvwMaltBatches = new ListView<>();
        this.add(lvwMaltBatches, 0, 1, 1, 7);
        lvwMaltBatches.setPrefWidth(200);
        lvwMaltBatches.setPrefHeight(300);
        lvwMaltBatches.getItems().setAll(Controller.getMaltbatches());

        // Buttons

        Button btnCreateMaltBatch = new Button("Opret nyt malt batch");
        this.add(btnCreateMaltBatch, 1, 1);
        btnCreateMaltBatch.setOnAction(event -> this.createMaltBatchAction());

    }
    public void updateList() {
        lvwMaltBatches.getItems().setAll(Controller.getMaltbatches());
    }

    private void createMaltBatchAction() {
        CreateMaltWindow dia = new CreateMaltWindow("Opret nyt malt batch");
        dia.showAndWait();
        updateList();
    }

}
