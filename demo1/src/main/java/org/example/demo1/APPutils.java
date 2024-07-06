package org.example.demo1;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.util.Duration;

import static javafx.util.Duration.seconds;

public class APPutils {
    public static void ShowAlert(String headerText, String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);
        alert.show();

        //closing the alert after 3 seconds
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> alert.close());
        delay.play();

    }

}
