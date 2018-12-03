package tmhorne.fairychess.moverule.orthogonal;

import tmhorne.fairychess.moverule.CompoundRule;
import tmhorne.fairychess.moverule.MoveRule;
import tmhorne.fairychess.util.MoveModifier;

/**
 * defines moving orthogonally in all directions, like a rook
 * <br>
 * parlett: +
 *
 * @see MoveRule
 * @see OrthogonalForwardsBackwardsRule
 * @see OrthogonalSidewaysRule
 */
public class OrthogonalRule extends CompoundRule {
    private MoveModifier modifier;
    private boolean initial;
    private Integer length;

    public OrthogonalRule(MoveModifier modifier, boolean initial, Integer length) {
        this.modifier = modifier;
        this.initial = initial;
        this.length = length;
    }

    @Override
    protected MoveRule[] getRules() {
        return new MoveRule[] {
                new OrthogonalForwardsBackwardsRule(modifier, initial, length),
                new OrthogonalSidewaysRule(modifier, initial, length)
        };
    }
}
