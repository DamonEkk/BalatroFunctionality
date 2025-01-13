package com.example.balatroremake.Scenes;

import com.example.balatroremake.MainApplication;
import com.example.balatroremake.Model.GameLoop;
import com.example.balatroremake.enums.GameState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LobbyController {
    @FXML
    private Label titleText;

    static GameState gameState;

    @FXML
    private Button startButton;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        Stage stage = (Stage) startButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("Settings");
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.setScene(scene);
    }

    public static void setState(GameState gameLoop) {


    }

}