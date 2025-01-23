package com.example.balatroremake.Scenes;

import com.example.balatroremake.Objects.CardPane;
import com.example.balatroremake.Objects.PlayCard;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.*;

public class GameScreenController {

    public List<PlayCard> allCards;
    public List<PlayCard> usedCards = new ArrayList<>();
    public List<CardPane> hand = new ArrayList<>();
    public static List<CardPane> selectedCards = new ArrayList<>();

    public static int selectedHand = 0;

    int handCompacity = 7;

    @FXML
    private HBox playedCardsVBox;

    @FXML
    private HBox cardVBox;

    @FXML
    private Button generateButton;

    public void initialize() {
        PlayCard[] tempList = PlayCard.getAllCards();
        allCards = new ArrayList<>(Arrays.asList(tempList));
        generate();
    }

    // This generates the cards from allCards
    public void generate(){
        if (allCards.size() == 0) {
            // run game over
        }

        while (allCards.size() >= 0 && hand.size() <= handCompacity) {
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

    public void onDiscardAction() {
        Iterator<CardPane> iterator = selectedCards.iterator(); // Create itereator
        while (iterator.hasNext()) {
            CardPane cardPane = iterator.next();
            try{
                hand.remove(cardPane); // remove cardPane from hand list
                cardVBox.getChildren().remove(cardPane); // remove cardPane from the cardVBox which displays the cards
                selectedHand = 0; // resets the limit on selected cards
                iterator.remove(); // removes cardPane from selectedCards list.
                generate(); // regenerate cards to hand.

            }
            catch (Exception e){
                System.out.println("Tried removing " + cardPane);
                for (CardPane card : hand) {
                    System.out.println(card);
                }
            }
        }

    }

    public void onPlayHandAction() {
        Iterator<CardPane> iterator = selectedCards.iterator(); // Create itereator
        while (iterator.hasNext()) {
            CardPane cardPane = iterator.next();
            try{
                hand.remove(cardPane); // remove cardPane from hand list
                cardVBox.getChildren().remove(cardPane); // remove cardPane from the cardVBox which displays the cards

                // disable card properties
                cardPane.setSelected(3);
                cardPane.setTranslateY(0);

                playedCardsVBox.getChildren().add(cardPane);
                selectedHand = 0; // resets the limit on selected cards
                iterator.remove(); // removes cardPane from selectedCards list.
                generate(); // regenerate cards to hand.

            }
            catch (Exception e){
                System.out.println("Tried removing " + cardPane);
                for (CardPane card : hand) {
                    System.out.println(card);
                }
            }
        }

    }
}
