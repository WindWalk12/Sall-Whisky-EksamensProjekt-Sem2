package gui;

import application.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartWindow extends Application {



    @Override
    public void init() {
        Controller.initStorage();
    }


    @Override
    public void start(Stage stage) {
        stage.setTitle("Sporbarhed i produktionen og fadlagerstyring");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    // -------------------------------------------------------------------------

    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        Tab tabCaskPane = new Tab("Fade");
        tabPane.getTabs().add(tabCaskPane);

        CaskPane caskPane = new CaskPane();
        tabCaskPane.setContent(caskPane);
        tabCaskPane.setOnSelectionChanged(event -> caskPane.updateList());

        Tab tabWarehousePane = new Tab("Lager");
        tabPane.getTabs().add(tabWarehousePane);

        WarehousePane warehousePane = new WarehousePane();
        tabWarehousePane.setContent(warehousePane);
        tabWarehousePane.setOnSelectionChanged(event -> warehousePane.updateList());


    }

}
