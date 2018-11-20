package tmhorne.fairychess.moverule.orthogonal;

import tmhorne.fairychess.moverule.CompoundRule;
import tmhorne.fairychess.moverule.LeapRule;
import tmhorne.fairychess.moverule.MoveRule;
import tmhorne.fairychess.util.Vector2;

public class OrthogonalSidewaysRule extends CompoundRule {
    private Integer length;

    public OrthogonalSidewaysRule(Integer length) {
        this.length = length;
    }

    @Override
    protected MoveRule[] getRules() {
        return new MoveRule[] {
                new LeapRule(new Vector2(0,1), length),
                new LeapRule(new Vector2(0,-1), length)
        };
    }
}
