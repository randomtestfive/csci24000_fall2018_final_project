package tmhorne.fairychess.util

/**
 * defines an immutable, 2d, integer position
 *
 * its a vector because you can add and multiply
 */
data class Vector2(val x: Int, val y: Int) {
    /**
     * adds two vectors
     * @param op vector to add
     * @return result of adding the two vectors
     */
    fun add(op: Vector2): Vector2 {
        val newX = this.x + op.x;
        val newY = this.y + op.y;
        return Vector2(newX, newY);
    }

    /**
     * multiplies a vector by a scalar
     * @param op scalar
     * @return result of multiplying
     */
    fun multiplyScalar(op: Int): Vector2 {
        val newX = this.x * op;
        val newY = this.y * op;
        return Vector2(newX, newY);
    }

    /**
     * finds if a position is on a chess board
     * @param board chess board to check
     * @return whether the vector is on the board
     */
    fun onBoard(board: ChessBoard): Boolean {
        if(this.x >= 0 && this.y >= 0) {
            if(this.x < board.xSize && this.y < board.ySize) {
                return true;
            }
        }
        return false;
    }
}