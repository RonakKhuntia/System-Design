package factory;

import enums.SplitType;
import strategy.EqualSplitStrategy;
import strategy.PercentageSplitStrategy;
import strategy.SplitStrategy;

public class SplitStrategyFactory {
    public static SplitStrategy getSplitStrategy(SplitType type) {
        return switch(type) {
            case EQUAL -> new EqualSplitStrategy();
            case PERCENTAGE -> new PercentageSplitStrategy();
        };
    }
}
