package com.example.ludo;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class BoardCoordinates {

    int sizeOfBlock = 39;
    int initialX = 335;
    int initialY = 515;

    ArrayList<Pair<Integer, Integer>> blocks;
    ArrayList<Pair<Integer, Integer>> blueWinning;
    ArrayList<Pair<Integer, Integer>> greenWinning;


    void createBlocks(int times, int xVar, int yVar){
        for(int i=0; i<times; ++i){
            blocks.add(new Pair<>(blocks.get(blocks.size()-1).getKey() + xVar, blocks.get(blocks.size()-1).getValue() + yVar));
        }
    }

    BoardCoordinates(){
        blocks = new ArrayList<>();
        blueWinning = new ArrayList<>();
        greenWinning = new ArrayList<>();
        blocks.add(new Pair<>(initialX, initialY));

        createBlocks(4, 0, -sizeOfBlock);
        createBlocks(1, -sizeOfBlock, -sizeOfBlock);
        createBlocks(5, -sizeOfBlock, 0);
        createBlocks(2, 0, -sizeOfBlock);
        createBlocks(5, sizeOfBlock, 0);
        createBlocks(1, sizeOfBlock, -sizeOfBlock);
        createBlocks(5, 0, -sizeOfBlock);
        createBlocks(2, sizeOfBlock, 0);
        createBlocks(5, 0, sizeOfBlock);
        createBlocks(1, sizeOfBlock, sizeOfBlock);
        createBlocks(5, sizeOfBlock, 0);
        createBlocks(2, 0, sizeOfBlock);
        createBlocks(5, -sizeOfBlock, 0);
        createBlocks(1, -sizeOfBlock, sizeOfBlock);
        createBlocks(5, 0, sizeOfBlock);
        createBlocks(2, -sizeOfBlock, 0);

        blueWinning.add(new Pair<>(blocks.get(50).getKey(), blocks.get(50).getValue() - sizeOfBlock));
        for(int i=0; i<5; ++i){
            blueWinning.add(new Pair<>(blueWinning.get(blueWinning.size()-1).getKey(), blueWinning.get(blueWinning.size()-1).getValue()-sizeOfBlock));
        }

        greenWinning.add(new Pair<>(blocks.get(24).getKey(), blocks.get(24).getValue() + sizeOfBlock));
        for(int i=0; i<5; ++i){
            greenWinning.add(new Pair<>(greenWinning.get(greenWinning.size()-1).getKey(), greenWinning.get(greenWinning.size()-1).getValue()+sizeOfBlock));
        }

    }

    ArrayList<Integer> safePlaces = new ArrayList<Integer>(List.of(0, 8, 13, 21, 26, 34, 39, 47));

}