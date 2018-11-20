package tmhorne.fairychess.moverule;

import tmhorne.fairychess.util.Vector2;

public class MultileapRule extends CompoundRule {
    private Vector2 leap;
    private Integer length;

    public MultileapRule(Vector2 leap, Integer length) {
        this.leap = leap;
        this.length = length;
    }

    @Override
    protected MoveRule[] getRules() {
        int x = leap.getX();
        int y = leap.getY();
        MoveRule[] tmpRules = {
                new LeapRule(new Vector2( x, y), length),
                new LeapRule(new Vector2(-x, y), length),
                new LeapRule(new Vector2( x,-y), length),
                new LeapRule(new Vector2(-x,-y), length),
                new LeapRule(new Vector2( y, x), length),
                new LeapRule(new Vector2(-y, x), length),
                new LeapRule(new Vector2( y,-x), length),
                new LeapRule(new Vector2(-y,-x), length)
        };

        return tmpRules;
    }
}
