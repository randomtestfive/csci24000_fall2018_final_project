package tmhorne.fairychess.moverule;

import tmhorne.fairychess.util.MoveModifier;
import tmhorne.fairychess.util.Vector2;

/**
 * defines a normal hippogonal move, such as the knight
 * <br>
 * parlett: (x,y)
 *
 * @see MoveRule
 */
public class MultileapRule extends CompoundRule {
    private Vector2 leap;
    private MoveModifier modifier;
    private boolean initial;
    private Integer length;

    public MultileapRule(Vector2 leap, MoveModifier modifier, boolean initial, Integer length) {
        this.leap = leap;
        this.modifier = modifier;
        this.initial = initial;
        this.length = length;
    }

    @Override
    protected MoveRule[] getRules() {
        int x = leap.getX();
        int y = leap.getY();
        // get every permutation of the two moves
        MoveRule[] tmpRules = {
                new LeapRule(new Vector2( x, y), modifier, initial, length),
                new LeapRule(new Vector2(-x, y), modifier, initial, length),
                new LeapRule(new Vector2( x,-y), modifier, initial, length),
                new LeapRule(new Vector2(-x,-y), modifier, initial, length),
                new LeapRule(new Vector2( y, x), modifier, initial, length),
                new LeapRule(new Vector2(-y, x), modifier, initial, length),
                new LeapRule(new Vector2( y,-x), modifier, initial, length),
                new LeapRule(new Vector2(-y,-x), modifier, initial, length)
        };

        return tmpRules;
    }
}
