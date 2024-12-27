package com.example.balatroremake.Model;

import com.example.balatroremake.enums.GameState;


public class GameLoop {

    private GameState gameState;

    public GameLoop() {
        this.gameState = GameState.lobby;
    }

    public void loop() {
        switch (gameState) {
            case lobby:

                break;
            case startGame:
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



    public GameState returnGameState() {
        return this.gameState;
    }

}
