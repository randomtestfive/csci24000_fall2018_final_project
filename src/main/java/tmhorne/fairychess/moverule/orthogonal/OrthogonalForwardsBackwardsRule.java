package tmhorne.fairychess.moverule.orthogonal;

import tmhorne.fairychess.moverule.CompoundRule;
import tmhorne.fairychess.moverule.MoveRule;
import tmhorne.fairychess.util.MoveModifier;

/**
 * defines moving forwards and backwards orthogonally
 * <br>
 * parlett: <>
 *
 * @see MoveRule
 * @see OrthogonalForwardsRule
 * @see OrthogonalBackwardsRule
 */
public class OrthogonalForwardsBackwardsRule extends CompoundRule {
    private MoveModifier modifier;
    private boolean initial;
    private Integer length;

    public OrthogonalForwardsBackwardsRule(MoveModifier modifier, boolean initial, Integer length) {
        this.modifier = modifier;
        this.initial = initial;
        this.length = length;
    }

    @Override
    protected MoveRule[] getRules() {
        return new MoveRule[] {
                new OrthogonalForwardsRule(modifier, initial, length),
                new OrthogonalBackwardsRule(modifier, initial, length)
        };
    }
}
