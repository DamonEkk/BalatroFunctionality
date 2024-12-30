package com.example.balatroremake.Scenes;

import com.example.balatroremake.Model.GameLoop;
import com.example.balatroremake.enums.GameState;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LobbyController {
    @FXML
    private Label titleText;

    static GameState gameState;

    @FXML
    protected void onHelloButtonClick() {
        gameState = GameState.startGame;
        GameLoop.setGameState(gameState);
    }

    public static void setState(GameState gameLoop) {
        gameState = gameLoop;

    }

}