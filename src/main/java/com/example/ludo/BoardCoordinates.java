package com.example.ludo;

import javafx.util.Pair;
import java.util.ArrayList;

public class BoardCoordinates {

    int sizeOfBlock = 39;
    int initialX = 335;
    int initialY = 515;

    ArrayList<Pair<Integer, Integer>> blocks;

    void createBlocks(int times, int xVar, int yVar){
        for(int i=0; i<times; ++i){
            blocks.add(new Pair<>(blocks.get(blocks.size()-1).getKey() + xVar, blocks.get(blocks.size()-1).getValue() + yVar));
        }
    }

    BoardCoordinates(){
        blocks = new ArrayList<>();
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

    }
}