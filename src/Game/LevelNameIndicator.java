package Game;

import Sprites.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

// 322852047 Eyal Geva
/**
 * The class Level name indicator.
 *
 * @author Eyal Geva
 */
public class LevelNameIndicator implements Sprite {
    private static final int TEXT_HEIGHT = 14;
    private static final int SIZE_OF_TEXT = 15;
    private static final int START_OF_TEXT_X = 600;
    private String levelName;

    /**
     * Instantiates a new Level name indicator.
     *
     * @param levelName the level name
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(START_OF_TEXT_X, TEXT_HEIGHT, "Level Name: " +  levelName,
                SIZE_OF_TEXT);
    }

    @Override
    public void timePassed() {

    }

    /**
     * Adds this level name indicator to game.
     *
     * @param game the game to add to
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
