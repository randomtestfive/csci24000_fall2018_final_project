package tmhorne.fairychess.moverule;

import tmhorne.fairychess.util.*;

import java.util.Set;

public interface MoveRule {
    public Set<Vector2> getPossibleMoves(ChessPiece piece);
}
