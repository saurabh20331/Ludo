package com.example.ludo;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainController {

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

    private Token[]  greenToken;
    private Token[]  blueToken;
    private Dice blueDice;
    private Dice greenDice;


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

        greenToken[0] = new Token(green1, "green", green1.getLayoutX(), green1.getLayoutY());
        greenToken[1] = new Token(green2, "green", green2.getLayoutX(), green2.getLayoutY());
        greenToken[2] = new Token(green3, "green", green3.getLayoutX(), green3.getLayoutY());
        greenToken[3] = new Token(green4, "green", green4.getLayoutX(), green4.getLayoutY());

        blueToken[0] = new Token(blue1, "blue", blue1.getLayoutX(), blue1.getLayoutY());
        blueToken[1] = new Token(blue2, "blue", blue2.getLayoutX(), blue2.getLayoutY());
        blueToken[2] = new Token(blue3, "blue", blue3.getLayoutX(), blue3.getLayoutY());
        blueToken[3] = new Token(blue4, "blue", blue4.getLayoutX(), blue4.getLayoutY());

        Player greenPlayer = new Player(greenToken);
        Player bluePlayer = new Player(blueToken);

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
