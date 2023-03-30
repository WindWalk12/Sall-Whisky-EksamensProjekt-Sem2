package gui;

import application.controller.Controller;
import application.model.WhiskyBatch;
import application.model.WhiskyBottle;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewBottlesWindow extends Stage {

    private TableView<WhiskyBottle> tvwWhiskyBottle = new TableView<>();

    private Button btnViewHistory;

    private WhiskyBatch whiskyBatch;

    public ViewBottlesWindow(String title, WhiskyBatch whiskyBatch) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.whiskyBatch = whiskyBatch;


        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene =  new Scene(pane);
        this.setScene(scene);
    }


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblWhiskyBottle = new Label("Whiskyflasker");
        pane.add(lblWhiskyBottle, 0, 0);

        TableColumn<WhiskyBottle, String> id = new TableColumn<>("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
      //  TableColumn<WhiskyBottle, Double> volume = new TableColumn<>("volume");
      //  id.setCellValueFactory(new PropertyValueFactory<>("volume"));
        tvwWhiskyBottle.getColumns().addAll(id);
        tvwWhiskyBottle.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pane.add(tvwWhiskyBottle, 0, 1, 1, 7);

        tvwWhiskyBottle.getItems().setAll(Controller.getWhiskyBottles(whiskyBatch));
    }


}
