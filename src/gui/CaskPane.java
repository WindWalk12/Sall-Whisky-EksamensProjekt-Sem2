package gui;

import application.controller.Controller;
import application.model.Cask;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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


        // Buttons

        Button btnCreateNewCask = new Button("Opret nyt fad");
        this.add(btnCreateNewCask, 1, 1);
        btnCreateNewCask.setOnAction(event -> this.createNewCaskAction());

        Button btnMoveCask = new Button("Flyt fad til lager");
        this.add(btnMoveCask, 1,  2);
        btnMoveCask.setOnAction(event -> this.moveCaskAction());


    }

    // -------------------------------------------------------------------------

    private void selectedCaskChanged() {
    }

    public void updateList() {
        lvwCasks.getItems().setAll(Controller.getCasks());
    }

    private void createNewCaskAction() {
        CreateCaskWindow dia = new CreateCaskWindow("Opret nyt fad");
        dia.showAndWait();
        updateList();

    }
    private void moveCaskAction() {

    }
}
