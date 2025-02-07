package com.example.balatroremake.Model;

import com.example.balatroremake.Objects.CardPane;

import java.util.List;

public class CalculateHand {
    private static List<CardPane> selectedHand;
    private static int playFlag = 0X0;

    // chips | mult | usedAmount
    int[] pair = {3, 4, 0};
    int[] twoPair = {20, 2, 0};
    int[] threeKind = {30, 3, 0};
    int[] fourKind = {60, 7, 0};
    int[] fiveKind = {0, 0, 0};
    int[] fFiveKind = {0, 0, 0};
    int[] flush = {35, 4, 0};
    int[] straight = {30, 4, 0};
    int[] rFlush = {100, 8, 0};
    int[] highCard = {5, 1, 0};
    int[] fullHouse = {40, 4, 0};

    // flag is used to determine if the hand has been played or just selected.
    public static void getSelectionHand(List<CardPane> cardPane, int flag) {
        playFlag = 0X0;
        selectedHand.addAll(cardPane);
    }

    public static void getPlay(int flag){
        for (int i = 0; i < selectedHand.size(); i++) {
            selectedHand.get(i).getCard();

            //TODO
            //String encoding (forgot the name) 2 2 5 j 10 will become 22,5,j,t
            // count double
            // if no pair types move onto straight functionality where we check -1 chains after being quicksorted.
            // -1 value presents next card present a tick, whereas these "ticks" will be counted and at 5 (or however the game goes) is found straight is found
            // proceed to flush check regarless of straight.
            //
            // each check will trigger a bit in playFlag
            // so a pair will trigger, 3 of a kind will trigger, 4 and 5 and the highest bit will be counted.
            // straight will be signified by another bit
            // flush will be signified by another bit.
            // from there we can do checks for straights and 5 of a kind to see if its a flush.

        }
    }
}
