package com.example.ludo;

public class ChangeTurn {

    private final Dice d1;
    private final Dice d2;
    private final Player p1;
    private final Player p2;

    public Player retP1(){
        return p1;
    }
    public Player retP2(){
        return p2;
    }

    ChangeTurn(Dice d1, Dice d2, Player p1, Player p2){
        this.d1 = d1;
        this.d2 = d2;
        this.p1 = p1;
        this.p2 = p2;
    }

    public void enableDisable(boolean r1, boolean r2, boolean r3, boolean r4){

        d1.retDice().setDisable(!r1);
        d2.retDice().setDisable(!r2);
        for(int i=0; i<4; ++i){
            p1.tokens[i].retMain().setDisable(!r3);
        }
        for(int i=0; i<4; ++i){
            p2.tokens[i].retMain().setDisable(!r4);
        }

    }

    public void stopAllAnimations(){
        for(int i=0; i<4; ++i){
            p1.tokens[i].stopAnimation();
            p2.tokens[i].stopAnimation();
        }
    }
}
