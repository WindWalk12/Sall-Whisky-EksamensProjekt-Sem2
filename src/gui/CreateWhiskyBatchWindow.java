package gui;

import application.controller.Controller;
import application.model.Cask;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;
import java.util.ArrayList;

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

    private TextField txfId, txfWaterVolume, txfTotalVolume, txfAlcPercentage, txfWhiskyType;

    private CheckBox cbxCaskStrength;

    private DatePicker dpTapped;

    private Button btnCreateBatch, btnCancel, btnPlus;

    private Label lblId, lblWaterVolume, lblTotalVolume, lblAlcPercentage, lblWhiskyType, lblCaskStrength, lblTapped, lblcask;

    private Label lblError = new Label();

    private ComboBox<Cask> cbxCasks;

    ArrayList<ComboBox> comboBoxes = new ArrayList<>();

    private int index = 7;

    private int numberOf = 1;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Textfields, checkbox and labels

        lblId = new Label("Id");
        pane.add(lblId, 0, 0);
        txfId = new TextField();
        pane.add(txfId, 1, 0);

        lblWaterVolume = new Label("Vand volumen");
        pane.add(lblWaterVolume, 0, 1);
        txfWaterVolume = new TextField();
        pane.add(txfWaterVolume, 1, 1);

        lblAlcPercentage = new Label("Alkohol procent");
        pane.add(lblAlcPercentage, 0, 2);
        txfAlcPercentage = new TextField();
        pane.add(txfAlcPercentage, 1, 2);

        lblTotalVolume = new Label("Total volumen");
        pane.add(lblTotalVolume, 0, 3);
        txfTotalVolume = new TextField();
        pane.add(txfTotalVolume, 1, 3);

        lblWhiskyType = new Label("Whisky type");
        pane.add(lblWhiskyType, 0, 4);
        txfWhiskyType = new TextField();
        pane.add(txfWhiskyType, 1, 4);

        lblCaskStrength = new Label("Cask strength");
        pane.add(lblCaskStrength, 0, 5);
        cbxCaskStrength = new CheckBox();
        pane.add(cbxCaskStrength, 1, 5);

        lblTapped = new Label("Tapnings dato");
        pane.add(lblTapped, 0, 6);
        dpTapped = new DatePicker();
        dpTapped.setValue(LocalDate.now());
        pane.add(dpTapped, 1, 6);

        lblcask = new Label("Cask");
        pane.add(lblcask, 0, 7);
        cbxCasks = new ComboBox<>();
        pane.add(cbxCasks, 1, 7);
        cbxCasks.getItems().setAll(Controller.getThreeYearOldCasks());


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

    }

    private void cancelAction() {
        this.hide();
    }

    private void createBatchAction() {
        if (!txfWhiskyType.getText().trim().isEmpty() && !txfId.getText().trim().isEmpty() && !txfWaterVolume.getText().trim().isEmpty() && !txfTotalVolume.getText().trim().isEmpty() && !txfAlcPercentage.getText().trim().isEmpty()) {
            try {
                String whiskyType = txfWhiskyType.getText().trim();
                String id = txfId.getText().trim();
                Double waterVolume = Double.parseDouble(txfWaterVolume.getText().trim());
                Double totalVolume = Double.parseDouble(txfTotalVolume.getText().trim());
                Double alcPercentage = Double.parseDouble(txfAlcPercentage.getText().trim());
                Boolean caskStrength = cbxCaskStrength.isSelected();
                LocalDate tapped = dpTapped.getValue();

               // Controller.createWhiskyBatch(waterVolume, caskStrength, alcPercentage, whiskyType, tapped, )

            } catch (IllegalArgumentException e) {
                lblError.setText(e.getMessage());
            }
        } else {
            lblError.setText("Der må ikke være nogen tomme felter");
        }

    }

    public void addNewComboBox(GridPane pane) {
        index++;
        numberOf++;
        Label lbl = new Label("Cask" + numberOf);
        pane.add(lbl, 0, index);
        ComboBox<Cask> cb = new ComboBox<>();
        cb.getItems().setAll(Controller.getThreeYearOldCasks());
        pane.add(cb, 1, index);
        comboBoxes.add(cb);
        pane.getChildren().remove(btnPlus);
        pane.getChildren().remove(btnCreateBatch);
        pane.getChildren().remove(btnCancel);
        pane.add(btnPlus, 1, index + 1);
        pane.add(btnCreateBatch, 0, index + 2);
        pane.add(btnCancel, 1, index + 2);
        sizeToScene();
    }
}
