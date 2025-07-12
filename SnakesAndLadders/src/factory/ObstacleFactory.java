package factory;

import enums.ObstacleType;
import model.Ladder;
import model.Obstacle;
import model.Snake;

public class ObstacleFactory {
    public static Obstacle createObstacle(ObstacleType type, int src, int dest) {
        return switch (type) {
            case SNAKE -> new Snake(src, dest);
            case LADDER -> new Ladder(src, dest);
        };
    }
}
