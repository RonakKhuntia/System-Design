import model.Player;
import service.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the board size : ");
        int boardSize = sc.nextInt();

        System.out.println("Enter the number of snakes : ");
        int noOfSnakes = sc.nextInt();

        System.out.println("Enter the number of ladders : ");
        int noOfLadders = sc.nextInt();

        System.out.println("Enter the number of players : ");
        int noOfPlayers = sc.nextInt();

        System.out.println("Enter the number of dice : ");
        int noOfDice = sc.nextInt();

        Game game = new Game(boardSize, noOfLadders, noOfSnakes, noOfDice);

        for (int i = 0; i < noOfPlayers; i++) {
            System.out.println("Enter the name of Player " + (i + 1) + ": ");
            Player player = new Player(sc.next());
            game.addPlayer(player);
        }

        game.startGame();
    }
}