package gui;

import application.controller.Controller;
import application.model.WhiskyBatch;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class WhiskyPane extends GridPane {

    private TableView<WhiskyBatch> tvwWhisky = new TableView<>();

    private Alert alert;

    private Button btnCreateWhiskyBatch, btnViewHistory, btnFillBottle;

    public WhiskyPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblWhisky = new Label("Whisky");
        this.add(lblWhisky, 0, 0);

        TableColumn<WhiskyBatch, String> id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        TableColumn<WhiskyBatch, Double> waterVolume = new TableColumn<>("Vand volumen");
        waterVolume.setCellValueFactory(new PropertyValueFactory<>("waterVolume"));
        TableColumn<WhiskyBatch, Double> totalVolume = new TableColumn<>("Total volumen");
        totalVolume.setCellValueFactory(new PropertyValueFactory<>("totalVolume"));
        TableColumn<WhiskyBatch, Boolean> caskStrength = new TableColumn<>("Fortyndet");
        caskStrength.setCellValueFactory(new PropertyValueFactory<>("caskStrength"));
        TableColumn<WhiskyBatch, Double> alcPercentage = new TableColumn<>("Alkohol %");
        alcPercentage.setCellValueFactory(new PropertyValueFactory<>("alcPrercntage"));
        TableColumn<WhiskyBatch, String> whiskyType = new TableColumn<>("Type");
        whiskyType.setCellValueFactory(new PropertyValueFactory<>("whiskyType"));
        tvwWhisky.getColumns().addAll(id, waterVolume, totalVolume, caskStrength, alcPercentage, whiskyType);
        tvwWhisky.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.add(tvwWhisky, 0, 1, 1, 7);


        // Buttons

        btnCreateWhiskyBatch = new Button("Opret nyt whisky batch");
        this.add(btnCreateWhiskyBatch, 1, 1);
        btnCreateWhiskyBatch.setOnAction(event -> this.createWhiskyAction());
        btnCreateWhiskyBatch.setMaxWidth(Double.MAX_VALUE);

        btnViewHistory = new Button("Se historik");
        this.add(btnViewHistory, 1, 2);
        btnViewHistory.setOnAction(event -> this.viewHistoryAction());
        btnViewHistory.disableProperty().bind(Bindings.isNull(tvwWhisky.getSelectionModel().selectedItemProperty()));
        btnViewHistory.setMaxWidth(Double.MAX_VALUE);

        btnFillBottle = new Button("Fyld på flaske");
        this.add(btnFillBottle, 1, 3);
        btnFillBottle.setOnAction(event -> this.fillBottleAction());
        btnFillBottle.disableProperty().bind(Bindings.isNull(tvwWhisky.getSelectionModel().selectedItemProperty()));
        btnFillBottle.setMaxWidth(Double.MAX_VALUE);

    }

    // -------------------------------------------------------------------------

    public void updateList() {
        tvwWhisky.getItems().setAll(Controller.getWhiskyBatch());
    }

    private void createWhiskyAction() {
        CreateWhiskyBatchWindow dia = new CreateWhiskyBatchWindow("Opret nyt whisky batch");
        dia.showAndWait();
        updateList();
    }

    private void viewHistoryAction() {
        ViewHistoryWindow dia = new ViewHistoryWindow("Historik", tvwWhisky.getSelectionModel().getSelectedItem());
        dia.showAndWait();
        updateList();
    }

    private void fillBottleAction() {
        Controller.createWhiskyBottles(tvwWhisky.getSelectionModel().getSelectedItem());
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Whisky er fyldt på flasker");
        alert.showAndWait();
        updateList();
    }
}
