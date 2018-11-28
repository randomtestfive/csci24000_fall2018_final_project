package tmhorne.fairychess.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GraphicsRegistry {
    private static Map<String, BufferedImage> registry = new HashMap<>();

    public static void init() {
        registry = new HashMap<>();

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getImage(String unique) {
        return registry.get(unique);
    }

}
