package com.example.balatroremake.Objects;

import com.example.balatroremake.enums.SpecialJoker;

public class JokerCard  extends Card{

    SpecialJoker specialJoker;
    String power;
    int mult;
    int coins;
    float multiplier; // set to none unless card will alter per something.
    public JokerCard(String name, String description, SpecialJoker specialJoker, String power, int mult, int coins) {
        super(name, description);
        this.specialJoker = specialJoker;
        this.power = power;
        this.mult = mult;
        this.coins = coins;
        this.multiplier = mult;
    }
}
