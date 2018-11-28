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

    public GameFrame() {
        super("Fairy Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        players = new GraphicsPlayer[] {
                new GraphicsPlayer(PlayerColor.BLACK),
                new GraphicsPlayer(PlayerColor.WHITE)
        };

        board = BoardRegistry.getBoardFromName("chess", players[1], players[0]);

        game = new ChessGame(board, players[0], players[1]);

        panel = new ChessBoardPanel(board);
        panel.addBoardClickListener(this);

        this.add(panel);

        pack();
        setLocationRelativeTo(null);
        repaint();
        setVisible(true);


        // start the game in a new thread
        new Thread(() -> {
            game.play();
        }).start();
    }

    @Override
    public void onBoardClicked(Vector2 click) {
        GraphicsPlayer currentPlayer = players[game.getTurn() % 2];

        if(board.getPieceAt(click) != null && // if theres a piece there
                // and it belongs to the current player
                board.getPieceAt(click).getPlayer() == game.getCurrentPlayer()) {

            currentPlayer.setStart(click);
            panel.setSelection(click);
        } else if (currentPlayer.getStart() != null && // if the player has selected a piece
                // and they clicked somewhere the piece can move
                board.getPieceAt(currentPlayer.getStart()).getPossibleMoves().contains(click)) {
            currentPlayer.setEnd(click);
            // let the other thread do its thing
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            panel.setSelection(null);
        } else {
            panel.setSelection(null);
        }
    }
}
