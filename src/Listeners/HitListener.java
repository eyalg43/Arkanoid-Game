package Listeners;
import Sprites.*;

// 322852047 Eyal Geva
/**
 * The interface Hit listener.
 *
 * @author Eyal Geva
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit. The hitter
     * parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball who is the hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}

