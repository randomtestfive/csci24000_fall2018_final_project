package tmhorne.fairychess.moverule.diagonal;

import tmhorne.fairychess.moverule.CompoundRule;
import tmhorne.fairychess.moverule.LeapRule;
import tmhorne.fairychess.moverule.MoveRule;
import tmhorne.fairychess.util.ChessPiece;
import tmhorne.fairychess.util.MoveModifier;
import tmhorne.fairychess.util.Vector2;

/**
 * defines moving diagonally, but only backwards
 * <br>
 * parlett: X<
 *
 * @see MoveRule
 */
public class DiagonalBackwardsRule extends CompoundRule {
    private MoveModifier modifier;
    private boolean initial;
    private Integer length;

    public DiagonalBackwardsRule(MoveModifier modifier, boolean initial, Integer length) {
        this.modifier = modifier;
        this.initial = initial;
        this.length = length;
    }

    @Override
    protected MoveRule[] getRules() {
        return new MoveRule[] {
                // see DiagonalForwards for full explaination
                // this just also adds a scalar multiplication to get backwards
                new LeapRule(new Vector2(0,0), modifier, initial, length) {
                    @Override
                    public Vector2 getLeap(ChessPiece piece) {
                        return piece.getPlayer().getForward().multiplyScalar(-1).add(new Vector2(1,0));
                    }
                },
                new LeapRule(new Vector2(0,0), modifier, initial, length) {
                    @Override
                    public Vector2 getLeap(ChessPiece piece) {
                        return piece.getPlayer().getForward().multiplyScalar(-1).add(new Vector2(-1,0));
                    }
                }
        };
    }
}
