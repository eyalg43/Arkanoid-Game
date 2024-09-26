package Animation;

import biuoop.DrawSurface;

// 322852047 Eyal Geva
/**
 * The interface Animation.
 *
 * @author Eyal Geva
 */
public interface Animation {
    /**
     * Perform one frame of the animation.
     *
     * @param d the draw surface on which the animation is drawn
     */
    void doOneFrame(DrawSurface d);

    /**
     * Check if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    boolean shouldStop();
}
