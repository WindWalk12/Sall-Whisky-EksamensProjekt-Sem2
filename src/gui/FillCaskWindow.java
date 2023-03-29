package gui;

import application.controller.Controller;
import application.model.Cask;
import application.model.Distillation;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;


public class FillCaskWindow extends Stage {

    public FillCaskWindow(String title, Distillation distillation) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.distillation = distillation;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private Distillation distillation;

    private TextField txfSelectedDistillation, txfVolume;

    private ComboBox<Cask> cbxCasks;

    private DatePicker dpFillDate;

    private Button btnFillCask, btnExit;

    private Label lblError = new Label();

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Textfield and labels

        Label lblSelectedDistillation = new Label("Distillation");
        pane.add(lblSelectedDistillation, 0, 0);

        txfSelectedDistillation = new TextField();
        pane.add(txfSelectedDistillation, 0, 1);
        txfSelectedDistillation.setText(distillation.getSpiritBatchNr());
        txfSelectedDistillation.setEditable(false);


        Label lblCasks = new Label("Fade");
        pane.add(lblCasks, 0, 2);

        cbxCasks = new ComboBox<>();
        pane.add(cbxCasks, 0, 3);
        cbxCasks.getItems().setAll(Controller.getEmptyCasks());
        cbxCasks.getSelectionModel().select(0);

        Label lblFillDate = new Label("Påfyldnings dato");
        pane.add(lblFillDate, 0, 4);

        dpFillDate = new DatePicker();
        pane.add(dpFillDate, 0, 5);
        dpFillDate.setEditable(true);
        dpFillDate.setValue(LocalDate.now());

        Label lblvolume = new Label("Volumen[L]");
        pane.add(lblvolume, 0, 6);

        txfVolume = new TextField();
        pane.add(txfVolume, 0, 7);


        // Buttons

        btnFillCask = new Button("Fyld på fad");
        pane.add(btnFillCask, 0, 8);
        btnFillCask.setOnAction(event -> this.fillCaskAction());

        btnExit = new Button("Luk");
        pane.add(btnExit, 1, 9);
        btnExit.setOnAction(event -> this.exitAction());

        lblError.setTextFill(Color.RED);
        pane.add(lblError, 0, 10, 2, 1);

    }

    private void fillCaskAction() {
        try {
            LocalDate fillingDate = dpFillDate.getValue();
            Cask cask = cbxCasks.getValue();
            String volume = txfVolume.getText();
            Controller.fillCask(fillingDate, distillation, cask, Integer.valueOf(volume));
            this.hide();
        } catch (RuntimeException e) {
            lblError.setText(e.getMessage());
        }
    }
    private void exitAction() {
        this.hide();
    }

}
