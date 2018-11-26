package tmhorne.fairychess.moverule;

import tmhorne.fairychess.util.*;

import java.util.HashSet;
import java.util.Set;

public class LeapRule implements MoveRule {
    private Vector2 leap;
    private Integer length;
    private MoveModifier modifier;
    private boolean initial;

    public LeapRule(Vector2 leap, MoveModifier modifier, boolean initial, Integer length) {
        this.leap = leap;
        this.modifier = modifier;
        this.initial = initial;
        this.length = length;
    }

    public Vector2 getLeap(ChessPiece piece) {
        return leap;
    }

    @Override
    public Set<Vector2> getPossibleMoves(ChessPiece piece) {
        // max length is either the size of the board or the actual length
        int maxLength = this.length == null ?
                Math.max(piece.getBoard().getXSize(), piece.getBoard().getYSize()) :
                this.length;

        Set<Vector2> moves = new HashSet<>();

        // if its an initial move and we've already moved, get out
        if(initial && piece.getMoved()) {
            return moves;
        }

        // starting from current position
        Vector2 current = piece.getPosition();

        ChessBoard board = piece.getBoard();

        for(int i = 0; i < maxLength; i++) {
            // move current position
            current = current.add(this.getLeap(piece));

            // check if still on the board
            if(!current.onBoard(piece.getBoard())) {
                break;
            }

            ChessPiece pieceThere = board.getPieceAt(current);

            if(modifier == MoveModifier.NONCAPTURE || modifier == MoveModifier.BOTH) {
                if(pieceThere == null) {
                    // if theres no piece there, the move is good
                    moves.add(current);
                }
            }

            if(modifier == MoveModifier.CAPTURE || modifier == MoveModifier.BOTH) {
                // if theres a piece there
                if(pieceThere != null) {
                    // and its an enemy piece
                    if(pieceThere.getPlayer() != piece.getPlayer()) {
                        // that ones good
                        moves.add(current);
                    }
                }
            }
        }

        return moves;
    }
}
