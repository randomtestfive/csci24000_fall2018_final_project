package tmhorne.fairychess.player;

import org.jetbrains.annotations.NotNull;
import tmhorne.fairychess.util.ChessMove;
import tmhorne.fairychess.util.Player;
import tmhorne.fairychess.util.PlayerColor;
import tmhorne.fairychess.util.Vector2;

/**
 * dummy player, does nothing, just a placeholder
 */
public class DumbPlayer extends Player {
    public DumbPlayer(PlayerColor color) {
        super(color);
    }


    @NotNull
    @Override
    public ChessMove getMove() {
        return new ChessMove(new Vector2(0,0), new Vector2(0,0));
    }
}
