package org.example.demo1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class Creationcontroller {
    @FXML
    private Hyperlink backToLogin;
    @FXML
    private void onHyperlinkClick(ActionEvent event){
        System.out.println("backToLogin Link Pressed!");
        try {
            //Loading the FXML for the going back tologin page
            Parent LoginView = FXMLLoader.load(getClass().getResource("/org/example/demo1/hello-view.fxml"));
            Scene LoginScene = new Scene(LoginView);

            //Getting the current stage from the event source and set the new scene
            Stage window = (Stage) backToLogin.getScene().getWindow();
            window.setScene(LoginScene);
        } catch(IOException e){
            e.printStackTrace();

        }
    }
    @FXML
    private TextField Fname;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;

    @FXML
    private void onClickButtonSubmit(ActionEvent event){
        System.out.println("Submit button clicked!");
    }

    @FXML
    private void onClickButtonClear(ActionEvent event){
        System.out.println("Clear Button clicked!");
        Fname.setText("");
        username.setText("");
        email.setText("");
        password1.setText("");
        password2.setText("");
    }


}
