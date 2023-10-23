package com.lmpsri;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player extends GameParticipant{

    private String name;

    public Player(String name, boolean isPlayerOne, GameBoard board) {
        this.name = name;
        this.isPlayerOne = isPlayerOne;
        this.board = board;
        this.participantSelections = new ArrayList<>();
    }

    @Override
    public void placeSymbol() {
        System.out.println(name + "'s turn");
        Scanner scanner = new Scanner(System.in);
        int place = scanner.nextInt();
        while (place < 1 || place > 9 || participantSelections.contains(place) || getOpponent().getParticipantSelections().contains(place)) { //repeat until the input is valid
            System.out.println("Invalid choice, please re-enter");
            place = scanner.nextInt();
        }
        participantSelections.add(place); //adding in a list player's inputs
        char symbol = isPlayerOne ? 'X' : '0';
        board.placeSymbol(place, symbol);
    }
}
