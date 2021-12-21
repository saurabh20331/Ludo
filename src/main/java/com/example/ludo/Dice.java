package com.example.ludo;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Dice implements Runnable{

    private final ImageView dice;
    Arrows arrows;
    public ImageView retDice(){
        return dice;
    }

    Dice(ImageView dice){
        this.dice = dice;
    }
    private Player player;
    public void playerAssociation(Player p1){
        this.player = p1;
    }

    public void addArrow(Arrows arrow){
        this.arrows = arrow;
    }

    @Override
    public void run() {
        arrows.stopUpAnimation();
        arrows.startDownAnimation();
        Random rand = new Random();
        int num = rand.nextInt(6) + 1;
        FileInputStream[] inputStream = new FileInputStream[6];

        for(int i=0; i<6; ++i){
            int j = i+1;
            String str = "src/main/resources/com/example/ludo/dice"+ j + ".png";
            try {
                inputStream[i] = new FileInputStream(str);
            } catch (FileNotFoundException e) {
                System.out.println("Dice" + i + " not Found");
            }
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                assert inputStream[num-1] != null;
                Image image = new Image(inputStream[num-1]);
                dice.setImage(image);
            }
        });


        player.play(num);
    }
}
