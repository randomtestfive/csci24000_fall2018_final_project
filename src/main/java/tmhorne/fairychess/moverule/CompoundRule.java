package tmhorne.fairychess.moverule;

import tmhorne.fairychess.util.ChessPiece;
import tmhorne.fairychess.util.Vector2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * defines a compound rule, a rule that combines multiple smaller rules
 */
public abstract class CompoundRule implements MoveRule {
    private Set<MoveRule> rules;

    @Override
    public Set<Vector2> getPossibleMoves(ChessPiece piece) {
        // checks if the rules have been found then finds them
        if(rules == null) {
            rules = new HashSet<>(Arrays.asList(getRules()));
        }
        return rules.stream()
                .map( r -> r.getPossibleMoves(piece) ) // convert to moves on the board
                .reduce( (a,b) -> { a.addAll(b); return a; } ) // combine rules from each rule
                .orElse(new HashSet<>()); // if there were no rules, there are no moves
    }

    /**
     * finds all of the rules that are to be combined
     * @return an array of the rules
     */
    protected abstract MoveRule[] getRules();
}
