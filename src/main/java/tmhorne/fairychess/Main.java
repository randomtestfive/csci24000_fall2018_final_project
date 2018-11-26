package tmhorne.fairychess;

import tmhorne.fairychess.piece.KingPiece;
import tmhorne.fairychess.piece.RookPiece;
import tmhorne.fairychess.player.DumbPlayer;
import tmhorne.fairychess.util.*;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class
 */
public class Main {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard(8, 8);
        Player p = new DumbPlayer(PlayerColor.BLACK);

        ChessPiece piece = new RookPiece(new Vector2(4,4), p, board);

        System.out.println(board);
    }
}
