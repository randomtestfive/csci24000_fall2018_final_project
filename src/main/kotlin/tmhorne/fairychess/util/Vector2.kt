package tmhorne.fairychess.util

data class Vector2(val x: Int, val y: Int) {
    fun add(op: Vector2): Vector2 {
        val newX = this.x + op.x;
        val newY = this.y + op.y;
        return Vector2(newX, newY);
    }

    fun multiplyScalar(op: Int): Vector2 {
        val newX = this.x * op;
        val newY = this.y * op;
        return Vector2(newX, newY);
    }

    fun onBoard(board: ChessBoard): Boolean {
        if(this.x >= 0 && this.y >= 0) {
            if(this.x < board.xSize && this.y < board.ySize) {
                return true;
            }
        }
        return false;
    }
}