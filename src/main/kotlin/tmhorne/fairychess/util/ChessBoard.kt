package tmhorne.fairychess.util

class ChessBoard(val xSize: Int, val ySize: Int) {
    val pieces: HashSet<ChessPiece> = HashSet<ChessPiece>();

    fun getPieceAt(pos: Vector2): ChessPiece? {
        return pieces.stream()
                .filter { p -> p.getPosition().equals(pos) }
                .findFirst()
                .orElse(null);
    }

    fun addPiece(piece: ChessPiece) {
        pieces.add(piece);
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