package com.example.ludo;

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
        dice1.setDisable(false);
        dice2.setDisable(true);
        greenToken = new Token[4];
        blueToken = new Token[4];

        greenToken[0] = new Token(green1);
        greenToken[1] = new Token(green2);
        greenToken[2] = new Token(green3);
        greenToken[3] = new Token(green4);

        blueToken[0] = new Token(blue1);
        blueToken[1] = new Token(blue2);
        blueToken[2] = new Token(blue3);
        blueToken[3] = new Token(blue4);

        greenPlayer = new Player(greenToken);
        bluePlayer = new Player(blueToken);

        blueDice.playerAssociation(bluePlayer);
        greenDice.playerAssociation(greenPlayer);



    }


}