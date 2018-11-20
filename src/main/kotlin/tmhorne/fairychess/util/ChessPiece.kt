package tmhorne.fairychess.util

import tmhorne.fairychess.moverule.MoveRule
import java.util.*
import java.util.stream.Collectors

class ChessPiece(var position: Vector2,
                 val player: Player,
                 val board: ChessBoard,
                 private val rules: Set<MoveRule>) {

    fun getPossibleMoves(): Set<Vector2> {
        val out = rules.stream()
                .map { r -> r.getPossibleMoves(board, player, this) }
                .reduce { a,b -> a.addAll(b); a }
                .orElse(HashSet<Vector2>());
        return out;
    }
}