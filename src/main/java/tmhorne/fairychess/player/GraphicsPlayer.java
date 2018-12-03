package tmhorne.fairychess.player;

import org.jetbrains.annotations.NotNull;
import tmhorne.fairychess.util.ChessMove;
import tmhorne.fairychess.util.Player;
import tmhorne.fairychess.util.PlayerColor;
import tmhorne.fairychess.util.Vector2;

/**
 * player for use with a graphical interface, or just an interface running in another thread
 */
public class GraphicsPlayer extends Player {
    private Vector2 start;
    private Vector2 end;

    public GraphicsPlayer(PlayerColor color) {
        super(color);
    }

    /**
     * sets the start position for the next move
     * @param start position
     */
    public void setStart(Vector2 start) {
        this.start = start;
    }

    public Vector2 getStart() {
        return start;
    }

    /**
     * sets the end position for the next move
     * <br>
     * unfreezes the thread that {@link Player#getMove} was called on
     * @param end position
     */
    public synchronized void setEnd(Vector2 end) {
        this.notify();
        this.end = end;
    }

    public Vector2 getEnd() {
        return end;
    }

    @NotNull
    @Override
    public ChessMove getMove() {
        try {
            synchronized (this) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Vector2 tmpStart = start;
        Vector2 tmpEnd = end;
        start = null;
        end = null;

        return new ChessMove(tmpStart, tmpEnd);
    }
}
