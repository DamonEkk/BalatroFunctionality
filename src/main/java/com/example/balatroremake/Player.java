package com.example.balatroremake;

public class Player {

    private static Player instance;

    int hands;
    int discards;
    int handSize;
    int jokerHandSize;
    int cash;
    int ante;
    float chips;
    float mult;

    public Player(int hands, int discards, int handSize, int jokerHandSize, int cash, int ante, float chips, float mult ) {
        this.hands = hands;
        this.discards = discards;
        this.handSize = handSize;
        this.jokerHandSize = jokerHandSize;
        this.cash = cash;
        this.ante = ante;
        this.chips = chips;
        this.mult = mult;
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player(0, 0, 0, 0, 0, 0, 0, 0);
        }
        return instance;
    }
}
