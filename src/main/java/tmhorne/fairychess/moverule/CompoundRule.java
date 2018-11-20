package tmhorne.fairychess.moverule;

import tmhorne.fairychess.util.ChessBoard;
import tmhorne.fairychess.util.ChessPiece;
import tmhorne.fairychess.util.Player;
import tmhorne.fairychess.util.Vector2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class CompoundRule implements MoveRule {
    private Set<MoveRule> rules;

    public CompoundRule() {
        this.rules = new HashSet<>(Arrays.asList(getRules()));
    }

    @Override
    public Set<Vector2> getPossibleMoves(ChessBoard board, Player player, ChessPiece piece) {
        return rules.stream()
                .map( r -> r.getPossibleMoves(board, player, piece) )
                .reduce( (a,b) -> { a.addAll(b); return a; } )
                .orElse(new HashSet<>());
    }

    protected abstract MoveRule[] getRules();
}
