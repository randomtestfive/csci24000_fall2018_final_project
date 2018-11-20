package tmhorne.fairychess.moverule;

import tmhorne.fairychess.util.ChessBoard;
import tmhorne.fairychess.util.ChessPiece;
import tmhorne.fairychess.util.Player;
import tmhorne.fairychess.util.Vector2;

import java.util.HashSet;
import java.util.Set;

public class LeapRule implements MoveRule {
    private Vector2 leap;
    private Integer length;

    public LeapRule(Vector2 leap, Integer length) {
        this.leap = leap;
        this.length = length;
    }

    public Vector2 getLeap(ChessBoard board, Player player, ChessPiece piece) {
        return leap;
    }

    @Override
    public Set<Vector2> getPossibleMoves(ChessBoard board, Player player, ChessPiece piece) {
        // max length is either the size of the board or the actual length
        int maxLength = this.length == null ?
                Math.max(board.getXSize(), board.getYSize()) :
                this.length;

        Set<Vector2> moves = new HashSet<>();

        // starting from current position
        Vector2 current = piece.getPosition();

        for(int i = 0; i < maxLength; i++) {
            // move current position
            current = current.add(this.getLeap(board, player, piece));

            // check if still on the board
            if(!current.onBoard(board)) {
                break;
            }

            // this ones good
            moves.add(current);
        }

        return moves;
    }
}
