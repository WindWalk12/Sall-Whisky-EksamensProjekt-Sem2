package gui;

import application.controller.Controller;
import application.model.Distillation;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class DistillationPane extends GridPane {
    private final TableView<Distillation> table = new TableView<>();

    public DistillationPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // Labels and ListView

        Label lblDistillation = new Label("Destillationer");
        this.add(lblDistillation, 0, 0);

        TableColumn<Distillation, String> spiritBatchNr = new TableColumn<>("Batch nummer");
        spiritBatchNr.setCellValueFactory(new PropertyValueFactory<>("spiritBatchNr"));
        TableColumn <Distillation, String> volumen = new TableColumn<>("Volume[L]");
        volumen.setCellValueFactory(new PropertyValueFactory<>("volumen"));
        table.getColumns().addAll(spiritBatchNr,volumen);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.add(table, 0, 1, 1, 7);

        // Buttons

        Button btnFillCask = new Button("Fyld på fad");
        this.add(btnFillCask, 1, 1);
        btnFillCask.setOnAction(event -> this.fillCaskAction());
        btnFillCask.disableProperty().bind(Bindings.isNull(table.getSelectionModel().selectedItemProperty()));
        btnFillCask.setMaxWidth(Double.MAX_VALUE);

        Button btnCreateDistillation = new Button("Opret destillation");
        this.add(btnCreateDistillation, 1, 2);
        btnCreateDistillation.setOnAction(event -> this.createDistillationAction());
        btnCreateDistillation.setMaxWidth(Double.MAX_VALUE);
    }

    private void createDistillationAction() {
        CreateDistillationWindow dia = new CreateDistillationWindow("Opret destillation");
        dia.showAndWait();
        updateList();
    }

    private void fillCaskAction() {
        FillCaskWindow dia = new FillCaskWindow("Fyld på fad", this.table.getSelectionModel().getSelectedItem());
        dia.showAndWait();
        updateList();
    }

    public void updateList() {
        table.getItems().setAll(Controller.getDistillations());
    }
}
