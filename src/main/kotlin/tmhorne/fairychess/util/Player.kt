package tmhorne.fairychess.util

/**
 * defines a player
 */
abstract class Player(val color: PlayerColor) {
    /**
     * finds forward on the board
     */
    fun getForward(): Vector2 {
        return when(this.color) {
            PlayerColor.BLACK -> Vector2(0, 1);
            PlayerColor.WHITE -> Vector2(0, -1);
        }
    }

    /**
     * gets the move the player wants to make
     * @return move to take
     */
    abstract fun getMove(): ChessMove
}