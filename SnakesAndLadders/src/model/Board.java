package model;

import enums.ObstacleType;

import java.util.Queue;

public class Board {
    private final int size;

    public int getSize() {
        return size;
    }

    private final int sideLength;
    private final Cell[][] grid;

    public Board(int size) {
        this.size = size;
        this.sideLength = (int) Math.sqrt(size);
        this.grid = new Cell[sideLength][sideLength];

        int position = 1;
        boolean lefToRight = true;

        for (int i = sideLength - 1; i >= 0; i--) {
            if (lefToRight) {
                for (int j = 0; j < sideLength; j++) {
                    grid[i][j] = new Cell(position++);
                }
            }
            else {
                for (int j = sideLength - 1; j >= 0; j--) {
                    grid[i][j] = new Cell(position++);
                }
            }
            lefToRight = !lefToRight;
        }
    }

    private int getRow(int position) {
        int row = (position - 1) / sideLength;
        return sideLength - 1 - row;
    }

    private int getColumn(int position) {
        int row = getRow(position);
        int col = (position - 1) % sideLength;
        return (row % 2 == 0) ? sideLength - 1 - col : col;
    }

    private Cell getCell(int position) {
        return grid[getRow(position)][getColumn(position)];
    }

    public boolean addObstacle(Obstacle obstacle) {
        Cell srcCell = getCell(obstacle.getSrc());
        Cell destCell = getCell(obstacle.getDest());

        if (srcCell.hasObstacle() || destCell.hasObstacle()) {
            return false;
        }

        srcCell.setObstacle(obstacle);
        return true;
    }

    public int getNewPosition(Player player, int offset) {
        int newPosition = player.getPosition() + offset;

        if (newPosition > size) {
            System.out.println("Invalid position");
            return player.getPosition();
        }

        Cell cell = grid[getRow(newPosition)][getColumn(newPosition)];
        int finalPosition = cell.getFinalPosition();

        if (finalPosition < newPosition) {
            System.out.println("Oops! Snake has bitten " + player.getName());
        } else if (finalPosition > newPosition) {
            System.out.println("Congratulations! " + player.getName() + "moved up through a ladder");
        } else {
            System.out.println(player.getName() + " moved from " + player.getPosition() + " to " + finalPosition);
        }

        return finalPosition;
    }

    public void printBoard(Queue<Player> players) {
        Player player = players.peek();
        int playerPosition = player.getPosition();
        System.out.println("\nCurrent Board State: ");
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                Cell cell = grid[i][j];
                Obstacle obstacle = cell.getObstacle();
                ObstacleType obstacleType = obstacle == null ? null : obstacle.getObstacleType();
                int position = grid[i][j].getPosition();
                String cellContent = String.valueOf(position);
                if (obstacleType != null) {
                    System.out.print("" + obstacleType.name().charAt(0) + obstacle.getDest() + " ");
                } else if (j == playerPosition) {
                    System.out.print(player.getName() + " ");
                } else {
                    System.out.print(cellContent + " ");
                }
            }
            System.out.println();
        }
    }

}
