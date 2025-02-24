package com.example.balatroremake.Objects;

import com.example.balatroremake.Scenes.GameScreenController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicReference;

import static java.lang.System.currentTimeMillis;

public class CardPane extends StackPane {

    private double offsetX;
    private double offsetY;
    private long timeInitated;
    private double oldX = 0;
    private Timeline debounce = new Timeline();

    @FXML
    private HBox topCard;

    PlayCard card;
    int selected = 0;
    private int xPos = 0;
    private double oldMousePos;

    public CardPane(PlayCard card, int selected, int xPos) {
        this.card = card;
        this.selected = selected;
        this.xPos = xPos;
    }

    public CardPane(PlayCard card) {
        this(card, 0, GameScreenController.getInstance().getXPosition());
        System.out.println("card positoning = "+this.xPos);
        VBox fullCard = new VBox();
        HBox spaceBox = new HBox();
        HBox middleBox = new HBox();
        HBox bottomBox = new HBox();
        VBox nameBox = new VBox();
        VBox suitBox = new VBox();
        VBox nameBox1 = new VBox();
        VBox suitBox1 = new VBox();
        this.setStyle("-fx-background-color: #fffaf0");

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
        Rotate rotation = new Rotate();


        this.setOnMouseReleased(e -> {
            boolean dragged = false;

            if (currentTimeMillis() - timeInitated >= 125) { // checks to see if time elapsed is more than .25 of a second
                dragged = true;
            }

            if (this.selected == 0 && GameScreenController.selectedHand <= 4 && !dragged) {
                this.setTranslateY(-20);
                System.out.println(this.xPos);
                GameScreenController.selectedHand++;
                GameScreenController.selectedCards.add(this);
                selected = 1;
            }

            else if (this.selected == 1) {
                this.setTranslateY(0);
                GameScreenController.selectedHand--;
                GameScreenController.selectedCards.remove(this);
                selected = 0;
            }

            else if (dragged && selected == 0) {
                GameScreenController.getInstance().sortPositions();
                Timeline timeline = new Timeline(
                        new KeyFrame( // smooth return feel
                                Duration.millis(200),
                                new KeyValue(this.translateXProperty(), 0, Interpolator.EASE_BOTH),
                                new KeyValue(this.translateYProperty(), 0, Interpolator.EASE_BOTH),
                                new KeyValue(rotation.angleProperty(), 0, Interpolator.EASE_BOTH)
                        )
                );
                timeline.play();
                this.setViewOrder(0.0);
            }
        });


        this.setOnMousePressed(event ->{
            this.setViewOrder(-1.0);
            oldMousePos = 0;
            if (selected == 0){
                timeInitated = currentTimeMillis(); // records time of click, used to check time between letting go.
                offsetX = event.getSceneX() - this.getBoundsInParent().getMinX(); //gets offset values for the card positions.
                offsetY = event.getSceneY() - this.getBoundsInParent().getMinY();
            }
        });


        this.setOnMouseDragged(event -> {

            if (selected == 0 && currentTimeMillis() - timeInitated >= 125) {
                double newX = event.getSceneX() - offsetX; // gets the card coordinates based of mouse positon.
                double newY = event.getSceneY() - offsetY;
                double smoothing = 0.02;
                double currentAngle = rotation.getAngle();

                this.setTranslateX(newX - this.getLayoutX()); //sets card to mouse location.
                this.setTranslateY(newY - this.getLayoutY());
                rotation.setPivotX(0);

                double direction = event.getSceneX() - oldX;
                double fixedDirection = direction * 15.5;
                double smoothedAngle = currentAngle + (fixedDirection - currentAngle) * smoothing;

                if ((smoothedAngle <= 180 && smoothedAngle >= -180) && ((smoothedAngle >= 0.2) || (smoothedAngle <= -0.2))){
                    rotation.setAngle(smoothedAngle);
                }
                else if (newX > 0.2 || newX < -0.2){
                    rotation.setAngle(0);
                }

                oldX = event.getSceneX();
                this.getTransforms().setAll(rotation);
                cardPositionChange(newX);
            }
        });
    }


    public void cardPositionChange(double newX){
        double adjacentCard;
        int currentX = this.getxPos();

        if (newX > oldMousePos && currentX != GameScreenController.hand.size() - 1) {
            adjacentCard = GameScreenController.getInstance().getAdjacentPosition(currentX + 1);

            if (newX > adjacentCard){
                GameScreenController.getInstance().switchPosition(currentX, currentX + 1); // switches xpos with each other RIGHT
            }
        }

        else if (newX < oldMousePos && currentX > 0) {
            adjacentCard = GameScreenController.getInstance().getAdjacentPosition(currentX - 1);

            if (newX < adjacentCard){
                GameScreenController.getInstance().switchPosition(currentX - 1, currentX); // LEFT

            }
        }
        oldMousePos = newX;
    }


    public void setSelected(int selected) {
        this.selected = selected;
    }

    public PlayCard getCard(){
        return card;
    }


    public int getxPos(){
        return xPos;
    }


    public void setxPos(int xPos) {
        this.xPos = xPos;
    }
}
