package gui;

import application.controller.Controller;
import application.model.Distillation;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class DistillationPane extends GridPane {

    private ListView<Distillation> lvwDistillation;

    public DistillationPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // Labels and ListView

        Label lblDistillation = new Label("Distillationer");
        this.add(lblDistillation, 0, 0);

        lvwDistillation = new ListView<>();
        this.add(lvwDistillation, 0, 1, 1, 7);
        lvwDistillation.setPrefWidth(200);
        lvwDistillation.setPrefHeight(300);
        lvwDistillation.getItems().setAll(Controller.getDistillations());

        // Buttons

        Button btnFillCask = new Button("Fyld på fad");
        this.add(btnFillCask, 1, 1);
        btnFillCask.setOnAction(event -> this.fillCaskAction());
    }

    private void fillCaskAction() {
        FillCaskWindow dia = new FillCaskWindow("Fyld på fad", this.lvwDistillation.getSelectionModel().getSelectedItem());
        dia.showAndWait();
        updateList();
    }

    public void updateList() {
        lvwDistillation.getItems().setAll(Controller.getDistillations());
    }
}
