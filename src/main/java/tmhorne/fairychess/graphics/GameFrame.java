package tmhorne.fairychess.graphics;

import tmhorne.fairychess.BoardRegistry;
import tmhorne.fairychess.PieceRegistry;
import tmhorne.fairychess.player.DumbPlayer;
import tmhorne.fairychess.util.ChessBoard;
import tmhorne.fairychess.util.Player;
import tmhorne.fairychess.util.PlayerColor;
import tmhorne.fairychess.util.Vector2;

import javax.swing.*;

public class GameFrame extends JFrame implements BoardClickListener {
    private ChessBoardPanel panel;

    public GameFrame() {
        super("Fairy Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Player p1 = new DumbPlayer(PlayerColor.BLACK);
        Player p2 = new DumbPlayer(PlayerColor.WHITE);

        ChessBoard board = BoardRegistry.getBoardFromName("chess", p2, p1);

        panel = new ChessBoardPanel(board);
        panel.addBoardClickListener(this);

        this.add(panel);

        pack();
        setLocationRelativeTo(null);
        repaint();
        setVisible(true);
    }

    @Override
    public void onBoardClicked(Vector2 click) {
        panel.setSelection(click);
    }
}
