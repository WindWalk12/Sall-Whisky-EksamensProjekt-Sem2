package gui;

import application.controller.Controller;
import application.model.Cask;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class CaskPane extends GridPane {
    private final TableView<Cask> table = new TableView<>();

    public CaskPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblCasks = new Label("Fade");
        this.add(lblCasks, 0, 0);

        TableColumn<Cask, String> id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn <Cask, String> castType = new TableColumn<>("Fadtype");
        castType.setCellValueFactory(new PropertyValueFactory<>("caskType"));
        TableColumn<Cask, String> tbwWarehouseName = new TableColumn<>("Lager");
        tbwWarehouseName.setCellValueFactory(new PropertyValueFactory<>("tbwWarehouseName"));
        TableColumn<Cask, String> tbwStorageRackId = new TableColumn<>("Plads");
        tbwStorageRackId.setCellValueFactory(new PropertyValueFactory<>("tbwStorageRackId"));
        TableColumn<Cask, Double> volume = new TableColumn<>("volume[L]");
        volume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        table.getColumns().addAll(id, castType, tbwWarehouseName, tbwStorageRackId, volume);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.add(table, 0, 1, 1, 7);



        // Buttons

        Button btnCreateNewCask = new Button("Opret nyt fad");
        this.add(btnCreateNewCask, 1, 1);
        btnCreateNewCask.setOnAction(event -> this.createNewCaskAction());
        btnCreateNewCask.setMaxWidth(Double.MAX_VALUE);

        Button btnMoveCask = new Button("Flyt fad til lager");
        this.add(btnMoveCask, 1,  2);
        btnMoveCask.setOnAction(event -> this.moveCaskAction());
        btnMoveCask.disableProperty().bind(Bindings.isNull(table.getSelectionModel().selectedItemProperty()));
        btnMoveCask.setMaxWidth(Double.MAX_VALUE);


    }

    // -------------------------------------------------------------------------

    private void selectedCaskChanged() {
    }

    public void updateList() {
        table.getItems().setAll(Controller.getCasks());
    }

    private void createNewCaskAction() {
        CreateCaskWindow dia = new CreateCaskWindow("Opret nyt fad");
        dia.showAndWait();
        updateList();
    }
    private void moveCaskAction() {
        MoveCaskWindow dia = new MoveCaskWindow("Flyt fad", this.table.getSelectionModel().getSelectedItem());
        dia.showAndWait();
        updateList();

    }
}
