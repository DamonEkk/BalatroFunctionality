package com.example.balatroremake.Model;

import com.example.balatroremake.MainApplication;
import com.example.balatroremake.enums.GameState;
import com.example.balatroremake.Scenes.LobbyController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class GameLoop {

    private GameState gameState;

    public GameLoop() {
        this.gameState = GameState.intro;
    }

    public void loop() throws IOException {
        switch (gameState) {

            case intro:
                LobbyController.setState(this.gameState);
                break;
            case lobby:

                break;
            case startGame:
                System.out.println("Starting game loop");
//                Stage stage = (Stage) settingsButton.getScene().getWindow();
//                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("settings-view.fxml"));
//                Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
//                stage.setTitle("Settings");
//                stage.setWidth(1080);
//                stage.setHeight(1920);
//                stage.setScene(scene);
                break;

            case selectLevel:
                break;

            case roundStart:
                break;

            case selectCards:
                break;

            case playGame:
                break;

            case roundWin:
                break;

            case roundLose:
                break;

            case skipRound:
                break;

            case shop:
                break;

            case winGame:
                break;

            case loseGame:
                break;

            case endless:
                break;

                default:
                    this.gameState = GameState.lobby;
                    break;
        }
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState returnGameState() {
        return this.gameState;
    }

}
