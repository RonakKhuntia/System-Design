package model;

public class Cell {
    private final int position;
    private Obstacle obstacle;

    public int getPosition() {
        return position;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public Cell(int position) {
        this.position = position;
    }

    public boolean hasObstacle() {
        return obstacle != null;
    }

    public int getFinalPosition() {
        return hasObstacle() ? obstacle.movePlayer() : position;
    }
}
