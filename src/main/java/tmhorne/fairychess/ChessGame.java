package tmhorne.fairychess;

import tmhorne.fairychess.util.ChessBoard;
import tmhorne.fairychess.util.ChessMove;
import tmhorne.fairychess.util.ChessPiece;
import tmhorne.fairychess.util.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * describes a chess game
 */
public class ChessGame {
    private ChessBoard board;
    private Player[] players;
    private int turn;

    /**
     * creates a chess game
     * @param board board to play the game on
     * @param p1 black player
     * @param p2 white player
     */
    public ChessGame(ChessBoard board, Player p1, Player p2) {
        this.board = board;
        this.players = new Player[] { p1, p2 };
        this.turn = 1;
    }

    /**
     * gets the current player
     * @return current player
     */
    public Player getCurrentPlayer() {
        return players[turn % 2];
    }

    public int getTurn() {
        return turn;
    }

    public Player play() {
        Set<ChessPiece> kings = board.getPieces().stream()
                .filter(p -> p.getName().equals("King"))
                .collect(Collectors.toSet());

        Player winner = null;

        // while no one has won
        while(winner == null) {
            ChessMove move = players[turn % 2].getMove();
            board.applyMove(move);

            Set<ChessPiece> removedKings = new HashSet<>();
            removedKings.addAll(kings);
            removedKings.removeAll(board.getPieces());

            // if any kings have been removed
            if(removedKings.size() != 0) {
                // ding ding ding we have a winner

                // get the taken piece
                ChessPiece[] removed = new ChessPiece[1];
                removedKings.toArray(removed);

                // find the winner
                winner = removed[0].getPlayer() == players[0] ? players[1] : players[0];
            }

            turn++;
        }

        return winner;
    }
}
