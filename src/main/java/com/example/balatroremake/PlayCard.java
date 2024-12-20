package com.example.balatroremake;

import com.example.balatroremake.enums.Special;



public class PlayCard extends Card{
    static PlayCard[] allCards = new PlayCard[52]; // This should be a list to accomodate later stages in the games and different decks.
    static String[] suits = {"heart", "diamond", "club", "spade"};

    String faceCard;
    int cardValue;
    Special attribute;
    public PlayCard(String name, String description, String faceCard, int cardValue) {
        super(name, description);
        this.faceCard = faceCard;
        this.cardValue = cardValue;
    }

    // initilizing card objects
    public static void loadCards(){

        String name = "";
        String description = "";
        String faceCard;
        int totalCards = 0;

        for (int i = 0; i < 4; i++) {
            description = suits[i];
            for (int j = 2; j <= 14; j++) {
                PlayCard card;
                if (j == 11){
                    faceCard = "JACK";
                }
                else if (j == 12){
                    faceCard = "QUEEN";
                }
                else if (j == 13){
                    faceCard = "KING";
                }
                else if (j == 14){
                    faceCard = "ACE";
                }
                else {
                    faceCard = "NONE";
                }

                if (faceCard.equals("NONE")){
                    name = description + "_" + j;
                    card = new PlayCard(name, description, faceCard, j);
                }
                else {
                    name = description + "_" + faceCard;
                    card = new PlayCard(name, description, faceCard, 0);
                }

                allCards[totalCards] = card;
                totalCards++;
            }
        }

        for (PlayCard card : allCards) {
            System.out.println(card.name + " " + card.description + " " + card.faceCard + " " + card.cardValue);
        }

    }

    // for player hand and original deck to pull extra cards
    public PlayCard pickRandomCard(PlayCard[] deck){
        PlayCard card = null;
        return card;
    }

    // Apply special values.
    public PlayCard applyAttribute(PlayCard card){
        return card;
    }
}
