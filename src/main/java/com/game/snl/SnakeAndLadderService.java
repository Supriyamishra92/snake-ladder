package com.game.snl;

import com.game.snl.models.*;

import java.util.*;

public class SnakeAndLadderService {
    private SnakeAndLadderBoard gameBoard;
    private int initialNumOfPlayers;
    private Queue<Player> players;
    private boolean isGameOver;

    private int numberOfDices; //extension rule 1
    private boolean shouldGameContinueTillLastPlayer; //extension rule 3



    private boolean isSixValidNow;  //extension rule 4

    private static final int DEFAULT_BOARD_SIZE = 100;
    private static final int DEFAULT_NO_OF_DICE = 1;


    public SnakeAndLadderService(int boardSize) {  //extension rule 2
        this.gameBoard = new SnakeAndLadderBoard(boardSize);
        this.players = new LinkedList<Player>();
        this.numberOfDices = SnakeAndLadderService.DEFAULT_NO_OF_DICE;
    }

    public SnakeAndLadderService() {
        this(SnakeAndLadderService.DEFAULT_BOARD_SIZE);
    }

    public void setNumberOfDices(int numberOfDices) {
        this.numberOfDices = numberOfDices;
    }

    public void setShouldGameContinueTillLastPlayer(boolean shouldGameContinueTillLastPlayer) {
        this.shouldGameContinueTillLastPlayer = shouldGameContinueTillLastPlayer;
    }

    public void setSixValidNow(boolean sixValidNow) {
        isSixValidNow = sixValidNow;
    }

    public void setSnakes(List<Snake> snakes)  {
       gameBoard.setSnakes(snakes);

    }
    public void setLadders(List<Ladder> ladders) {
        gameBoard.setLadders(ladders);
    }

    public void setPlayers(List<Player> players) {
        this.players = new LinkedList<Player>();
        this.initialNumOfPlayers = players.size();

        Map<String, Integer> playerPieces = new HashMap<>();

        for (Player player:players) {
            this.players.add(player);
            playerPieces.put(player.getUserId(), 0);
            
        }
        gameBoard.setPlayerPieces(playerPieces);
    }

    private int getNextPosition(int newPosition) {
        int previousPosition;

        do {
            previousPosition = newPosition;
            for (Snake snake : gameBoard.getSnakes()) {
                if (snake.getStart() == newPosition) {
                    newPosition = snake.getEnd();
                }

            }
            for (Ladder ladder : gameBoard.getLadders()) {
                if (ladder.getStart() == newPosition) {
                    newPosition = ladder.getEnd();
                }
             }
        } while (newPosition != previousPosition);
        return newPosition;
    }

    private void movePlayer(Player player, int positions) {
        int oldPosition = gameBoard.getPlayerPieces().get(player.getUserId());
        int newPosition = oldPosition + positions; // Based on the dice value, the player moves their piece forward that number of cells.

        int boardSize = gameBoard.getSize();

        if (newPosition > boardSize) {
            newPosition = oldPosition;

        } else {
            getNextPosition(newPosition);

        }
        gameBoard.getPlayerPieces().put(player.getUserId(), newPosition);
        System.out.println(player.getName() + " rolled a " + positions + " and moved from " + oldPosition +" to " + newPosition);

    }

    private int getTotalDiceOutComes() {
        return DiceService.roll();
    }

    private boolean hasPlayerWon(Player player) {

        int playerPosition = gameBoard.getPlayerPieces().get(player.getUserId());
        int winningPosition = gameBoard.getSize();
        return playerPosition == winningPosition;

    }

    private boolean isGameOver() {
        int currentPlayers = players.size();
        return currentPlayers < initialNumOfPlayers;
    }

    public void startGame() {
        while (!isGameOver) {
            int totalStepToTake = getTotalDiceOutComes();
            Player currentPlayer = players.poll();
            movePlayer(currentPlayer, totalStepToTake);
            if (hasPlayerWon(currentPlayer)) {
                System.out.println(currentPlayer.getName() + " wins the game");
            } else {
                players.add(currentPlayer);
            }
        }
    }
}

