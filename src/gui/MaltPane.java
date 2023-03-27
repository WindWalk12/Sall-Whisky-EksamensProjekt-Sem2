package gui;

import application.controller.Controller;
import application.model.Cask;
import application.model.Maltbatch;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class MaltPane extends GridPane {
    private final TableView<Maltbatch> table = new TableView<>();

    public MaltPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // Label and listview

        Label lblMaltBatches = new Label("Malt Batches");
        this.add(lblMaltBatches, 0, 0);

        TableColumn<Maltbatch, String> grainType = new TableColumn<>("Korntype");
        grainType.setCellValueFactory(new PropertyValueFactory<>("grainType"));
        TableColumn <Maltbatch, String> cornField = new TableColumn<>("Mark");
        cornField.setCellValueFactory(new PropertyValueFactory<>("cornField"));
        TableColumn<Maltbatch, LocalDate> harvestDate = new TableColumn<>("Høstet");
        harvestDate.setCellValueFactory(new PropertyValueFactory<>("harvestDate"));
        table.getColumns().addAll(grainType, cornField, harvestDate);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getItems().setAll(Controller.getMaltbatches());
        this.add(table, 0, 1, 1, 7);

        // Buttons

        Button btnCreateMaltBatch = new Button("Opret malt batch");
        this.add(btnCreateMaltBatch, 1, 1);
        btnCreateMaltBatch.setOnAction(event -> this.createMaltBatchAction());

    }
    public void updateList() {
        table.getItems().setAll(Controller.getMaltbatches());
    }

    private void createMaltBatchAction() {
        CreateMaltWindow dia = new CreateMaltWindow("Opret nyt malt batch");
        dia.showAndWait();
        updateList();
    }

}
