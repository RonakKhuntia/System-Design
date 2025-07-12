package service;

import enums.ObstacleType;
import factory.ObstacleFactory;
import model.Board;
import model.Dice;
import model.Obstacle;
import model.Player;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class Game {
    private final int noOfSnakes;
    private final int noOfLadders;
    private final Board board;
    private final Queue<Player> players;
    private final Dice dice;

    public Game(int size, int noOfSnakes, int noOfLadders, int noOfDice) {
        this.noOfSnakes = noOfSnakes;
        this.noOfLadders = noOfLadders;
        board = new Board(size);
        dice = new Dice(noOfDice);
        players = new ArrayDeque<>();

        initBoardObstacles();
    }

    private void initBoardObstacles() {
        generateObstacles(noOfSnakes, ObstacleType.SNAKE);
        generateObstacles(noOfLadders, ObstacleType.LADDER);
    }

    private void generateObstacles(int count, ObstacleType type) {
        Random random = new Random();
        int size = board.getSize();

        while (count > 0) {
            int src = random.nextInt(size - 1) + 2;
            int dest = random.nextInt(src - 1) + 2;

            Obstacle obstacle = ObstacleFactory.createObstacle(type, src, dest);
            if (board.addObstacle(obstacle)) {
                count--;
            }
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void startGame() {
        board.printBoard(players);

        while (players.size() > 1) {
            Player currentPlayer = players.poll();
            System.out.println("------------------------------------------------------");

            int diceRoll = dice.roll();
            System.out.println(currentPlayer.getName() + " rolled " + diceRoll);

            int newPosition = board.getNewPosition(currentPlayer, diceRoll);

            if (newPosition == currentPlayer.getPosition()) {
                players.offer(currentPlayer);
                continue;
            }

            currentPlayer.setPosition(newPosition);

            if (newPosition == board.getSize()) {
                System.out.println(currentPlayer.getName() + " won!");
            } else {
                players.offer(currentPlayer);
            }

            board.printBoard(players);
        }
    }
}
