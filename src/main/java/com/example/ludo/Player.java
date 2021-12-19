package com.example.ludo;

import java.util.Objects;

public class Player {

    Token[] tokens;
    int diceNo;
    ChangeTurn turn;

    Player(Token[] token){
        tokens = new Token[4];
        System.arraycopy(token, 0, this.tokens, 0, 4);
    }

    void turnAdd(ChangeTurn ct){
        this.turn = ct;
        for (int i=0; i<4; ++i){
            tokens[i].turn = ct;
        }
    }

    void play(int num){

        this.diceNo = num;

        turn.enableDisable(false, false, false, false);
        int numOfDisabled = 0;
        for(int i=0; i<4; ++i){
            tokens[i].movePos = diceNo;
            if(!tokens[i].isNearEnd && diceNo == 6){
                tokens[i].enableDisable(false);
            }
            else if(tokens[i].isHome && diceNo != 6) {
                tokens[i].enableDisable(true);
                ++numOfDisabled;
            }
            else if(tokens[i].isNearEnd && 5-tokens[i].pos < num){
                tokens[i].enableDisable(true);
                ++numOfDisabled;
            }
            else{
                tokens[i].enableDisable(false);
            }
        }
        if(numOfDisabled == 4){
            if(Objects.equals(this.tokens[0].color, "blue")){
                turn.enableDisable(false, true, false, false);
            }
            else{
                turn.enableDisable(true, false, false, false);
            }

        }

    }

}
