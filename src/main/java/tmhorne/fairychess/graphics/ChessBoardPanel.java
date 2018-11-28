package tmhorne.fairychess.graphics;

import tmhorne.fairychess.util.ChessBoard;
import tmhorne.fairychess.util.ChessPiece;
import tmhorne.fairychess.util.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

public class ChessBoardPanel extends JPanel implements MouseListener {
    private ChessBoard board;
    private Vector2 selection;

    private HashSet<BoardClickListener> listeners;

    public ChessBoardPanel(ChessBoard board) {
        this.board = board;
        this.addMouseListener(this);
        listeners = new HashSet<>();
        this.setPreferredSize(new Dimension(50*board.getXSize(), 50*board.getYSize()));
    }

    public void setSelection(Vector2 select) {
        selection = select;
        this.repaint();
    }

    public Vector2 getSelection() {
        return selection;
    }

    private int getSquareSize() {
        return Math.min(getSize().width/board.getXSize(),
                getSize().height/board.getYSize());
    }

    private void drawCheckers(Graphics2D g2d) {
        Color[] colors = { new Color(0xf0f0f0),
                new Color(0x2f2f2f) };
        int size = getSquareSize();
        for(int y = 0; y < board.getYSize(); y++) {
            for(int x = 0; x < board.getXSize(); x++) {
                g2d.setColor(colors[(x+y+1) % 2]);
                g2d.fillRect(x*size, y*size,
                        size, size);
            }
        }
    }

    private void drawSelection(Graphics2D g2d) {
        int size = getSquareSize();

        if(selection != null && board.getPieceAt(selection) != null) {
            ChessPiece p = board.getPieceAt(selection);
            Set<Vector2> possible = p.getPossibleMoves();
            g2d.setColor(Color.YELLOW);

            for(Vector2 v : possible) {
                g2d.fillRect(v.getX()*size, v.getY()*size,
                        size, size);
            }

            g2d.setColor(Color.RED);
            g2d.fillRect(selection.getX()*size, selection.getY()*size,
                    size, size);
        }
    }

    private void drawPieces(Graphics2D g2d) {
        int size = getSquareSize();
        for(ChessPiece p : board.getPieces()) {
            g2d.drawImage(GraphicsRegistry.getImage(p.getGraphicsName()),
                    p.getPosition().getX()*size,
                    p.getPosition().getY()*size,
                    size, size, null);
        }
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paint");

        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        drawCheckers(g2d);

        drawSelection(g2d);

        drawPieces(g2d);
    }

    public void addBoardClickListener(BoardClickListener listener) {
        listeners.add(listener);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int size = getSquareSize();
        int x = mouseEvent.getX() / size;
        int y = mouseEvent.getY() / size;

        Vector2 click = new Vector2(x, y);

        for(BoardClickListener l : listeners) {
            l.onBoardClicked(click);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}
}
