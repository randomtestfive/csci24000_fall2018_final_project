package tmhorne.fairychess.moverule.orthogonal;

import tmhorne.fairychess.moverule.LeapRule;
import tmhorne.fairychess.moverule.MoveRule;
import tmhorne.fairychess.util.ChessPiece;
import tmhorne.fairychess.util.MoveModifier;
import tmhorne.fairychess.util.Vector2;

/**
 * defines moving orhtogonally backwards
 * <br>
 * parlett: <
 *
 * @see MoveRule
 */
public class OrthogonalBackwardsRule extends LeapRule {
    public OrthogonalBackwardsRule(MoveModifier modifier, boolean initial, Integer length) {
        super(new Vector2(0,0), modifier, initial, length);
    }

    @Override
    public Vector2 getLeap(ChessPiece piece) {
        return piece.getPlayer().getForward().multiplyScalar(-1);
    }
}
