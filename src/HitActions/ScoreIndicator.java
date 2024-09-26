package HitActions;

import biuoop.DrawSurface;
import Sprites.*;
import Game.*;
import java.awt.Color;

// 322852047 Eyal Geva
/**
 * The class Score indicator.
 *
 * @author Eyal Geva
 */
public class ScoreIndicator implements Sprite {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCORE_RECTANGLE_HEIGHT = 15;
    private static final int SCORE_TEXT_HEIGHT = 14;
    private static final int SIZE_OF_TEXT = 15;
    private static final int START_OF_TEXT_X = 350;

    private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    public void drawOn(DrawSurface d) {
        // Draw the background rectangle
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, SCREEN_WIDTH, SCORE_RECTANGLE_HEIGHT);
        // Draw the score on the given DrawSurface
        d.setColor(Color.BLACK);
        d.drawText(START_OF_TEXT_X, SCORE_TEXT_HEIGHT, "Score: "
                + score.getValue(), SIZE_OF_TEXT);
    }

    public void timePassed() {
        // Do nothing - the score doesn't change over time
    }

    /**
     * adds this score indicator to game.
     *
     * @param game the game to add to
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
