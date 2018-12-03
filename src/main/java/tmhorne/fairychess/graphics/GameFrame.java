package tmhorne.fairychess.graphics;

import tmhorne.fairychess.BoardRegistry;
import tmhorne.fairychess.ChessGame;
import tmhorne.fairychess.PieceRegistry;
import tmhorne.fairychess.player.DumbPlayer;
import tmhorne.fairychess.player.GraphicsPlayer;
import tmhorne.fairychess.util.ChessBoard;
import tmhorne.fairychess.util.Player;
import tmhorne.fairychess.util.PlayerColor;
import tmhorne.fairychess.util.Vector2;

import javax.swing.*;

public class GameFrame extends JFrame implements BoardClickListener {
    private ChessBoardPanel panel;
    private ChessGame game;
    private GraphicsPlayer[] players;
    private ChessBoard board;

    /**
     * create a frame to display a specific chess variant
     * @param name name of the variant
     */
    public GameFrame(String name) {
        // basic setup for jframe
        super("Fairy Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the players
        players = new GraphicsPlayer[] {
                new GraphicsPlayer(PlayerColor.BLACK),
                new GraphicsPlayer(PlayerColor.WHITE)
        };

        // create the board
        board = BoardRegistry.getBoardFromName(name, players[1], players[0]);

        // create the game
        game = new ChessGame(board, players[0], players[1]);

        // create the chess board panel and add self as listener
        panel = new ChessBoardPanel(board);
        panel.addBoardClickListener(this);

        this.add(panel);

        // final setup
        pack();
        setLocationRelativeTo(null);
        repaint();
        setVisible(true);


        // start the game in a new thread
        new Thread(() -> {
            // play the game get the winner
            Player winner = game.play();

            // display winner
            JOptionPane.showMessageDialog(this, winner.getColor() + " WINS!");

            // finish up
            this.setVisible(false);
            this.dispose();
        }).start();
    }

    @Override
    public void onBoardClicked(Vector2 click) {
        // get the current player
        GraphicsPlayer currentPlayer = players[game.getTurn() % 2];

        if(board.getPieceAt(click) != null && // if theres a piece there
                // and it belongs to the current player
                board.getPieceAt(click).getPlayer() == game.getCurrentPlayer()) {

            // set the start of the move to where the click happened
            currentPlayer.setStart(click);
            panel.setSelection(click);
        } else if (currentPlayer.getStart() != null && // if the player has selected a piece
                // and they clicked somewhere the piece can move
                board.getPieceAt(currentPlayer.getStart()).getPossibleMoves().contains(click)) {

            // set the end of the move and actually do it
            currentPlayer.setEnd(click);
            // let the other thread do its thing
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // deselect
            panel.setSelection(null);
        } else {
            // deselect
            panel.setSelection(null);
        }
    }
}
