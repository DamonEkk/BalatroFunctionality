package com.example.balatroremake.Scenes;

import com.example.balatroremake.Objects.CardPane;
import com.example.balatroremake.Objects.PlayCard;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.*;

public class GameScreenController {

    public List<PlayCard> allCards;
    public List<PlayCard> usedCards = new ArrayList<>();
    public static List<CardPane> hand = new ArrayList<>();
    public static List<CardPane> selectedCards = new ArrayList<>();
    public static List<CardPane> sortedCards = new ArrayList<>();
    public static List<Integer> discardingPositions = new ArrayList<>();

    public static int selectedHand = 0;
    public static int xPosition = 0;
    public static boolean positionFlag = false;

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

    private static GameScreenController instance;

    public GameScreenController() {
        instance = this;
    }

    public static GameScreenController getInstance() {
        return instance;
    }

    // This generates the cards from allCards
    public void generate(){
        if (allCards.size() == 0) {
            // run game over
        }

        while (allCards.size() > 0 && hand.size() <= handCompacity) {
            if (discardingPositions.size() > 0) {
                xPosition = discardingPositions.get(0);
                discardingPositions.remove(0);
                positionFlag = true;
            }

            int cardIndex = new Random().nextInt(allCards.size());

            PlayCard playCard = allCards.get(cardIndex);

            usedCards.add(playCard);
            allCards.remove(playCard);


            CardPane cardGraphic = new CardPane(playCard);
            cardVBox.getChildren().add(cardGraphic);
            hand.add(cardGraphic);
        }

        sortPositions(); // sorts all cards so new cards added replacd their old counterpart
    }

    public void onDiscardAction() {
        Iterator<CardPane> iterator = selectedCards.iterator(); // Create itereator
        while (iterator.hasNext()) {
            CardPane cardPane = iterator.next();
            try{
                discardingPositions.add(cardPane.getxPos()); // used to reproduce old xPos values.
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
                discardingPositions.add(cardPane.getxPos());

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
                System.out.println(e);
                System.out.println("Tried removing " + cardPane);
                for (CardPane card : hand) {
                    System.out.println(card);
                }
            }
        }
    }

    public int getXPosition(){
        if (positionFlag){
            positionFlag = false; // Flag is used to prevent a higher position than cards in hand.
            return xPosition;
        }
        xPosition++;
        return xPosition - 1; // used when generating the first cards.
    }

    public void sortPositions() {
        hand.sort(Comparator.comparingInt(CardPane::getxPos));

        cardVBox.getChildren().clear();
        cardVBox.getChildren().addAll(hand);

        //cardVBox.layout();
        for (CardPane card : hand) {
            card.setTranslateX(0);
        }
    }

    public double getAdjacentPosition(int cardIndex) {
        return hand.get(cardIndex).getLayoutX();
    }

    public void switchPosition(int activeIndex, int adjacentIndex) {

        hand.get(adjacentIndex).setTranslateX(hand.get(adjacentIndex).getTranslateX() -156);
        hand.get(activeIndex).setTranslateX(hand.get(activeIndex).getTranslateX() +156);

        hand.get(activeIndex).setxPos(adjacentIndex);
        hand.get(adjacentIndex).setxPos(activeIndex);
        Collections.swap(hand, activeIndex, adjacentIndex);

    }
}
