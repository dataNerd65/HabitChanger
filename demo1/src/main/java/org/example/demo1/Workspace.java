package org.example.demo1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.InputStream;

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
    private Button createSidebarButton(String text, VBox sidebar, Runnable action){
        Button button = new Button(text);
        button.prefWidthProperty().bind(sidebar.widthProperty());
        button.setOnAction(event -> action.run());

        //Styling the buttons
        String buttonStyle = "-fx-background-color: #98dce7;"+
                "-fx-text-fill: black;" +
                "-fx-font-family: 'Georgia';"+
                "-fx-font-style: italic;"+
                "-fx-border-color: white;"+
                "-fx-border-width: 2;"+
                "-fx-font-size: 14px;";
        button.setStyle(buttonStyle);
        return button;
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
        //Create buttons calling the method
        Button profileButton = createSidebarButton("Your profile", sidebar, () -> System.out.println("Profile button clicked!"));
        Button dailyReportsButton = createSidebarButton("Daily Reports", sidebar, () -> System.out.println("Daily Reports button clicked!"));
        Button weeklyReportsButton = createSidebarButton("Weekly Reports", sidebar, () -> System.out.println("Weekly Reports button clicked!"));
        Button financialsButton = createSidebarButton("Financials", sidebar, () -> System.out.println("Financials button clicked!"));
        Button MyCalendar = createSidebarButton("Calendar", sidebar, () -> System.out.println("Calendar button clicked!"));
        Button logoutButton = createSidebarButton("Logout", sidebar, () -> System.out.println("Logout button clicked!"));
        Button settingsButton = createSidebarButton("Settings", sidebar, () -> System.out.println("Settings button clicked!"));

        //Adding the buttons to sideBar
        sidebar.getChildren().addAll(profileButton, dailyReportsButton, weeklyReportsButton, financialsButton, MyCalendar, logoutButton, settingsButton);
        
        HBox upBar = new HBox(10);
        upBar.setPrefWidth(100);
        //Adding some css
        sidebar.setStyle("-fx-background-color: rgba(122,157,185,255);"+
                "-fx-border-color: red;"+
                "-fx-border-width: 2;");
        upBar.setStyle("-fx-background-color: #e6f2f0; -fx-border-color: red; -fx-border-width: 2px;");

        // Set the sidebar to the left of the main layout
        this.setLeft(sidebar);
        //and the hbox vertical
        //but first a label
        Label headerLabel = new Label("Dashboard");
        headerLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;");//Increase font size for larger text
        Pane spacerleft = new Pane();
        Pane spacerRight = new Pane();
        HBox.setHgrow(spacerleft, Priority.ALWAYS);
        HBox.setHgrow(spacerRight, Priority.ALWAYS);
        upBar.getChildren().addAll(spacerleft, headerLabel, spacerRight);
        this.setTop(upBar);

        // Create a GridPane for more flexible positioning
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Horizontal gap between columns
        gridPane.setVgap(10); // Vertical gap between rows

        // Create and style the center label
        Label centerLabel = new Label("Welcome Peter. Freedom from sins is guaranteed in God's word.");
        //bible verse label
        Label bibleVerseLabel = new Label("It is for freedom that Christ has set us free. Stand firm, then, and do not let yourselves be burdened by a yoke of slavery. -Gal 5:1");
        //Label for invites
        Label invitesLabel = new Label("Would you like to invite any of your friends to also experience it?");
        //Styling the labels
        String labelTextStyle = "-fx-text-fill: black;" +
                "-fx-font-family: 'Georgia';"+
                "-fx-font-style: italic;"+
                "-fx-font-size: 20px;";

        //checkbox
        CheckBox invitesCheckbox = new CheckBox("Yes, I would like to invite my friends.");
        //styling
        invitesCheckbox.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 18px; -fx-font-weight: bold;");

        //TextField for entering friend's email, initially not visible
        TextField friendEmail = new TextField();
        friendEmail.setVisible(false); // initially hidden
        friendEmail.setPromptText("Enter their email...");
        friendEmail.setPrefWidth(300);

        //submit and cancel buttons
        Button submitButton = new Button("Submit");
        submitButton.setVisible(false);//initially not visible
        Button cancelButton = new Button("Cancel");
        cancelButton.setVisible(false);//also hidden

        // Style for submitButton
        String submitButtonStyle = "-fx-background-color: #00FFFFFF;" +
                "-fx-text-fill: black;" +
                "-fx-font-family: 'Arial';" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 2;" +
                "-fx-font-size: 14px;";

        // Style for cancelButton
        String cancelButtonStyle = "-fx-background-color: #00FFFFFF;" +
                "-fx-text-fill: red;" +
                "-fx-font-family: 'Arial';" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 2;" +
                "-fx-font-size: 14px;";

        // Apply the styles
        submitButton.setStyle(submitButtonStyle);
        cancelButton.setStyle(cancelButtonStyle);



        //Creating a HBox to hold the label ant the TextField together
        HBox invitesBox = new HBox(10);//spacing between elements in the HBox
        invitesBox.setAlignment(Pos.CENTER_LEFT); //Aligning items to the left
        //Add
        invitesBox.getChildren().addAll(invitesCheckbox, friendEmail, submitButton, cancelButton);

        //listener to checkbox
        invitesCheckbox.selectedProperty().addListener((observable, oldValue, newValue) ->{
            friendEmail.setVisible(newValue);
            submitButton.setVisible(newValue);
            cancelButton.setVisible(newValue);
        });

        centerLabel.setStyle(labelTextStyle);
        bibleVerseLabel.setStyle(labelTextStyle);
        invitesLabel.setStyle(labelTextStyle);

        // Add the label to the GridPane at position 1, 2 (column 1, row 2)
        gridPane.add(centerLabel, 1, 2);
        //bible verse below
        gridPane.add(bibleVerseLabel, 1, 3);
        //invites
        gridPane.add(invitesLabel, 1, 4);
        gridPane.add(invitesBox, 1, 5);


        // Set the GridPane to the center of the BorderPane
        this.setCenter(gridPane);
        //loading background image using getResourceAsStream
        try(InputStream is = getClass().getResourceAsStream("/goodimg.jpg")){
            if (is == null){
                throw new IllegalArgumentException("Image not found!");
            }
            //Enabling smooth property  for better scaling quality
            Image image =new Image(is, 0, 0, true, true);
            BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
            BackgroundImage backgroundImage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            gridPane.setBackground(new Background(backgroundImage));
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error loading image!");
        }

    }
}