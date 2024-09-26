package HitActions;

// 322852047 Eyal Geva
/**
 * The class Counter.
 *
 * @author Eyal Geva
 */
public class Counter {
    private static final int ZERO = 0;
    private int count;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {this.count = ZERO;}

    /**
     * Increase the counter by a given number
     *
     * @param number the number to increase by
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * Decrease the counter by a given number
     *
     * @param number the number to decrease by
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * Gets value of count.
     *
     * @return the value of count
     */
    public int getValue() {
        return count;
    }
}

