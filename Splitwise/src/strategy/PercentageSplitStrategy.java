package strategy;

import model.Split;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PercentageSplitStrategy implements SplitStrategy {
    @Override
    public List<Split> split(double totalAmount, List<User> participants, Map<User, Double> metadata) {
       double totalPercent = metadata.values().stream().mapToDouble(Double::doubleValue).sum();
       if (totalPercent != 100.0) {
           throw new IllegalArgumentException("Total percent must be 100%.");
       }
       List<Split> splits = new ArrayList<>();
       for (User user : participants) {
           splits.add(new Split(totalAmount * metadata.getOrDefault(user, 0.0) / 100, user));
       }
       return splits;
    }
}
