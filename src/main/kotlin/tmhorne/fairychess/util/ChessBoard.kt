package tmhorne.fairychess.util

import tmhorne.fairychess.PieceRegistry

/**
 * describes a chess board
 */
class ChessBoard {
    val pieces: HashSet<ChessPiece> = HashSet<ChessPiece>();
    val xSize: Int;
    val ySize: Int;

    constructor(xSize: Int, ySize: Int) {
        this.xSize = xSize;
        this.ySize = ySize;
    }

    /**
     * creates a chess board
     * @param info board information
     * @param p1 player 1
     * @param p2 player 2
     */
    constructor(info: ChessBoardInfo, p1: Player, p2: Player) {
        // copy sizes
        this.xSize = info.xSize;
        this.ySize = info.ySize;

        // go through x,y
        for((y, line) in info.pieces.withIndex()) {
            for((x, piece) in line.withIndex()) {
                // split to get color
                val pieceInfo = piece.split("/");
                // if its garbage just ignore it
                if(pieceInfo.size != 2) {
                    continue;
                }

                // add piece to the board
                addPiece(pieceInfo[0],
                        (if(pieceInfo[1] == "1") p1 else p2),
                        Vector2(x, y));
            }
        }
    }

    fun addPiece(info: String, player: Player, position: Vector2) {
        PieceRegistry.getPiece(info, position, player,this);
    }

    /**
     * finds piece at a position
     *
     * null if no piece
     * @param pos position to check
     * @return piece at position if it exists
     */
    fun getPieceAt(pos: Vector2): ChessPiece? {
        return pieces.stream()
                .filter { p -> p.getPosition().equals(pos) }
                .findFirst()
                .orElse(null);
    }

    fun addPiece(piece: ChessPiece) {
        pieces.add(piece);
    }

    /**
     * applies a move
     * @param move move to apply
     */
    fun applyMove(move: ChessMove) {
        val piece: ChessPiece = getPieceAt(move.start) ?: return;
        pieces.remove(getPieceAt(move.end));
        piece.position = move.end;
    }

    override fun toString(): String {
        val p: ChessPiece? = getPieceAt(Vector2(4,4));
        val m: Set<Vector2>? = p?.getPossibleMoves();
        var out: String = "";
        for(y in 1..ySize) {
            for(x in 1..xSize) {
                if(m?.contains(Vector2(x, y)) ?: false) {
                    out += "+";
                } else {
                    out += getPieceAt(Vector2(x,y))?.ident ?: "*";
                }
                out += " ";
            }
            out += "\n";
        }
        return out;
    }
}