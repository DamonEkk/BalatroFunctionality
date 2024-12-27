package com.example.balatroremake.Objects;

public class Level {
    String special;
    String name;
    String tier; // like 1-3
    Boolean boss;
    int requiredCoins;
    String description;
    String limit;

    public Level(String special, String name, String tier, Boolean boss, int requiredCoins, String description, String limit) {
        this.special = special;
        this.name = name;
        this.tier = tier;
        this.boss = boss;
        this.requiredCoins = requiredCoins;
        this.description = description;
        this.limit = limit;
    }
}
