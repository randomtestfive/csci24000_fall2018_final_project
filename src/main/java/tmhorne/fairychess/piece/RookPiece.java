package tmhorne.fairychess.piece;

import tmhorne.fairychess.util.ChessBoard;
import tmhorne.fairychess.util.ChessPiece;
import tmhorne.fairychess.util.Player;
import tmhorne.fairychess.util.Vector2;

public class RookPiece extends ChessPiece {
    public RookPiece(Vector2 position, Player player, ChessBoard board) {
        super("Rook", "R", position, player, board, "n+");
    }
}
