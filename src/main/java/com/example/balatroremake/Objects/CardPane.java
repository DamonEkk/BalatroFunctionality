package com.example.balatroremake.Objects;

import com.example.balatroremake.Scenes.GameScreenController;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicReference;

import static java.lang.System.currentTimeMillis;

public class CardPane extends StackPane {

    private double offsetX;
    private double offsetY;
    private long timeInitated;

    @FXML
    private HBox topCard;

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
        middleBox.setPadding(new Insets(75, 70, 75, 70));

        spaceBox.setAlignment(Pos.TOP_LEFT);
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);


        Label nameLabel = new Label();
        Label bottomNameLabel = new Label();
        if (card.cardValue != 0) {
            nameLabel.setText(String.valueOf(card.cardValue));
            nameLabel.setStyle("-fx-font-size: 20; -fx-text-fill: black;");


            bottomNameLabel.setText(String.valueOf(card.cardValue));
            bottomNameLabel.setStyle("-fx-font-size: 20; -fx-text-fill: black;");

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
            suitLabel.setStyle("-fx-font-size: 20; -fx-text-fill: red;");
            suitLabelBottom.setStyle("-fx-font-size: 20; -fx-text-fill: red;");


        }
        else{
            suitLabel.setStyle("-fx-font-size: 20; -fx-text-fill: black;");
            suitLabelBottom.setStyle("-fx-font-size: 20; -fx-text-fill: black;");
        }

        suitBox.getChildren().addAll(suitLabel);
        suitBox1.getChildren().addAll(suitLabelBottom);



        spaceBox.getChildren().addAll(suitBox, nameBox);
        bottomBox.getChildren().addAll(nameBox1, suitBox1);
        middleBox.getChildren().addAll(midSpace);
        fullCard.getChildren().addAll(spaceBox, middleBox, bottomBox);

        fullCard.setStyle("-fx-border-color: black;");
        getChildren().addAll(fullCard);



        this.setOnMouseReleased(e -> {
            boolean dragged = false;
            if (System.currentTimeMillis() - timeInitated >= 125) { // checks to see if time elapsed is more than .25 of a second
                dragged = true;
            }

            if (this.selected == 0 && GameScreenController.selectedHand <= 4 && !dragged) {
//

                System.out.println(GameScreenController.selectedHand);
                this.setTranslateY(-20);
                GameScreenController.selectedHand++;
                GameScreenController.selectedCards.add(this);

                selected = 1;
            }
            else if (this.selected == 1) {
//
                this.setTranslateY(0);
                GameScreenController.selectedHand--;
                GameScreenController.selectedCards.remove(this);
                selected = 0;
            }

            else if (dragged && selected == 0) {
                this.setTranslateX(0); // reset the card positon.
                this.setTranslateY(0);
            }

        });

        this.setOnMousePressed(event ->{
            if (selected == 0){
                timeInitated = System.currentTimeMillis(); // records time of click, used to check time between letting go.
                offsetX = event.getSceneX() - this.getBoundsInParent().getMinX(); //gets offset values for the card positions.
                offsetY = event.getSceneY() - this.getBoundsInParent().getMinY();
            }
        });

        this.setOnMouseDragged(event -> {

            if (selected == 0 && System.currentTimeMillis() - timeInitated >= 125) {
                double newX = event.getSceneX() - offsetX; // gets the card coordinates based of mouse positon.
                double newY = event.getSceneY() - offsetY;

                this.setTranslateX(newX - this.getLayoutX()); //sets card to mouse location.
                this.setTranslateY(newY - this.getLayoutY());
            }
        });


    }

    public PlayCard getCard() {
        return card;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }
}
