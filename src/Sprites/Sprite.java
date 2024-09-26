package Sprites;
import biuoop.DrawSurface;

// 322852047 Eyal Geva
/**
 * The interface Sprite.
 *
 * @author Eyal Geva
 */
public interface Sprite {
    /**
     * draw the sprite on the screen.
     *
     * @param d the draw face
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed
     */
    void timePassed();
}