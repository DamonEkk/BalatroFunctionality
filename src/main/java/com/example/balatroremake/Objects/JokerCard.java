package com.example.balatroremake.Objects;

import com.example.balatroremake.MainApplication;
import com.example.balatroremake.enums.SpecialJoker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JokerCard  extends Card{

    SpecialJoker specialJoker;
    String power;
    int mult;
    int coins;
    float multiplier; // set to none unless card will alter per something.
    Image picture;
    public JokerCard(String name, String description, SpecialJoker specialJoker, String power, int mult, int coins) {
        super(name, description);
        this.specialJoker = specialJoker;
        this.power = power;
        this.mult = mult;
        this.coins = coins;
        this.multiplier = mult;
        this.picture = new Image(MainApplication.class.getResourceAsStream("/greenJoker.png"));
    }
}
