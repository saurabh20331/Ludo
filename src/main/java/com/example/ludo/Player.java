package com.example.ludo;

import java.util.Objects;

public class Player {

    Token[] tokens;
    private ChangeTurn turn;

    Player(Token[] token){
        tokens = new Token[4];
        System.arraycopy(token, 0, this.tokens, 0, 4);
    }

    public void turnAdd(ChangeTurn ct){
        this.turn = ct;
        for (int i=0; i<4; ++i){
            tokens[i].turn = ct;
        }
    }

    public void play(int num){

        int autoMov = -1;
        turn.enableDisable(false, false, false, false);
        int numOfDisabled = 0;
        for(int i=0; i<4; ++i){
            tokens[i].movePos = num;
            if(!tokens[i].retIsNearEnd() && num == 6){
                tokens[i].enableDisable(false);
                autoMov = i;
            }
            else if(tokens[i].retIsHome() && num != 6) {
                tokens[i].enableDisable(true);
                ++numOfDisabled;
            }
            else if(tokens[i].retIsNearEnd() && 5-tokens[i].retPos() < num){
                tokens[i].enableDisable(true);
                ++numOfDisabled;
            }
            else{
                tokens[i].enableDisable(false);
                autoMov = i;
            }
            if(!tokens[i].retMain().isDisabled())
                tokens[i].playAnimation();
        }
        if(numOfDisabled == 4){
            if(Objects.equals(this.tokens[0].retColor(), "blue")){
                turn.enableDisable(false, true, false, false);
            }
            else{
                turn.enableDisable(true, false, false, false);
            }
        }
        else if(numOfDisabled == 3){
            Thread th = new Thread(tokens[autoMov]);
            th.start();
        }

    }

}
