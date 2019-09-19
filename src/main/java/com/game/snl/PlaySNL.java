package com.game.snl;

import com.game.snl.models.Ladder;
import com.game.snl.models.Player;
import com.game.snl.models.Snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaySNL {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalSnakes = scanner.nextInt();
        List<Snake> snakes = new ArrayList<Snake>();
        for (int i = 0; i < totalSnakes ; i++) {
            snakes.add(new Snake(scanner.nextInt(), scanner.nextInt()));
        }

        int totalLadders = scanner.nextInt();
        List<Ladder> ladders = new ArrayList<Ladder>();
        for (int i = 0; i < totalLadders ; i++) {
            ladders.add(new Ladder(scanner.nextInt(), scanner.nextInt()));
        }
        int noOfPlayers = scanner.nextInt();
        List<Player> players = new ArrayList<Player>();
        for (int i = 0; i < noOfPlayers; i++) {
            players.add(new Player(scanner.next()));
        }
        SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService();
        snakeAndLadderService.setPlayers(players);
        snakeAndLadderService.setSnakes(snakes);
        snakeAndLadderService.setLadders(ladders);

        snakeAndLadderService.startGame();

    }
}

