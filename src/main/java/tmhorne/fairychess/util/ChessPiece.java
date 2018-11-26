package tmhorne.fairychess.util;

import tmhorne.fairychess.moverule.MoveRule;

import java.util.HashSet;
import java.util.Set;

public class ChessPiece {
    private String name;
    private String ident;
    private Vector2 position;
    private Player player;
    private ChessBoard board;

    private Set<MoveRule> rules;

    private boolean moved;

    public ChessPiece(String name, String ident, Vector2 position,
                      Player player, ChessBoard board, String parlett) {
        // get infos
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

    public void setPosition(Vector2 pos) {
        moved = true;
        this.position = pos;
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

    public Set<Vector2> getPossibleMoves() {
        return rules.stream()
                .map(r -> r.getPossibleMoves(this))
                .reduce((a,b) -> { a.addAll(b); return a; })
                .orElse(new HashSet<Vector2>());
    }
}
