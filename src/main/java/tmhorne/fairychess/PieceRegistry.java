package tmhorne.fairychess;

import tmhorne.fairychess.util.*;

import java.util.HashMap;
import java.util.Map;

public class PieceRegistry {
    private static Map<String, ChessPieceInfo> registry = new HashMap<>();

    public static void init() {
        registry = new HashMap<>();

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


        // TODO load pieces from directory
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
