package tmhorne.fairychess;

import tmhorne.fairychess.player.DumbPlayer;
import tmhorne.fairychess.util.*;

import java.io.File;

/**
 * The main class
 */
public class Main {
    public static void main(String[] args) {
        PieceRegistry.init();

        ChessBoard board = new ChessBoard(8, 8);
        Player p = new DumbPlayer(PlayerColor.BLACK);
        Player p2 = new DumbPlayer(PlayerColor.WHITE);

        PieceRegistry.getPiece("chess.pawn", new Vector2(4,4), p, board);
        PieceRegistry.getPiece("chess.pawn", new Vector2(3,3), p, board);
        PieceRegistry.getPiece("chess.pawn", new Vector2(4,2), p2, board);

        System.out.println(board);
    }
}
