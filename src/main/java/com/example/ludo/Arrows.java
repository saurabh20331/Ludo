package com.example.ludo;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Arrows {

    ImageView up;
    ImageView down;
    TranslateTransition transitionUp;
    TranslateTransition transitionDown;

    Arrows(ImageView img1, ImageView img2){
        this.up = img1;
        this.down = img2;

        transitionUp = new TranslateTransition();
        transitionUp.setNode(this.up);
        transitionUp.setDuration(Duration.millis(500));
        transitionUp.setCycleCount(TranslateTransition.INDEFINITE);
        transitionUp.setByY(-10);
        transitionUp.setAutoReverse(true);

        transitionDown = new TranslateTransition();
        transitionDown.setNode(this.down);
        transitionDown.setDuration(Duration.millis(500));
        transitionDown.setCycleCount(TranslateTransition.INDEFINITE);
        transitionDown.setByY(-10);
        transitionDown.setAutoReverse(true);


    }
    void startUpAnimation(){
        up.setVisible(true);
        transitionUp.play();
    }

    void stopUpAnimation(){
        transitionUp.jumpTo(Duration.ZERO);
        up.setVisible(false);
        transitionUp.stop();
    }

    void startDownAnimation(){
        down.setVisible(true);
        transitionDown.play();
    }

    void stopDownAnimation(){
        transitionDown.jumpTo(Duration.ZERO);
        down.setVisible(false);
        transitionDown.stop();
    }



}
