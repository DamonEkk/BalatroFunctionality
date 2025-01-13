package com.example.balatroremake.Scenes;

import com.example.balatroremake.Objects.CardPane;
import com.example.balatroremake.Objects.PlayCard;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameScreenController {

    public List<PlayCard> allCards;
    public List<PlayCard> usedCards = new ArrayList<>();
    public List<CardPane> hand = new ArrayList<>();

    @FXML
    private HBox cardVBox;

    @FXML
    private Button generateButton;

    public void initialize() {
        PlayCard[] tempList = PlayCard.getAllCards();
        allCards = new ArrayList<>(Arrays.asList(tempList));
    }

    public void generateButtonAction(){
        if (allCards.size() >= 0 && hand.size() <= 7) {
            int cardIndex = new Random().nextInt(allCards.size());
            System.out.println("cardIndex: " + cardIndex);

            PlayCard playCard = allCards.get(cardIndex);

            usedCards.add(playCard);
            allCards.remove(playCard);


            CardPane cardGraphic = new CardPane(playCard);
            cardVBox.getChildren().add(cardGraphic);
            hand.add(cardGraphic);
        }
    }
}
