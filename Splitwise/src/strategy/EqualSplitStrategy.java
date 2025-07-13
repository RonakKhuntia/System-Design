package strategy;

import model.Split;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy {
    @Override
    public List<Split> split(double totalAmount, List<User> participants, Map<User, Double> metadata) {
        double share = totalAmount / participants.size();
        List<Split> splits = new ArrayList<>();
        for (User user : participants) {
            splits.add(new Split(share, user));
        }
        return splits;
    }
}
