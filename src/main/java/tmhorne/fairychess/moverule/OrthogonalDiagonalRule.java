package tmhorne.fairychess.moverule;

import tmhorne.fairychess.moverule.diagonal.DiagonalBackwardsRule;
import tmhorne.fairychess.moverule.diagonal.DiagonalRule;
import tmhorne.fairychess.moverule.orthogonal.OrthogonalForwardsSidewaysRule;
import tmhorne.fairychess.moverule.orthogonal.OrthogonalRule;
import tmhorne.fairychess.util.MoveModifier;

public class OrthogonalDiagonalRule extends CompoundRule {
    private MoveModifier modifier;
    private boolean initial;
    private Integer length;

    public OrthogonalDiagonalRule(MoveModifier modifier, boolean initial, Integer length) {
        this.modifier = modifier;
        this.initial = initial;
        this.length = length;
    }

    @Override
    protected MoveRule[] getRules() {
        return new MoveRule[] {
                new OrthogonalRule(modifier, initial, length),
                new DiagonalRule(modifier, initial, length)
        };
    }
}
