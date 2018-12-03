package tmhorne.fairychess.moverule.orthogonal;

import tmhorne.fairychess.moverule.CompoundRule;
import tmhorne.fairychess.moverule.MoveRule;
import tmhorne.fairychess.util.MoveModifier;

/**
 * defines moving forwards and sideways orthogonally
 * <br>
 * parlett: =>
 *
 * @see MoveRule
 * @see OrthogonalForwardsRule
 * @see OrthogonalSidewaysRule
 */
public class OrthogonalForwardsSidewaysRule extends CompoundRule {
    private MoveModifier modifier;
    private boolean initial;
    private Integer length;

    public OrthogonalForwardsSidewaysRule(MoveModifier modifier, boolean initial, Integer length) {
        this.modifier = modifier;
        this.initial = initial;
        this.length = length;
    }

    @Override
    protected MoveRule[] getRules() {
        return new MoveRule[] {
                new OrthogonalForwardsRule(modifier, initial, length),
                new OrthogonalSidewaysRule(modifier, initial, length)
        };
    }
}
