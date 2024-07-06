package org.example.demo1;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    //Handling the submit button click
    @FXML
    private void onSubmitButtonClick(ActionEvent event){
        System.out.println("Submit button hit!");
        String user = username.getText();
        String pass = password.getText();

        if(user.isEmpty() || pass.isEmpty()){
            APPutils.ShowAlert("Login Error", "Username and Password cannot be empty.");
            return;
        }

        try(Connection conn = DatabaseUtil.getConnection()){
            if(conn != null){
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM users2 WHERE username = ? AND password = ?");
                statement.setString(1, user);
                statement.setString(2, pass);

                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    //Login Success
                    APPutils.ShowAlert("Success!", "Login Success.");
                    //Navigating to the next scene
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo1/Workspace.fxml"));
                        Parent workspaceView = loader.load();
                        Scene  workspaceScene = new Scene(workspaceView, 1200, 800);

                        //Getting current Stage
                        Stage window = (Stage) createAccount.getScene().getWindow();
                        window.setScene(workspaceScene);
                        window.show();
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                }else {
                    //Login Failure
                    APPutils.ShowAlert("Login Error!", "Invalid username or password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            APPutils.ShowAlert("Database Error!", "An error occurred while accessing the database.");
        }
    }
    @FXML
    private void onClearButtonClick(ActionEvent event){
        System.out.println("Clear Button hit!");
        username.setText("");
        password.setText("");
    }
}