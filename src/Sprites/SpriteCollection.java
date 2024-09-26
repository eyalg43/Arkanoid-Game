package Sprites;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

// 322852047 Eyal Geva
/**
 * The class Sprite collection.
 *
 * @author Eyal Geva
 */
public class SpriteCollection {
    // fields
    private List<Sprite> sprites;

    // constructor
    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds sprite to this list of sprites.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s){
        sprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(sprites);
        for (Sprite sprite: spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the draw face
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite: sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * returns this list of sprites.
     *
     * @return this list of sprites.
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * removes sprite from list of sprites
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
}