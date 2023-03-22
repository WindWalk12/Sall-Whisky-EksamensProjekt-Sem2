package gui;

import application.controller.Controller;
import application.model.Cask;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class CaskPane extends GridPane {
    private ListView<Cask> lvwCasks;

    public CaskPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblCasks = new Label("Fade");
        this.add(lblCasks, 0, 0);

        lvwCasks = new ListView<>();
        this.add(lvwCasks, 0, 1, 1, 7);
        lvwCasks.setPrefWidth(200);
        lvwCasks.setPrefHeight(300);
        lvwCasks.getItems().setAll(Controller.getCasks());


    }

    // -------------------------------------------------------------------------

    private void selectedCaskChanged() {
        this.updateView();
    }

    public void updateList() {
        lvwCasks.getItems().setAll(Controller.getCasks());
    }

    private void updateView() {}
}
