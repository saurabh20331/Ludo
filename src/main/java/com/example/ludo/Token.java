package com.example.ludo;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Token implements Runnable{

    int pos;
    int movePos;
    boolean isHome;
    boolean isSafe;
    boolean isNearEnd;
    ImageView main;
    String color;
    ChangeTurn turn;
    Token(ImageView main, String color){
        this.main = main;
        isHome = true;
        isSafe = true;
        isNearEnd = false;
        this.color = color;
        if(Objects.equals(color, "blue"))
            pos = 0;
        else
            pos = 26;
    }
    void addTurn(ChangeTurn ct){
        this.turn = ct;
    }

    void enableDisable(boolean res){
        main.setDisable(res);
    }



    @Override
    public void run() {
        if(!main.isDisabled()){
            BoardCoordinates b = new BoardCoordinates();
            if(isHome){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        main.setLayoutX(b.blocks.get(pos).getKey());
                        main.setLayoutY(b.blocks.get(pos).getValue());
                    }
                });
                isHome = false;
            }
            else {
                for (int i = 0; i < movePos; ++i) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            main.setLayoutX(b.blocks.get(++pos).getKey());
                            main.setLayoutY(b.blocks.get(pos).getValue());
                        }
                    });
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(Objects.equals(this.color, "blue")){
                turn.enableDisable(false, true, false, false);
            }
            else
                turn.enableDisable(true, false, false, false);
        }
    }
}
