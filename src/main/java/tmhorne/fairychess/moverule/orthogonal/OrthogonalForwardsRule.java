package tmhorne.fairychess.moverule.orthogonal;

import tmhorne.fairychess.moverule.LeapRule;
import tmhorne.fairychess.util.ChessPiece;
import tmhorne.fairychess.util.MoveModifier;
import tmhorne.fairychess.util.Vector2;

public class OrthogonalForwardsRule extends LeapRule {
    public OrthogonalForwardsRule(MoveModifier modifier, boolean initial, Integer length) {
        // just give a garbage vector
        super(new Vector2(0,0), modifier, initial, length);
    }

    @Override
    public Vector2 getLeap(ChessPiece piece) {
        return piece.getPlayer().getForward();
    }
}
