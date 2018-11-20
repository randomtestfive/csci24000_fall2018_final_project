package tmhorne.fairychess.util

class ChessBoard(val xSize: Int, val ySize: Int) {
    val pieces: Set<ChessPiece> = HashSet<ChessPiece>();

    // TODO add ways to add pieces

    fun getPieceAt(pos: Vector2): ChessPiece? {
        return pieces.stream()
                .filter { p -> p.position.equals(pos) }
                .findFirst()
                .orElse(null);
    }
}