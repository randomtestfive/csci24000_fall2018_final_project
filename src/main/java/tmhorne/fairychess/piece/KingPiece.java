package tmhorne.fairychess.piece;

import tmhorne.fairychess.util.ChessBoard;
import tmhorne.fairychess.util.ChessPiece;
import tmhorne.fairychess.util.Player;
import tmhorne.fairychess.util.Vector2;

public class KingPiece extends ChessPiece {
    public KingPiece(Vector2 position, Player player, ChessBoard board) {
        super("King", "K", position, player, board, "1*");
    }
}
