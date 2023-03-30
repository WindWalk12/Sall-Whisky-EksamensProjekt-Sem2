package gui;

import application.controller.Controller;
import application.model.WhiskyBottle;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class BottlePane extends GridPane {

    private TableView<WhiskyBottle> tvwWhiskyBottle = new TableView<>();

    private Button btnViewHistory;

    public BottlePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblWhiskyBottle = new Label("Whiskyflaske");
        this.add(lblWhiskyBottle, 0, 0);

        TableColumn<WhiskyBottle, String> id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<WhiskyBottle, Double> volume = new TableColumn<>("Volumen");
        id.setCellValueFactory(new PropertyValueFactory<>("volume"));
        tvwWhiskyBottle.getColumns().addAll(id, volume);
        tvwWhiskyBottle.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.add(tvwWhiskyBottle, 0, 1, 1, 7);
    }

    public void updateList() {
        tvwWhiskyBottle.getItems().setAll(Controller.getWhiskyBottles());
    }
}
