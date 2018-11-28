package tmhorne.fairychess.player;

import org.jetbrains.annotations.NotNull;
import tmhorne.fairychess.util.ChessMove;
import tmhorne.fairychess.util.Player;
import tmhorne.fairychess.util.PlayerColor;
import tmhorne.fairychess.util.Vector2;

public class GraphicsPlayer extends Player {
    private Vector2 start;
    private Vector2 end;

    public GraphicsPlayer(PlayerColor color) {
        super(color);
    }

    public void setStart(Vector2 start) {
        this.start = start;
    }

    public void setEnd(Vector2 end) {
        this.notify();
        this.end = end;
    }

    @NotNull
    @Override
    public ChessMove getMove() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ChessMove(start, end);
    }
}
