package tmhorne.fairychess.moverule.orthogonal;

import tmhorne.fairychess.moverule.CompoundRule;
import tmhorne.fairychess.moverule.MoveRule;
import tmhorne.fairychess.util.MoveModifier;

public class OrthogonalBackwardsSidewaysRule extends CompoundRule {
    private MoveModifier modifier;
    private boolean initial;
    private Integer length;

    public OrthogonalBackwardsSidewaysRule(MoveModifier modifier, boolean initial, Integer length) {
        this.modifier = modifier;
        this.initial = initial;
        this.length = length;
    }

    @Override
    protected MoveRule[] getRules() {
        return new MoveRule[] {
                new OrthogonalBackwardsRule(modifier, initial, length),
                new OrthogonalSidewaysRule(modifier, initial, length)
        };
    }
}
