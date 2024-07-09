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

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    private void onClickButtonSubmit(ActionEvent event) throws SQLException {
        System.out.println("Submit button clicked!");
        String fname = Fname.getText();
        String user = username.getText();
        String mail = email.getText();
        String pass1 = password1.getText();
        String pass2 = password2.getText();

        //Validation if not empty
        if(fname.isEmpty() || user.isEmpty() || mail.isEmpty() || pass1.isEmpty() || pass2.isEmpty()){
            APPutils.ShowAlert( "Incomplete Form", "Please fill in all required fields.");
            return;
        }
        if(!pass1.equals(pass2)){
            APPutils.ShowAlert("Invalid Credentials!", "Passwords do not match.");
            return;
        }
        try(Connection conn = DatabaseUtil.getConnection()){
            if(conn != null){

                //We first check if the email and username exists
                String checkSQL = "SELECT COUNT(*) FROM users2 WHERE username = ? OR email = ?";
                try (PreparedStatement checkStatement = conn.prepareStatement(checkSQL)){
                    checkStatement.setString(1, user);
                    checkStatement.setString(2, mail);
                    ResultSet rs = checkStatement.executeQuery();
                    if(rs.next() && rs.getInt(1) > 0){
                        APPutils.ShowAlert("Error!", "Email or username already exists.");
                        return;
                    }
                }

                //Insert new user if username or email are unique
                String sql = "INSERT INTO users2 (fullname, username, email, password) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stateM = conn.prepareStatement(sql)) {
                    stateM.setString(1, fname);
                    stateM.setString(2, user);
                    stateM.setString(3, mail);
                    stateM.setString(4, pass1); // I will remember the hashing
                    stateM.executeUpdate();
                    APPutils.ShowAlert("Success!", "Account created Successfully");
                    Workspace  workspaceView = new Workspace();
                    Scene workspaceScene = new Scene(workspaceView, 1200, 900);//setting desired sizes

                    //Getting the current stage from any control
                    Stage window = (Stage) backToLogin.getScene().getWindow();
                    window.setScene(workspaceScene);
                    window.show();
                    //clearing fields after successful creation of account
                    Fname.setText("");
                    username.setText("");
                    email.setText("");
                    password1.setText("");
                    password2.setText("");
                } catch (SQLException e) {
                    APPutils.ShowAlert("Account Creation Error!", "Failed to create Account." + e.getMessage());
                }
            } else {
                APPutils.ShowAlert("Error!" , "Failed to connect to database.");
            }

            } catch(SQLException e){
            APPutils.ShowAlert("Connection Error!", "Error Connecting to the database." + e.getMessage());

        }
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
