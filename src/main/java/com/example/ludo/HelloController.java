package com.example.ludo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HelloController {

    @FXML
    private ImageView dice1;
    @FXML
    private ImageView dice2;

    @FXML
    private ImageView blue1;
    @FXML
    private ImageView blue2;
    @FXML
    private ImageView blue3;
    @FXML
    private ImageView blue4;

    @FXML
    private ImageView green1;
    @FXML
    private ImageView green2;
    @FXML
    private ImageView green3;
    @FXML
    private ImageView green4;

    Token[]  greenToken;
    Token[]  blueToken;
    Player greenPlayer;
    Player bluePlayer;
    Dice blueDice;
    Dice greenDice;


    public void dice1Clicked(MouseEvent mouseEvent) {
        Thread dice = new Thread(blueDice);
        dice.start();
    }

    public void dice2Clicked(MouseEvent mouseEvent) {
        Thread dice = new Thread(greenDice);
        dice.start();
    }

    public void initialize(){
        blueDice = new Dice(dice1);
        greenDice = new Dice(dice2);

        greenToken = new Token[4];
        blueToken = new Token[4];

        greenToken[0] = new Token(green1, "green");
        greenToken[1] = new Token(green2, "green");
        greenToken[2] = new Token(green3, "green");
        greenToken[3] = new Token(green4, "green");

        blueToken[0] = new Token(blue1, "blue");
        blueToken[1] = new Token(blue2, "blue");
        blueToken[2] = new Token(blue3, "blue");
        blueToken[3] = new Token(blue4, "blue");

        greenPlayer = new Player(greenToken);
        bluePlayer = new Player(blueToken);

        ChangeTurn turn = new ChangeTurn(blueDice, greenDice, bluePlayer, greenPlayer);
        turn.enableDisable(true, false, false, false);

        greenPlayer.turnAdd(turn);
        bluePlayer.turnAdd(turn);

        blueDice.playerAssociation(bluePlayer);
        greenDice.playerAssociation(greenPlayer);
    }

    public void blue1Clicked(MouseEvent mouseEvent) {
        Thread th = new Thread(blueToken[0]);
        th.start();
    }

    public void blue2Clicked(MouseEvent mouseEvent) {
        Thread th = new Thread(blueToken[1]);
        th.start();
    }

    public void blue3Clicked(MouseEvent mouseEvent) {
        Thread th = new Thread(blueToken[2]);
        th.start();
    }

    public void blue4Clicked(MouseEvent mouseEvent) {
        Thread th = new Thread(blueToken[3]);
        th.start();
    }

    public void green1Clicked(MouseEvent mouseEvent) {
        Thread th = new Thread(greenToken[0]);
        th.start();
    }

    public void green4Clicked(MouseEvent mouseEvent) {
        Thread th = new Thread(greenToken[3]);
        th.start();
    }

    public void green3Clicked(MouseEvent mouseEvent) {
        Thread th = new Thread(greenToken[2]);
        th.start();
    }

    public void green2Clicked(MouseEvent mouseEvent) {
        Thread th = new Thread(greenToken[1]);
        th.start();
    }
}
