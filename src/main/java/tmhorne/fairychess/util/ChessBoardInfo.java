package tmhorne.fairychess.util;

/**
 * holds information that a chess board can be instantiated from
 * @see ChessBoard
 */
public class ChessBoardInfo {
    private int xSize;
    private int ySize;
    private String[][] pieces;

    public ChessBoardInfo(int xSize, int ySize, String[][] pieces) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.pieces = pieces;
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }

    public String[][] getPieces() {
        return pieces;
    }
}
