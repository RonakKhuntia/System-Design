package strategy;

import model.Split;
import model.User;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    List<Split> split(double totalAmount, List<User> participants, Map<User, Double> metadata);
}
