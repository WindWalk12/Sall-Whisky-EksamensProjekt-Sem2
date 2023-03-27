package gui;

import application.controller.Controller;
import application.model.Cask;
import application.model.Distillation;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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

    private TextField txfSelectedDistillation;

    private ComboBox<Cask> cbxCasks;

    private DatePicker dpFillDate;

    private Button btnFillCask, btnCancel;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Textfield and labels

        Label lblSelectedDistillation = new Label("Distillation");
        pane.add(lblSelectedDistillation, 1, 0);

        txfSelectedDistillation = new TextField();
        pane.add(txfSelectedDistillation, 2, 0);
        txfSelectedDistillation.setText(distillation.getSpiritBatchNr());
        txfSelectedDistillation.setEditable(false);


        Label lblCasks = new Label("Fade");
        pane.add(lblCasks, 3, 0);

        cbxCasks = new ComboBox<>();
        pane.add(cbxCasks, 4, 0);
        cbxCasks.getItems().setAll(Controller.getCasks());
        cbxCasks.getSelectionModel().select(0);

        Label lblFillDate = new Label("Påfyldnings dato");
        pane.add(lblFillDate, 5, 0);

        dpFillDate = new DatePicker();
        pane.add(dpFillDate, 6, 0);
        dpFillDate.setEditable(true);


        // Buttons

        btnFillCask = new Button("Fyld på fad");
        pane.add(btnFillCask, 7, 0);
        btnFillCask.setOnAction(event -> this.fillCaskAction());

        Button btnExit = new Button("Luk");
        pane.add(btnExit, 7, 1);
        btnExit.setOnAction(event -> this.exitAction());

    }

    private void fillCaskAction() {

        LocalDate fillingDate = dpFillDate.getValue();

        Cask cask = cbxCasks.getValue();

        Controller.fillCask(fillingDate, distillation, cask);

    }
    private void exitAction() {
        this.hide();
    }

}
