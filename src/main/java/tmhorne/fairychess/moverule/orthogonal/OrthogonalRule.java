package tmhorne.fairychess.moverule.orthogonal;

import tmhorne.fairychess.moverule.CompoundRule;
import tmhorne.fairychess.moverule.MoveRule;

public class OrthogonalRule extends CompoundRule {
    private Integer length;

    public OrthogonalRule(Integer length) {
        this.length = length;
    }

    @Override
    protected MoveRule[] getRules() {
        return new MoveRule[] {
                new OrthogonalForwardsBackwardsRule(length),
                new OrthogonalSidewaysRule(length)
        };
    }
}
