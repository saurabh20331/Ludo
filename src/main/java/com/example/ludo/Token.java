package com.example.ludo;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Objects;

public class Token implements Runnable{

    private final double initialXPos;
    private final double initialYPos;
    private int pos;
    int movePos;
    private boolean isHome;
    private boolean isSafe;
    private boolean isWon;
    private boolean isNearEnd;
    private final ImageView main;
    private final String color;
    ChangeTurn turn;
    private final TranslateTransition transition;

    public String retColor(){
        return this.color;
    }

    public int retPos(){
        return this.pos;
    }
    public boolean retIsHome(){
        return this.isHome;
    }
    public boolean retIsNearEnd(){
        return this.isNearEnd;
    }
    public ImageView retMain(){
        return this.main;
    }

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

        transition = new TranslateTransition();
        transition.setNode(main);
        transition.setDuration(Duration.millis(500));
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setByY(-10);
        transition.setAutoReverse(true);
    }
    public void addTurn(ChangeTurn ct){
        this.turn = ct;
    }

    public void enableDisable(boolean res){
        main.setDisable(res);
    }

    public void playAnimation(){
        transition.play();
    }

    public void stopAnimation(){
        transition.jumpTo(Duration.ZERO);
        transition.stop();
    }


    @Override
    public void run() {
        if(!main.isDisabled()){
            turn.stopAllAnimations();
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
                        if(this.pos == this.turn.retP2().tokens[i].pos && !this.turn.retP2().tokens[i].isSafe){
                            int finalI = i;
                            int finalI1 = i;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    //System.out.println("YES");
                                    turn.retP2().tokens[finalI].main.setLayoutX(turn.retP2().tokens[finalI1].initialXPos);
                                    turn.retP2().tokens[finalI].main.setLayoutY(turn.retP2().tokens[finalI].initialYPos);
                                    turn.retP2().tokens[finalI].isHome = true;
                                    turn.retP2().tokens[finalI].isSafe = true;
                                    turn.retP2().tokens[finalI].pos = 26;
                                }
                            });
                        }
                    }
                }

                //Green token over blue token
                else{
                    for(int i=0; i<4; ++i){
                        if(this.pos == this.turn.retP1().tokens[i].pos && !this.turn.retP1().tokens[i].isSafe){
                            int finalI = i;
                            int finalI1 = i;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    //System.out.println("No");
                                    turn.retP1().tokens[finalI].main.setLayoutX(turn.retP1().tokens[finalI1].initialXPos);
                                    turn.retP1().tokens[finalI].main.setLayoutY(turn.retP1().tokens[finalI].initialYPos);
                                    turn.retP1().tokens[finalI].isHome = true;
                                    turn.retP1().tokens[finalI].isSafe = true;
                                    turn.retP1().tokens[finalI].pos = 0;
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
