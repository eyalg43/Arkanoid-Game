package HitActions;
import Game.*;
import Listeners.HitListener;
import Sprites.*;

// 322852047 Eyal Geva
/**
 * The class Ball remover.
 *
 * @author Eyal Geva
 */
public class BallRemover implements HitListener {
    private static final int ONE = 1;
    private GameLevel game;
    private Counter ballCounter;

    /**
     * Instantiates a new Ball remover.
     *
     * @param game        the game
     * @param ballCounter the ball counter
     */
    public BallRemover(GameLevel game, Counter ballCounter) {
        this.game = game;
        this.ballCounter = ballCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        // decrease counter of balls by one
        ballCounter.decrease(ONE);
    }
}
