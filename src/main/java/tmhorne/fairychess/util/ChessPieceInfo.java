package tmhorne.fairychess.util;

/**
 * information that a chess piece can be instantiated from
 * @see ChessPiece
 */
public class ChessPieceInfo {
    private String unique;
    private String name;
    private String ident;
    private String parlett;

    public ChessPieceInfo(String unique, String name, String ident, String parlett) {
        this.unique = unique;
        this.name = name;
        this.ident = ident;
        this.parlett = parlett;
    }

    public String getUnique() {
        return unique;
    }

    public String getName() {
        return name;
    }

    public String getIdent() {
        return ident;
    }

    public String getParlett() {
        return parlett;
    }
}
