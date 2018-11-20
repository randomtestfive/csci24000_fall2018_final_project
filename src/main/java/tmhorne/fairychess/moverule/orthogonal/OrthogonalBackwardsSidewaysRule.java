package tmhorne.fairychess.moverule.orthogonal;

import tmhorne.fairychess.moverule.CompoundRule;
import tmhorne.fairychess.moverule.MoveRule;

public class OrthogonalBackwardsSidewaysRule extends CompoundRule {
    private Integer length;

    public OrthogonalBackwardsSidewaysRule(Integer length) {
        this.length = length;
    }

    @Override
    protected MoveRule[] getRules() {
        return new MoveRule[] {
                new OrthogonalBackwardsRule(length),
                new OrthogonalSidewaysRule(length)
        };
    }
}
