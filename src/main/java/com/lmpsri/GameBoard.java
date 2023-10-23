package com.lmpsri;

import java.util.List;
import java.util.Scanner;

public class GameBoard {
    private char[][] board = {
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '},
    };

    private GameParticipant playerOne;
    private GameParticipant playerTwo;
    private List<List<Integer>> winningCombinations;

    public GameBoard() {
        menu();
        winningCombinations = List.of(List.of(1,2,3), List.of(4,5,6), List.of(7,8,9),
                List.of(1,4,7), List.of(2,5,8), List.of(3,6,9),
                List.of(1,5,9), List.of(3,5,7));
    }

    public GameParticipant getPlayerOne() { return this.playerOne; }

    public GameParticipant getPlayerTwo() { return this.playerTwo; }

    public void displayBoard() {
        for (int i=0; i< board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void placeSymbol(int place, char symbol) {
        switch (place){
            case 1 -> board[0][0] = symbol; //enhanced switch
            case 2 -> board[0][2] = symbol;
            case 3 -> board[0][4] = symbol;
            case 4 -> board[2][0] = symbol;
            case 5 -> board[2][2] = symbol;
            case 6 -> board[2][4] = symbol;
            case 7 -> board[4][0] = symbol;
            case 8 -> board[4][2] = symbol;
            case 9 -> board[4][4] = symbol;
        }
    }

    public void play() {
        while(true) {
            displayBoard();
            playerOne.placeSymbol();
            checkWinner();
            if(playerTwo instanceof Player) {
                displayBoard();
            }
            playerTwo.placeSymbol();
            checkWinner();
        }
    }

    public void checkWinner() {
        /*
        for(int i=0;i<winningCombinations.size();i++){
            if(playerOne.participantSelections.containsAll(winningCombinations.get(i))){
                System.out.println("Player one won");
                displayBoard();
            }
        }

        for(List<Integer> combination : winningCombinations){
            if(playerOne.participantSelections.containsAll(combination)){
                System.out.println("Player one won");
                displayBoard();
            }
        }
        */
        winningCombinations.forEach(combination -> {
            if(playerOne.participantSelections.containsAll(combination)){
                System.out.println("Player one won");
                displayBoard();
                System.exit(0);
            }
            if(playerTwo.participantSelections.containsAll(combination)){
                System.out.println("Player two won");
                displayBoard();
                System.exit(0);
            }
            if(playerOne.getParticipantSelections().size() + playerTwo.getParticipantSelections().size() == 9){
                System.out.println("Draw");
                displayBoard();
                System.exit(0);
            }
        });
    }

    public void menu() {
        System.out.println("Choose a gamemode option: ");
        System.out.println("1. Player vs Player");
        System.out.println("2. Player vs Bot");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1: // Player vs Player mode
                System.out.println("Enter name for Player One:");
                String playerOneName = scanner.nextLine();

                System.out.println("Enter name for Player Two:");
                String playerTwoName = scanner.nextLine();

                playerOne = new Player(playerOneName,true,this);
                playerTwo = new Player(playerTwoName,false,this);
                break;
            case 2: // Player vs Bot mode
                System.out.println("Enter name for Player One:");
                String playerName = scanner.nextLine();

                playerOne = new Player(playerName,true,this);
                playerTwo = new Bot(this);
                break;
            default:
                System.out.println("Please choose Option 1 or Option 2.");
        }
    }
}
