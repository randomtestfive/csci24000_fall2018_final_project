package tmhorne.fairychess.util

import tmhorne.fairychess.PieceRegistry

class ChessBoard {
    val pieces: HashSet<ChessPiece> = HashSet<ChessPiece>();
    val xSize: Int;
    val ySize: Int;

    constructor(xSize: Int, ySize: Int) {
        this.xSize = xSize;
        this.ySize = ySize;
    }

    constructor(info: ChessBoardInfo, p1: Player, p2: Player) {
        this.xSize = info.xSize;
        this.ySize = info.ySize;

        for((y, line) in info.pieces.withIndex()) {
            for((x, piece) in line.withIndex()) {
                val pieceInfo = piece.split("/");
                addPiece(pieceInfo[0],
                        (if(pieceInfo[1] == "1") p1 else p2),
                        Vector2(x, y));
            }
        }
    }

    fun addPiece(info: String, player: Player, position: Vector2) {
        PieceRegistry.getPiece(info, position, player,this);
    }

    fun getPieceAt(pos: Vector2): ChessPiece? {
        return pieces.stream()
                .filter { p -> p.getPosition().equals(pos) }
                .findFirst()
                .orElse(null);
    }

    fun addPiece(piece: ChessPiece) {
        pieces.add(piece);
    }

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