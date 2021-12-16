package com.example.ludo;

import javafx.scene.image.ImageView;

public class Token {

    int pos;
    boolean isHome;
    boolean isSafe;
    boolean isNearEnd;
    ImageView main;
    Token(ImageView main){
        this.main = main;
        isHome = true;
        isSafe = true;
        isNearEnd = false;
    }

    void enableDisable(boolean res){
        main.setDisable(res);
    }

}
