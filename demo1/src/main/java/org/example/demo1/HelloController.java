package org.example.demo1;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Hyperlink createAccount;
    @FXML
    private void onHyperlinkClick(ActionEvent event){
        System.out.println("Hyperlink Clicked!");
        try{
            //Loading the FXML for the account creation view
            Parent accountCreationView = FXMLLoader.load(getClass().getResource("/org/example/demo1/AccountCreation.fxml"));
            Scene accountCreationScene = new Scene(accountCreationView);

            //Getting the current stage from the event source and set the new scene
            Stage window = (Stage) createAccount.getScene().getWindow();
            window.setScene(accountCreationScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}