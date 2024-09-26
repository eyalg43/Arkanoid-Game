package Game;

import biuoop.DrawSurface;

import Animation.*;

// 322852047 Eyal Geva
/**
 * The class You win screen.
 *
 * @author Eyal Geva
 */
public class YouWinScreen implements Animation {
    private static final int TEXT_SIZE = 32;
    private static final int TEXT_X = 150;
    private static final int TEXT_Y = 250;

    private int score;


    /**
     * Instantiates a new You win screen.
     *
     * @param score the score to display on the screen
     */
    public YouWinScreen(int score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(TEXT_X, TEXT_Y, "You Win! Your score is: " +
                score, TEXT_SIZE);

    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}

