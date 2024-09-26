package HitActions;
import Game.*;
import Listeners.HitListener;
import Sprites.*;

// 322852047 Eyal Geva
/**
 * The class Block remover.
 *
 * @author Eyal Geva
 */
public class BlockRemover implements HitListener {
    private static final int ONE = 1;
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game            the game
     * @param remainingBlocks the counter of remaining blocks
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        // decrease counter of blocks by one
        remainingBlocks.decrease(ONE);
    }
}

