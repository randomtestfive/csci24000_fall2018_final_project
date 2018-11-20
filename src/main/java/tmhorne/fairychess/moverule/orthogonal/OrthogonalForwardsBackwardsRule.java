package tmhorne.fairychess.moverule.orthogonal;

import tmhorne.fairychess.moverule.CompoundRule;
import tmhorne.fairychess.moverule.MoveRule;

public class OrthogonalForwardsBackwardsRule extends CompoundRule {
    private Integer length;

    public OrthogonalForwardsBackwardsRule(Integer length) {
        this.length = length;
    }

    @Override
    protected MoveRule[] getRules() {
        return new MoveRule[] {
                new OrthogonalForwardsRule(length),
                new OrthogonalBackwardsRule(length)
        };
    }
}
