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
import java.sql.Connection;
import java.sql.PreparedStatement;
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
            JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
            return;
        }
        if(!pass1.equals(pass2)){
            JOptionPane.showMessageDialog(null, "Passwords do not Match!");
            return;
        }
        try(Connection conn = DatabaseUtil.getConnection()){
            if(conn != null){
                String sql = "INSERT INTO users2 (fullname, username, email, password) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stateM = conn.prepareStatement(sql)) {
                    stateM.setString(1, fname);
                    stateM.setString(2, user);
                    stateM.setString(3, mail);
                    stateM.setString(4, pass1); // Iwill remember the hashing
                    stateM.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Account created successfully");
                    try{
                        //after account creation success
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo1/Workspace.fxml"));
                        Parent workspaceView = loader.load();
                        Scene workspaceScene = new Scene(workspaceView, 800, 600); //setting the desired sizes

                        //Getting  the current stage from any control, i will use backToLogin hyperlink
                        Stage window = (Stage) backToLogin.getScene().getWindow();
                        window.setScene(workspaceScene);
                        window.show();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    Fname.setText("");
                    username.setText("");
                    email.setText("");
                    password1.setText("");
                    password2.setText("");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Failed to create account: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to connect to the database!");
            }

            } catch(SQLException e){
            JOptionPane.showMessageDialog(null,  "Database Connection Error: " + e.getMessage());
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
