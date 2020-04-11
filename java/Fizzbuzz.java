import java.util.List;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

class Fizzbuzz {
    private final List < Strategy > strategies;

    private Fizzbuzz() {
        this.strategies = initStrategies();
    }

    public void run(int maxCount) {
        int counter = 1;
        while (counter <= maxCount) {
            Strategy strategy = getStrategy(counter);
            strategy.doAction(counter);
            counter++;
        }
    }

    private Strategy getStrategy(final int counter) {
        Optional < Strategy > found = this.strategies
            .stream()
            .filter(e -> e.isSuitable(counter))
            .findFirst();
        return found.orElseThrow(() ->
            new RuntimeException("Programmer problem: no strategy to handle value=" + counter));
    }

    private List < Strategy > initStrategies() {
        return List.of(new DivisibleBy3And5(), new DivisibleBy3(), new DivisibleBy5(), new CatchAll());
    }

    public static void main(String[] args) {
        final int maxCount = 100;
        new Fizzbuzz().run(maxCount);
    }
}

interface Strategy {
    public boolean isSuitable(int counter);
    public void doAction(int counter);
}

abstract class AbstractDivisibleBySingleDigit implements Strategy {
    private final int divisor;
    private final String message;

    AbstractDivisibleBySingleDigit(int divisor, String message) {
        this.divisor = divisor;
        this.message = message;
    }

    public boolean isSuitable(final int counter) {
        return counter % this.divisor == 0;
    }

    public void doAction(final int counter) {
        System.out.println(this.message);
    }
}

class DivisibleBy3 extends AbstractDivisibleBySingleDigit {
    DivisibleBy3() {
        super(3, "fizz");
    }
}

class DivisibleBy5 extends AbstractDivisibleBySingleDigit {
    DivisibleBy5() {
        super(5, "buzz");
    }
}

class DivisibleBy3And5 implements Strategy {
    private final Collection < Strategy > childStrats;

    DivisibleBy3And5() {
        this.childStrats = List.of(new DivisibleBy3(), new DivisibleBy5());
    }

    public boolean isSuitable(final int counter) {
        return this.childStrats.stream().allMatch(e -> e.isSuitable(counter));
    }

    public void doAction(final int counter) {
        System.out.println("fizzbuzz");
    }
}

class CatchAll implements Strategy {
    public boolean isSuitable(final int counter) {
        return true;
    }

    public void doAction(final int counter) {
        System.out.println(counter);
    }
}
