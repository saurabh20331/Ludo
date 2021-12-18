package com.example.ludo;

public class ChangeTurn {

    Dice d1;
    Dice d2;
    Player p1;
    Player p2;

    ChangeTurn(Dice d1, Dice d2, Player p1, Player p2){
        this.d1 = d1;
        this.d2 = d2;
        this.p1 = p1;
        this.p2 = p2;
    }

    void enableDisable(boolean r1, boolean r2, boolean r3, boolean r4){

        d1.dice.setDisable(!r1);
        d2.dice.setDisable(!r2);
        for(int i=0; i<4; ++i){
            p1.tokens[i].main.setDisable(!r3);
        }
        for(int i=0; i<4; ++i){
            p2.tokens[i].main.setDisable(!r4);
        }
    }
}