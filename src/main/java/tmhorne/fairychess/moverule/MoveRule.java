package tmhorne.fairychess.moverule;

import tmhorne.fairychess.util.*;

import java.util.Set;

/**
 * defines a rule for a way to move in a game of chess
 */
public interface MoveRule {
    /**
     * finds all the possible moves for a given piece
     * @param piece piece to find moves for
     * @return all possible moves
     */
    Set<Vector2> getPossibleMoves(ChessPiece piece);
}
