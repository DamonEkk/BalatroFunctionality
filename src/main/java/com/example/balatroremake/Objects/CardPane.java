package com.example.balatroremake.Objects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CardPane extends StackPane {

    PlayCard card;
    int selected = 0;

    public CardPane(PlayCard card, int selected) {
        this.card = card;
        this.selected = selected;
    }

    public CardPane(PlayCard card) {
        this( card, 0 );
        VBox fullCard = new VBox();
        HBox spaceBox = new HBox();
        HBox middleBox = new HBox();
        HBox bottomBox = new HBox();
        VBox nameBox = new VBox();
        VBox suitBox = new VBox();
        VBox nameBox1 = new VBox();
        VBox suitBox1 = new VBox();

        Label midSpace = new Label();
        midSpace.setText(" ");
        middleBox.setPadding(new Insets(0, 35, 0, 35));

        spaceBox.setAlignment(Pos.TOP_LEFT);
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);


        Label nameLabel = new Label();
        Label bottomNameLabel = new Label();
        if (card.cardValue != 0) {
            nameLabel.setText(String.valueOf(card.cardValue));
            nameLabel.setStyle("-fx-font-size: 18; -fx-text-fill: black;");


            bottomNameLabel.setText(String.valueOf(card.cardValue));
            bottomNameLabel.setStyle("-fx-font-size: 18; -fx-text-fill: black;");

        }
        else{
            nameLabel.setText(card.faceCard);
            nameLabel.setStyle("-fx-text-fill: black;");

            bottomNameLabel.setText(card.faceCard);
            bottomNameLabel.setStyle("-fx-text-fill: black;");
        }
        nameBox.getChildren().addAll(nameLabel);
        nameBox1.getChildren().addAll(bottomNameLabel);

        Label suitLabel = new Label();
        Label suitLabelBottom = new Label();
        suitLabel.setText(card.description);
        suitLabelBottom.setText(card.description);


        if (card.description.equals("♥") || card.description.equals("♦")){
            suitLabel.setStyle("-fx-font-size: 18; -fx-text-fill: red;");
            suitLabelBottom.setStyle("-fx-font-size: 18; -fx-text-fill: red;");


        }
        else{
            suitLabel.setStyle("-fx-font-size: 18; -fx-text-fill: black;");
            suitLabelBottom.setStyle("-fx-font-size: 18; -fx-text-fill: black;");
        }

        suitBox.getChildren().addAll(suitLabel);
        suitBox1.getChildren().addAll(suitLabelBottom);

        fullCard.setSpacing(30);

        spaceBox.getChildren().addAll(suitBox, nameBox);
        bottomBox.getChildren().addAll(nameBox1, suitBox1);
        middleBox.getChildren().addAll(midSpace);
        fullCard.getChildren().addAll(spaceBox, middleBox, bottomBox);

        fullCard.setStyle("-fx-border-color: black;");
        getChildren().addAll(fullCard);

        fullCard.setOnMouseClicked(e -> {
            if (this.selected == 0){
                middleBox.setPadding(new Insets(40, 70, 40, 70));
                selected = 1;
            }
        });

    }
}
