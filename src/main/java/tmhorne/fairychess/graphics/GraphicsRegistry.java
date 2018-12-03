package tmhorne.fairychess.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * contains all of the graphics
 * chess pieces are preloaded
 * new pieces are loaded out of the pieces directory
 */
public class GraphicsRegistry {
    private static Map<String, BufferedImage> registry = new HashMap<>();

    private GraphicsRegistry() {}

    /**
     * initialize the registry
     * adds chess pieces
     * loads from directory
     */
    public static void init() {
        // reset the registry because why not
        registry = new HashMap<>();

        // load all of the normal chess pieces
        try {
            registry.put("chess.pawn/b", ImageIO.read(
                    GraphicsRegistry.class.getResource("pawn-b.png")));
            registry.put("chess.pawn/w", ImageIO.read(
                    GraphicsRegistry.class.getResource("pawn-w.png")));

            // king is reversed because i did a bad when creating the pngs
            registry.put("chess.king/b", ImageIO.read(
                    GraphicsRegistry.class.getResource("king-w.png")));
            registry.put("chess.king/w", ImageIO.read(
                    GraphicsRegistry.class.getResource("king-b.png")));

            registry.put("chess.rook/b", ImageIO.read(
                    GraphicsRegistry.class.getResource("rook-b.png")));
            registry.put("chess.rook/w", ImageIO.read(
                    GraphicsRegistry.class.getResource("rook-w.png")));

            registry.put("chess.bishop/b", ImageIO.read(
                    GraphicsRegistry.class.getResource("bishop-b.png")));
            registry.put("chess.bishop/w", ImageIO.read(
                    GraphicsRegistry.class.getResource("bishop-w.png")));

            registry.put("chess.knight/b", ImageIO.read(
                    GraphicsRegistry.class.getResource("knight-b.png")));
            registry.put("chess.knight/w", ImageIO.read(
                    GraphicsRegistry.class.getResource("knight-w.png")));

            registry.put("chess.queen/b", ImageIO.read(
                    GraphicsRegistry.class.getResource("queen-b.png")));
            registry.put("chess.queen/w", ImageIO.read(
                    GraphicsRegistry.class.getResource("queen-w.png")));

            // start working with user-defined pieces
            File pieceDirectory = new File("pieces");

            // make it if it isnt there
            pieceDirectory.mkdir();

            // get all of the subdirectories
            File[] pieces = pieceDirectory.listFiles(File::isDirectory);

            // for each subdirectory, get the two images
            for(File piece : pieces) {
                registry.put(piece.getName() + "/w",
                        ImageIO.read(new File(piece, "w.png")));
                registry.put(piece.getName() + "/b",
                        ImageIO.read(new File(piece, "b.png")));
            }

        } catch (IOException e) {
            // eh
            e.printStackTrace();
        }
    }

    /**
     * gets image from its unique graphics name
     * [piecename]/[w or b for color]
     * @param unique graphics name
     * @return drawable image
     */
    public static BufferedImage getImage(String unique) {
        return registry.get(unique);
    }

}
