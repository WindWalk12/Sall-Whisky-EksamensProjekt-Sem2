package gui;

import application.model.WhiskyBatch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewHistoryWindow extends Stage {

    public ViewHistoryWindow(String title, WhiskyBatch whiskyBatch) {
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

    private WhiskyBatch whiskyBatch;

    private TextArea txaHistory;

    private Label lblHistory;

    private Button btnCopyHistory, btnCancel;

    private Clipboard clipboard = Clipboard.getSystemClipboard();
    private ClipboardContent content = new ClipboardContent();


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Textarea and label

        lblHistory = new Label("Historik for whisky batch");
        pane.add(lblHistory, 0, 0);
        txaHistory = new TextArea();
        pane.add(txaHistory, 0, 1);
        txaHistory.setText(whiskyBatch.createHistoryString());

        // Buttons

        btnCopyHistory = new Button("Kopier historik");
        pane.add(btnCopyHistory, 1, 3);
        btnCopyHistory.setMaxWidth(Double.MAX_VALUE);
        btnCopyHistory.setOnAction(event -> this.copyHistoryAction());

        btnCancel = new Button("Luk");
        pane.add(btnCancel, 2, 3);
        btnCancel.setMaxWidth(Double.MAX_VALUE);
        btnCancel.setOnAction(event -> this.cancelAction());

    }

    private void cancelAction() {
        this.hide();
    }

    private void copyHistoryAction() {
        String history = txaHistory.getText().trim();

        content.putString(history);
        clipboard.setContent(content);
        this.hide();
    }

}
