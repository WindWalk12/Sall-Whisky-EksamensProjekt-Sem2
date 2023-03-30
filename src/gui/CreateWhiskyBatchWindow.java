package gui;

import application.controller.Controller;
import application.model.Cask;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateWhiskyBatchWindow extends Stage {

    public CreateWhiskyBatchWindow(String title) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene =  new Scene(pane);
        this.setScene(scene);
    }

    private TextField txfWaterVolume, txfAlcPercentage, txfWhiskyType, txfTapVolume;

    private CheckBox cbxCaskStrength;

    private DatePicker dpTapped;

    private Button btnCreateBatch, btnCancel, btnPlus;

    private Label lblWaterVolume, lblAlcPercentage, lblWhiskyType, lblCaskStrength, lblTapped, lblcask, lblTapVolume;

    private Label lblError = new Label();

    private ComboBox<Cask> cbxCasks;

    ArrayList<ComboBox> comboBoxes = new ArrayList<>();
    ArrayList<TextField> doubles = new ArrayList<>();

    private int index = 6;

    private int numberOf = 1;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Textfields, checkbox and labels

        lblWaterVolume = new Label("Vand volumen");
        pane.add(lblWaterVolume, 0, 0);
        txfWaterVolume = new TextField();
        pane.add(txfWaterVolume, 1, 0);

        lblAlcPercentage = new Label("Alkohol procent");
        pane.add(lblAlcPercentage, 0, 1);
        txfAlcPercentage = new TextField();
        pane.add(txfAlcPercentage, 1, 1);

        lblWhiskyType = new Label("Whisky type");
        pane.add(lblWhiskyType, 0, 2);
        txfWhiskyType = new TextField();
        pane.add(txfWhiskyType, 1, 2);

        lblCaskStrength = new Label("Cask strength");
        pane.add(lblCaskStrength, 0, 3);
        cbxCaskStrength = new CheckBox();
        pane.add(cbxCaskStrength, 1, 3);

        lblTapped = new Label("Tapnings dato");
        pane.add(lblTapped, 0, 4);
        dpTapped = new DatePicker();
        dpTapped.setValue(LocalDate.now());
        pane.add(dpTapped, 1, 4);

        lblcask = new Label("Cask");
        pane.add(lblcask, 0, 5);
        cbxCasks = new ComboBox<>();
        cbxCasks.getItems().setAll(Controller.getThreeYearOldCasks());
        if (Controller.getThreeYearOldCasks().size() > 0) {
            cbxCasks.getSelectionModel().select(0);
        }
        pane.add(cbxCasks, 1, 5);
        lblTapVolume = new Label("Tappet[L]");
        pane.add(lblTapVolume, 0, 6);
        txfTapVolume = new TextField();
        pane.add(txfTapVolume, 1, 6);




        // Buttons

        btnPlus = new Button("+");
        pane.add(btnPlus, 1, index + 1);
        btnPlus.setOnAction(event -> this.addNewComboBox(pane));

        btnCreateBatch = new Button("Opret batch");
        pane.add(btnCreateBatch, 0, index + 2);
        btnCreateBatch.setMaxWidth(Double.MAX_VALUE);
        btnCreateBatch.setOnAction(event -> this.createBatchAction());

        btnCancel = new Button("Luk");
        pane.add(btnCancel, 1, index + 2);
        btnCancel.setOnAction(event -> this.cancelAction());

        lblError.setTextFill(Color.RED);
        pane.add(lblError, 0, index + 3, 2, 1);

    }

    private void cancelAction() {
        this.hide();
    }

    private void createBatchAction() {
        boolean emptyComboBox = false;
        boolean emptyTapped = false;
        for (ComboBox<Cask> cb : comboBoxes) {
            if (cb.getSelectionModel().isEmpty()) {
                emptyComboBox = true;
                break;
            }
        }
        for (TextField txf : doubles) {
            if (txf.getText().isEmpty()) {
                emptyTapped = true;
                break;
            }
        }

        if (!emptyComboBox && !emptyTapped && !txfWhiskyType.getText().trim().isEmpty() && !txfWaterVolume.getText().trim().isEmpty() && !txfAlcPercentage.getText().trim().isEmpty() && !cbxCasks.getSelectionModel().isEmpty() && !txfTapVolume.getText().isEmpty()) {
            Map<Cask, Double> mapOfchosenCasks = new HashMap<>();
            for (int i = 0; i < comboBoxes.size(); i++) {
                mapOfchosenCasks.put((Cask)comboBoxes.get(i).getSelectionModel().getSelectedItem(), Double.parseDouble(doubles.get(i).getText()));
            }
            try {
                String whiskyType = txfWhiskyType.getText().trim();
                double waterVolume = Double.parseDouble(txfWaterVolume.getText().trim());
                double alcPercentage = Double.parseDouble(txfAlcPercentage.getText().trim());
                boolean caskStrength = cbxCaskStrength.isSelected();
                LocalDate tapped = dpTapped.getValue();
                Controller.createWhiskyBatch(waterVolume, caskStrength, alcPercentage, whiskyType, tapped, mapOfchosenCasks);
                this.hide();
            } catch (IllegalArgumentException e) {
                lblError.setText(e.getMessage());
            }
        } else {
            lblError.setText("Der må ikke være nogen tomme felter");
        }

    }

    public void addNewComboBox(GridPane pane) {
        index += 2;
        numberOf++;
        Label lbl = new Label("Cask" + numberOf);
        pane.add(lbl, 0, index);
        ComboBox<Cask> cb = new ComboBox<>();
        cb.getItems().setAll(Controller.getThreeYearOldCasks());
        if (Controller.getThreeYearOldCasks().size() > 0) {
            cb.getSelectionModel().select(0);
        }
        pane.add(cb, 1, index);
        Label lbl2 = new Label("Tappet[L]");
        pane.add(lbl2, 0, index + 1);
        TextField txf = new TextField();
        pane.add(txf, 1, index + 1);
        comboBoxes.add(cb);
        doubles.add(txf);
        pane.getChildren().remove(btnPlus);
        pane.getChildren().remove(btnCreateBatch);
        pane.getChildren().remove(btnCancel);
        pane.getChildren().remove(lblError);
        pane.add(btnPlus, 1, index + 2);
        pane.add(btnCreateBatch, 0, index + 3);
        pane.add(btnCancel, 1, index + 3);
        pane.add(lblError, 0, index + 4);
        sizeToScene();
    }
}
