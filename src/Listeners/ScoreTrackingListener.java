package Listeners;
import HitActions.Counter;
import Sprites.*;

// 322852047 Eyal Geva
/**
 * The class Score tracking listener.
 *
 * @author Eyal Geva
 */
public class ScoreTrackingListener implements HitListener {
    private static final int BLOCK_HIT_WORTH = 5;
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(BLOCK_HIT_WORTH);
    }
}
