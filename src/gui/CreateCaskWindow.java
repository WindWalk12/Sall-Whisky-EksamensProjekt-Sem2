package gui;

import application.controller.Controller;
import application.model.CaskType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CreateCaskWindow extends Stage {

    public CreateCaskWindow(String title) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene =  new Scene(pane);
        this.setScene(scene);
    }

    private TextField txfCountryOfOrigin, txfSupplier, txfVolume;

    private ComboBox cbxCaskType;

    private Button btnOK, btnCancel;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Textfields and labels where information about the new cask can be inserted
        Label lblCountryOfOrigin = new Label("Oprindelses land");
        pane.add(lblCountryOfOrigin,0,0);
        txfCountryOfOrigin = new TextField();
        pane.add(txfCountryOfOrigin,1,0);

        Label lblSupplier = new Label("Leverandør");
        pane.add(lblSupplier,0,1);
        txfSupplier = new TextField();
        pane.add(txfSupplier,1,1);

        Label lblVolume = new Label("Volumen");
        pane.add(lblVolume,0,2);
        txfVolume = new TextField();
        pane.add(txfVolume,1,2);

        Label lblCaskType = new Label("Fadtype");
        pane.add(lblCaskType,0,3);

        cbxCaskType = new ComboBox<>();
        pane.add(cbxCaskType,1,3);
        cbxCaskType.getItems().setAll(CaskType.values());
        cbxCaskType.getSelectionModel().select(0);

        btnOK = new Button("Gem fad");
        pane.add(btnOK,0,4);
        btnOK.setOnAction(event -> this.okAction());

        btnCancel = new Button("Annullere");
        pane.add(btnCancel,1,4);
        btnCancel.setOnAction(event -> this.cancelAction());
    }

    private void cancelAction() {
        this.hide();
    }

    private void okAction() {
        CaskType caskType = (CaskType) cbxCaskType.getSelectionModel().getSelectedItem();
        Controller.createCask(this.txfCountryOfOrigin.getText(), this.txfSupplier.getText(), Double.parseDouble(this.txfVolume.getText()), caskType);
        this.hide();
    }
}
