package org.example.demo1;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class DailyReports extends BorderPane {
    public DailyReports() {
        GridPane gridPane = new GridPane();
        //Applying the css
        this.getStylesheets().add(getClass().getResource("/org/example/demo1/styles/Styles1.css").toExternalForm());
        this.setCenter(gridPane);
    }
}
