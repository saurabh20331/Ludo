package com.example.ludo;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Token implements Runnable{

    double initialXPos;
    double initialYPos;
    int pos;
    int movePos;
    boolean isHome;
    boolean isSafe;
    boolean isWon;
    boolean isNearEnd;
    ImageView main;
    String color;
    ChangeTurn turn;

    Token(ImageView main, String color, double initialPos, double initialYPos){
        this.main = main;
        isHome = true;
        isSafe = true;
        isNearEnd = false;
        isWon = false;
        this.color = color;
        if(Objects.equals(color, "blue"))
            pos = 0;
        else
            pos = 26;
        this.initialXPos = initialPos;
        this.initialYPos = initialYPos;

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

            //Movement of token
            //At home
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

            //Not at home
            else {
                for (int i = 0; i < movePos; ++i) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            ++pos;
                            if(pos == 52)
                                pos = 0;
                            if(Objects.equals(color, "blue") && pos == 51 || Objects.equals(color, "green") && pos == 25){
                                pos = 0;
                                isNearEnd = true;
                            }
                            if(isNearEnd){
                                if(Objects.equals(color, "blue")){
                                    main.setLayoutX(b.blueWinning.get(pos).getKey());
                                    main.setLayoutY(b.blueWinning.get(pos).getValue());
                                }
                                else{
                                    main.setLayoutX(b.greenWinning.get(pos).getKey());
                                    main.setLayoutY(b.greenWinning.get(pos).getValue());
                                }
                                if(pos == 5)
                                    isWon = true;
                            }
                            else{
                                main.setLayoutX(b.blocks.get(pos).getKey());
                                main.setLayoutY(b.blocks.get(pos).getValue());
                            }
                        }
                    });
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isSafe = b.safePlaces.contains(pos);
                }


                //Blue token over Green token
                if(Objects.equals(this.color, "blue")){
                    for(int i=0; i<4; ++i){
                        if(this.pos == this.turn.p2.tokens[i].pos && !this.turn.p2.tokens[i].isSafe){
                            int finalI = i;
                            int finalI1 = i;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    //System.out.println("YES");
                                    turn.p2.tokens[finalI].main.setLayoutX(turn.p2.tokens[finalI1].initialXPos);
                                    turn.p2.tokens[finalI].main.setLayoutY(turn.p2.tokens[finalI].initialYPos);
                                    turn.p2.tokens[finalI].isHome = true;
                                    turn.p2.tokens[finalI].isSafe = true;
                                    turn.p2.tokens[finalI].pos = 26;
                                }
                            });
                        }
                    }
                }

                //Green token over blue token
                else{
                    for(int i=0; i<4; ++i){
                        if(this.pos == this.turn.p1.tokens[i].pos && !this.turn.p1.tokens[i].isSafe){
                            int finalI = i;
                            int finalI1 = i;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    //System.out.println("No");
                                    turn.p1.tokens[finalI].main.setLayoutX(turn.p1.tokens[finalI1].initialXPos);
                                    turn.p1.tokens[finalI].main.setLayoutY(turn.p1.tokens[finalI].initialYPos);
                                    turn.p1.tokens[finalI].isHome = true;
                                    turn.p1.tokens[finalI].isSafe = true;
                                    turn.p1.tokens[finalI].pos = 0;
                                }
                            });
                        }
                    }
                }
            }

            //Passing of turn
            if(Objects.equals(this.color, "blue")){
                if(movePos == 6)
                    turn.enableDisable(true, false, false, false);
                else
                    turn.enableDisable(false, true, false, false);

            }
            else {
                if(movePos == 6)
                    turn.enableDisable(false, true, false, false);
                else
                    turn.enableDisable(true, false, false, false);
            }
        }
    }
}
