package tmhorne.fairychess.util;

import tmhorne.fairychess.moverule.MoveRule;

import java.util.HashSet;
import java.util.Set;

/**
 * represents a chess piece in a game of chess
 */
public class ChessPiece {
    private String unique;
    private String name;
    private String ident;
    private Vector2 position;
    private Player player;
    private ChessBoard board;

    private Set<MoveRule> rules;

    private boolean moved;

    /**
     * creates a chess piece
     * @param unique the unique name for the piece
     * @param name the readable name for the piece
     * @param ident a 1 character identifier for the piece
     * @param position starting position
     * @param player player owner
     * @param board board the piece is on
     * @param parlett parlett notation for the piece
     */
    public ChessPiece(String unique, String name, String ident, Vector2 position,
                      Player player, ChessBoard board, String parlett) {
        // get infos
        this.unique = unique;
        this.name = name;
        this.ident = ident;
        this.position = position;
        this.player = player;
        this.board = board;

        // add piece to the board
        board.addPiece(this);

        // find the rules
        this.rules = ParlettParser.getRules(parlett);

        // we havent moved yet
        moved = false;
    }

    /**
     * creates a chess piece using info
     * @param info info that defines the piece
     * @param position starting position
     * @param player player owner
     * @param board board the piece is on
     */
    public ChessPiece(ChessPieceInfo info, Vector2 position, Player player, ChessBoard board) {
        this(info.getUnique(), info.getName(), info.getIdent(), position,
                player, board, info.getParlett());
    }

    public void setPosition(Vector2 pos) {
        moved = true;
        this.position = pos;
    }

    public String getName() {
        return name;
    }

    public Vector2 getPosition() {
        return position;
    }

    public String getIdent() {
        return ident;
    }

    public Player getPlayer() {
        return player;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public boolean getMoved() {
        return moved;
    }

    public String getGraphicsName() {
        return unique + "/" +
                (getPlayer().getColor() == PlayerColor.BLACK ? "b" : "w");
    }

    /**
     * finds possible moves based on board, player, and given rules
     * @return
     */
    public Set<Vector2> getPossibleMoves() {

        return rules.stream()
                .map(r -> r.getPossibleMoves(this))
                .reduce((a,b) -> { a.addAll(b); return a; })
                .orElse(new HashSet<Vector2>());
    }
}
