package org.example.demo1;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Workspace extends BorderPane {
    public Workspace() {
        super();
        initializeUI();
    }
    public void setBackgroundColor(){
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#000000"), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        this.setBackground(background);
    }
    public void styleBorderPane() {
        this.setStyle("-fx-border-color: #ffffff;"+
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 5px");
    }

    private void initializeUI() {
        setBackgroundColor();
        styleBorderPane();
        // Sidebar creation
        VBox sidebar = new VBox(10); // 10 is the spacing between elements
        //  Dynamic width calculation for sidebar based on BorderPane's width
        this.widthProperty().addListener((observable, oldValue, newValue) ->{
            double paneWidth = newValue.doubleValue();
            sidebar.setPrefWidth(paneWidth * 0.1); //setting sidebar to 10% of total width
        });
        //Create buttons
        
        HBox upBar = new HBox(10);
        upBar.setPrefWidth(50);
        //Adding some css
        sidebar.setStyle("-fx-background-color: rgba(122,157,185,255)");
        upBar.setStyle("-fx-background-color: #e6f2f0; -fx-border-color: black; -fx-border-width: 2px;");
//

        // Set the sidebar to the left of the main layout
        this.setLeft(sidebar);
        //and the hbox vertical
        //but first a label
        Label headerLabel = new Label("Dashboard");
        headerLabel.setStyle("-fx-font-weight: bold");
        Pane spacerleft = new Pane();
        Pane spacerRight = new Pane();
        HBox.setHgrow(spacerleft, Priority.ALWAYS);
        HBox.setHgrow(spacerRight, Priority.ALWAYS);
        spacerleft.setMinSize(10, 1);
        spacerRight.setMinSize(10, 1);
        upBar.getChildren().addAll(spacerleft, headerLabel, spacerRight);
        this.setTop(upBar);

    }
}