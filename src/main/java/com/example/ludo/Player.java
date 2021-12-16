package com.example.ludo;

import javafx.application.Platform;

public class Player {

    Token[] tokens;
    int diceNo;

    Player(Token[] token){
        tokens = new Token[4];
        System.arraycopy(token, 0, this.tokens, 0, 4);
    }

    void play(int num){

        this.diceNo = num;

        for(int i=0; i<4; ++i){

            if(!tokens[i].isNearEnd && diceNo == 6){
                tokens[i].enableDisable(false);
            }

            else if(tokens[i].isHome && diceNo != 6) {
                tokens[i].enableDisable(true);
            }

            //nearEnd

            else{
                tokens[i].enableDisable(false);
            }

            if(!tokens[i].main.isDisabled()){
                int finalI = i;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        tokens[finalI].main.setTranslateX(123);
                    }
                });
            }

        }
    }

}
