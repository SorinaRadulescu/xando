package com.lmpsri;

import java.util.ArrayList;
import java.util.Random;

public class Bot extends GameParticipant{

    public Bot(GameBoard board) {
        this.board = board;
        this.isPlayerOne = false;
        this.participantSelections = new ArrayList<>();
    }

    @Override
    public void placeSymbol() {
        Random random = new Random();
        int place = random.nextInt(9)+1;
        while (participantSelections.contains(place) || getOpponent().getParticipantSelections().contains(place)) { //repeat until the input is valid
            place = random.nextInt(9)+1;
        }
        participantSelections.add(place); //adding in a list player's inputs
        board.placeSymbol(place, '0');
    }
}
