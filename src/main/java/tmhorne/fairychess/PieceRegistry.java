package tmhorne.fairychess;

import tmhorne.fairychess.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * registry for pieces and the moves
 * <br>
 * pieces should be named (game).(name) to avoid collisions
 *
 * @see ChessPiece
 * @see ChessPieceInfo
 */
public class PieceRegistry {
    private static Map<String, ChessPieceInfo> registry = new HashMap<>();

    private PieceRegistry() {}

    /**
     * initialize registry
     * <br>
     * comes with chess builtin
     */
    public static void init() {
        registry = new HashMap<>();

        // create chess pieces
        addPieceParlett("chess.king", new ChessPieceInfo("chess.king",
                "King", "K", "1*"));
        addPieceParlett("chess.rook", new ChessPieceInfo("chess.rook",
                "Rook", "R", "n+"));
        addPieceParlett("chess.bishop", new ChessPieceInfo("chess.bishop",
                "Bishop", "B", "nX"));
        addPieceParlett("chess.queen", new ChessPieceInfo("chess.queen",
                "Queen", "Q", "n*"));
        addPieceParlett("chess.knight", new ChessPieceInfo("chess.knight",
                "Knight", "N", "1(1/2)"));
        addPieceParlett("chess.pawn", new ChessPieceInfo("chess.pawn",
                "Pawn", "P", "o1>,oi2>,c1X>"));

        // piece directory
        File pieceDirectory = new File("pieces");

        // make it if it isnt real
        pieceDirectory.mkdir();

        // get the directories
        File[] pieces = pieceDirectory.listFiles(File::isDirectory);

        // for each directory
        for(File piece : pieces) {
            try {
                // read info.txt
                Scanner s = new Scanner(new File(piece, "info.txt"));
                String infoFull = s.nextLine();
                // pipe separator
                String[] info = infoFull.split("\\|");
                // create the piece
                addPieceParlett(piece.getName(), new ChessPieceInfo(piece.getName(),
                        info[0], info[1], info[2]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addPieceParlett(String name, ChessPieceInfo parlett) {
        registry.put(name, parlett);
    }

    public static ChessPieceInfo getPieceInfo(String name) {
        return registry.get(name);
    }

    public static ChessPiece getPiece(String name, Vector2 position, Player player, ChessBoard board) {
        return new ChessPiece(getPieceInfo(name), position, player, board);
    }
}
