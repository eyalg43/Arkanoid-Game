package Animation;

import Sprites.*;
import biuoop.DrawSurface;

import java.awt.*;

// 322852047 Eyal Geva
/**
 * The class Countdown animation.
 *
 * @author Eyal Geva
 */
public class CountdownAnimation implements Animation {
    private static final int TEXT_Y = 300;
    private static final int TEXT_X = 120;
    private static final int TEXT_SIZE = 100;
    private static final int ZERO = 0;
    private static final int ONE_THOUSAND = 1000;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private long startTime;
    private long durationPerNumber;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the duration of the countdown animation in seconds
     * @param countFrom    the number to start the countdown from
     * @param gameScreen   game screen to draw before displaying the countdown
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.startTime = System.currentTimeMillis();
        this.durationPerNumber = (long) (numOfSeconds *
                ONE_THOUSAND / countFrom);
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        long passedTime = System.currentTimeMillis() - startTime;
        int currentNumber = countFrom - (int) (passedTime / durationPerNumber);
        // Draw the game screen
        gameScreen.drawAllOn(d);
        if (currentNumber > ZERO) {
            d.setColor(Color.BLUE);
            // Draw the countdown number
            d.drawText(TEXT_X, TEXT_Y, currentNumber + "...", TEXT_SIZE);
        }
        if (passedTime >= numOfSeconds * ONE_THOUSAND) {
            stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return stop;
    }
}

