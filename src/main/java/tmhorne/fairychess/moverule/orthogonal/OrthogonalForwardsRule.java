package tmhorne.fairychess.moverule.orthogonal;

import tmhorne.fairychess.moverule.LeapRule;
import tmhorne.fairychess.util.ChessBoard;
import tmhorne.fairychess.util.ChessPiece;
import tmhorne.fairychess.util.Player;
import tmhorne.fairychess.util.Vector2;

public class OrthogonalForwardsRule extends LeapRule {
    public OrthogonalForwardsRule(Integer length) {
        // just give a garbage vector
        super(new Vector2(0,0), length);
    }

    @Override
    public Vector2 getLeap(ChessBoard board, Player player, ChessPiece piece) {
        return player.getForward();
    }
}
