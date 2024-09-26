package Sprites;
import Listeners.HitListener;
import HitActions.HitNotifier;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Geometry.*;
import Collidable.*;
import Game.*;

// 322852047 Eyal Geva
/**
 * The class Block.
 *
 * @author Eyal Geva
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // fields
    private Rectangle rect;
    private Color color;
    private GameLevel game;
    private GameEnvironment gameEnvironment;
    private List<HitListener> hitListeners = new ArrayList<>();

    // constructor
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Velocity hit(Ball hitter ,Point collisionPoint,
                        Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        // finding minimum and maximum Y and X of this block
        double minY = rect.getUpperLeft().getY();
        double maxY = rect.getUpperLeft().getY() + rect.getHeight();
        double minX = rect.getUpperLeft().getX();
        double maxX = rect.getUpperLeft().getX() + rect.getWidth();
        // if there's a collision point with the block from top or bottom side
        if (collisionPoint.getY() <= minY || collisionPoint.getY() >= maxY) {
            dy = -dy;
        }
        // if there's a collision point with the block from left or right side
        if (collisionPoint.getX() <= minX || collisionPoint.getX() >= maxX) {
            dx = -dx;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }
    @Override
    public void drawOn(DrawSurface d) {
        // fill each block in given drawsurface with its given color
        d.setColor(color);
        d.fillRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                (int) rect.getHeight());
        // make a separation line between blocks
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                (int) rect.getHeight());
    }
    @Override
    public void timePassed() {
        // Currently we don't need to do anything here.
    }
    /**
     * Adding this block to the game.
     *
     * @param g the game to add to
     */
    public void addToGame(GameLevel g) {
        this.game = g;
        this.game.addCollidable(this);
        this.game.addSprite(this);
        this.gameEnvironment = g.getEnvironment();
    }

    /**
     * removing this block from the game.
     *
     * @param game the game to remove from
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
