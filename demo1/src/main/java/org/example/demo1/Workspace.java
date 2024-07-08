package org.example.demo1;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class Workspace extends BorderPane {
    public Workspace() {
        super();
        initializeUI();
    }

    private void initializeUI() {
        // Sidebar creation
        VBox sidebar = new VBox(10); // 10 is the spacing between elements
        sidebar.setPrefWidth(100); // Set the preferred width of the sidebar
        HBox upbar = new HBox(10);
        upbar.setPrefWidth(50);
        //Adding some css
        sidebar.setStyle("-fx-background-color: #523722");
        upbar.setStyle("-fx-background-color: #e6f2f0; -fx-border-color: black; -fx-border-width: 2px;");
        //Load the icons
        Image homeImage = new Image(getClass().getResourceAsStream("/org/example/demo1/images/home.png"));
        Image settingsImage = new Image(getClass().getResourceAsStream("/org/example/demo1/images/settings.png"));
        Image logoutImage = new Image(getClass().getResourceAsStream("/org/example/demo1/images/logout.png"));
        Image profileImage = new Image(getClass().getResourceAsStream("/org/example/demo1/images/user.png"));


        //creating imageview for each icon
        ImageView homeIcon = new ImageView(homeImage);
        ImageView settingsIcon = new ImageView(settingsImage);
        ImageView logoutIcon = new ImageView(logoutImage);
        ImageView profileIcon = new ImageView(profileImage);

        //Adjusting the icon size
        homeIcon.setFitHeight(40);
        homeIcon.setFitWidth(40);
        settingsIcon.setFitHeight(40);
        settingsIcon.setFitWidth(40);
        logoutIcon.setFitHeight(40);
        logoutIcon.setFitWidth(40);
        profileIcon.setFitHeight(40);
        profileIcon.setFitWidth(40);

        //Creating buttons with icons
        Button homeButton = new Button("", homeIcon);
        Button settingsButton = new Button("", settingsIcon);
        Button logoutButton = new Button("", logoutIcon);
        Button profileButton = new Button("", profileIcon);

        // Add buttons or other controls to the sidebar
        sidebar.getChildren().addAll(homeButton,profileButton, settingsButton, logoutButton);

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
        upbar.getChildren().addAll(spacerleft, headerLabel, spacerRight);
        this.setTop(upbar);

        // Other main content can be added to the center
        // this.setCenter(...);
    }
}