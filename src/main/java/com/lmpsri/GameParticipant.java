package com.lmpsri;

import java.util.List;

public abstract class GameParticipant {

    protected GameBoard board;
    protected boolean isPlayerOne;
    protected List<Integer> participantSelections;
    abstract void placeSymbol();

    public List<Integer> getParticipantSelections() { return participantSelections; }

    public GameParticipant getOpponent() {
        if(isPlayerOne) {
            return board.getPlayerTwo();
        }
        return board.getPlayerOne();
    }
}
