
package edu.uic.cs342.poker.controller;

import edu.uic.cs342.poker.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private void handleStartButton() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource(
                        "/edu/uic/cs342/poker/fxml/game.fxml"
                )
        );

        Parent root = loader.load();

        Stage stage = (Stage) welcomeLabel
                .getScene()
                .getWindow();

        Scene scene = new Scene(root, 750, 500);

        scene.getStylesheets().add(
                Main.class.getResource(
                        "/edu/uic/cs342/poker/css/style.css"
                ).toExternalForm()
        );

        stage.setTitle("Three Card Poker");
        stage.setScene(scene);
        stage.show();
    }
}
