package tmhorne.fairychess.moverule.diagonal;

import tmhorne.fairychess.moverule.CompoundRule;
import tmhorne.fairychess.moverule.LeapRule;
import tmhorne.fairychess.moverule.MoveRule;
import tmhorne.fairychess.util.ChessPiece;
import tmhorne.fairychess.util.MoveModifier;
import tmhorne.fairychess.util.Vector2;

public class DiagonalForwardsRule extends CompoundRule {
    private MoveModifier modifier;
    private boolean initial;
    private Integer length;

    public DiagonalForwardsRule(MoveModifier modifier, boolean initial, Integer length) {
        this.modifier = modifier;
        this.initial = initial;
        this.length = length;
    }

    @Override
    protected MoveRule[] getRules() {
        return new MoveRule[] {
                // okay this is about to get a little messy
                // im going to make two new leap rules but override the get leap function
                // this isnt very clean because i do it twice
                // but a new class would have been a bit much
                new LeapRule(new Vector2(0,0), modifier, initial, length) {
                    @Override
                    public Vector2 getLeap(ChessPiece piece) {
                        return piece.getPlayer().getForward().add(new Vector2(1,0));
                    }
                },
                new LeapRule(new Vector2(0,0), modifier, initial, length) {
                    @Override
                    public Vector2 getLeap(ChessPiece piece) {
                        //                    this should be the only difference vv
                        return piece.getPlayer().getForward().add(new Vector2(-1,0));
                    }
                }
        };
    }
}
