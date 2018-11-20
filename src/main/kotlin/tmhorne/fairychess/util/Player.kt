package tmhorne.fairychess.util

class Player(val color: PlayerColor) {
    fun getForward(): Vector2 {
        return when(this.color) {
            PlayerColor.BLACK -> Vector2(0, -1);
            PlayerColor.WHITE -> Vector2(0, 1);
        }
    }
}