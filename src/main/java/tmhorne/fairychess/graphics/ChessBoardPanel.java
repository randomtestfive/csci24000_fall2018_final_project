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

    /**
     * creates a panel to display a {@link ChessBoard chess board}.
     * @param board board to display
     */
    public ChessBoardPanel(ChessBoard board) {
        this.board = board;
        this.addMouseListener(this);
        listeners = new HashSet<>();
        this.setPreferredSize(new Dimension(50*board.getXSize(), 50*board.getYSize()));
    }

    /**
     * sets the selection of the board
     *
     * only used for rendering selected piece and its moves
     * @param select selected space
     */
    public void setSelection(Vector2 select) {
        selection = select;
        this.repaint();
    }

    public Vector2 getSelection() {
        return selection;
    }

    /**
     * calculates what size a square should be based on the size of the panel
     * @return size to draw squares
     */
    private int getSquareSize() {
        // take the smaller of the number of squares that can fit in either direction
        return Math.min(getSize().width/board.getXSize(),
                getSize().height/board.getYSize());
    }

    /**
     * draws the checkerboard for underneath the game
     * @param g2d graphics object to draw on
     */
    private void drawCheckers(Graphics2D g2d) {
        // define colors
        Color[] colors = { new Color(0xf0f0f0),
                new Color(0x2f2f2f) };

        // get the size
        int size = getSquareSize();

        // go through each x,y position on the board
        for(int y = 0; y < board.getYSize(); y++) {
            for(int x = 0; x < board.getXSize(); x++) {
                // find color for the square
                g2d.setColor(colors[(x+y+1) % 2]);
                // draw rectangle
                g2d.fillRect(x*size, y*size,
                        size, size);
            }
        }
    }

    /**
     * draws the selection graphics
     * @param g2d graphics object to draw on
     */
    private void drawSelection(Graphics2D g2d) {
        // find square size
        int size = getSquareSize();

        // if a piece is selected
        if(selection != null && board.getPieceAt(selection) != null) {
            // get the piece
            ChessPiece p = board.getPieceAt(selection);
            // get its moves
            Set<Vector2> possible = p.getPossibleMoves();
            // prepare to draw possible moves
            g2d.setColor(Color.YELLOW);

            // for each possible move
            for(Vector2 v : possible) {
                // draw a colored rectangle
                g2d.fillRect(v.getX()*size, v.getY()*size,
                        size, size);
            }

            // draw a colored rectange to show the selected piece
            g2d.setColor(Color.RED);
            g2d.fillRect(selection.getX()*size, selection.getY()*size,
                    size, size);
        }
    }

    /**
     * draws all of the pieces on the board
     * @param g2d graphics object to draw on
     */
    private void drawPieces(Graphics2D g2d) {
        // get square size
        int size = getSquareSize();

        // for each piece on the board
        for(ChessPiece p : board.getPieces()) {
            // draw the piece
            g2d.drawImage(GraphicsRegistry.getImage(p.getGraphicsName()),
                    p.getPosition().getX()*size,
                    p.getPosition().getY()*size,
                    size, size, null);
        }
    }

    /**
     * draws the chess board
     * @param g graphics object to draw on
     */
    @Override
    public void paint(Graphics g) {
//        System.out.println("paint");
        // call super
        super.paint(g);

        // convert graphics to graphics 2d
        Graphics2D g2d = (Graphics2D) g;

        // draw everything
        drawCheckers(g2d);

        drawSelection(g2d);

        drawPieces(g2d);
    }

    /**
     * add a listener to the board
     * @param listener listener to add
     */
    public void addBoardClickListener(BoardClickListener listener) {
        listeners.add(listener);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        // get the size
        int size = getSquareSize();
        // get the x and y position on the board
        int x = mouseEvent.getX() / size;
        int y = mouseEvent.getY() / size;

        // create vector
        Vector2 click = new Vector2(x, y);

        // tell each listener about it
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
