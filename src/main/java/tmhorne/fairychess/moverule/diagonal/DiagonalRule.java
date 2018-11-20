package tmhorne.fairychess.moverule.diagonal;

import tmhorne.fairychess.moverule.CompoundRule;
import tmhorne.fairychess.moverule.LeapRule;
import tmhorne.fairychess.moverule.MoveRule;
import tmhorne.fairychess.util.Vector2;

public class DiagonalRule extends CompoundRule {
    private Integer length;

    public DiagonalRule(Integer length) {
        this.length = length;
    }

    @Override
    protected MoveRule[] getRules() {
        return new MoveRule[] {
                new LeapRule(new Vector2(1,1), length),
                new LeapRule(new Vector2(-1,1), length),
                new LeapRule(new Vector2(1,-1), length),
                new LeapRule(new Vector2(-1,-1), length)
        };
    }
}
