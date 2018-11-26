package tmhorne.fairychess.moverule.diagonal;

import tmhorne.fairychess.moverule.CompoundRule;
import tmhorne.fairychess.moverule.LeapRule;
import tmhorne.fairychess.moverule.MoveRule;
import tmhorne.fairychess.util.MoveModifier;
import tmhorne.fairychess.util.Vector2;

public class DiagonalRule extends CompoundRule {
    private MoveModifier modifier;
    private boolean initial;
    private Integer length;

    public DiagonalRule(MoveModifier modifier, boolean initial, Integer length) {
        this.modifier = modifier;
        this.initial = initial;
        this.length = length;
    }

    @Override
    protected MoveRule[] getRules() {
        return new MoveRule[] {
                new LeapRule(new Vector2(1,1), modifier, initial, length),
                new LeapRule(new Vector2(-1,1), modifier, initial, length),
                new LeapRule(new Vector2(1,-1), modifier, initial, length),
                new LeapRule(new Vector2(-1,-1), modifier, initial, length)
        };
    }
}
