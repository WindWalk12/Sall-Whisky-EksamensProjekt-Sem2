package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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

    private TextField txfId, txfCountryOfOrigin, txfSupplier, txfVolume;

    private ComboBox cbxCaskType;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Textfields and labels where information about the new cask can be inserted
    }
}
