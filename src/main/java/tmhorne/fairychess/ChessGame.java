package tmhorne.fairychess;

import tmhorne.fairychess.util.ChessBoard;
import tmhorne.fairychess.util.ChessMove;
import tmhorne.fairychess.util.Player;

public class ChessGame {
    private ChessBoard board;
    private Player[] players;
    private int turn;

    /**
     *
     * @param board
     * @param p1 black player
     * @param p2 white player
     */
    public ChessGame(ChessBoard board, Player p1, Player p2) {
        this.board = board;
        this.players = new Player[] { p1, p2 };
        this.turn = 1;
    }

    public Player getCurrentPlayer() {
        return players[turn % 2];
    }

    public int getTurn() {
        return turn;
    }

    public void play() {
        // TODO check victory
        while(true) {
            ChessMove move = players[turn % 2].getMove();
            board.applyMove(move);
            turn++;
        }
    }
}
