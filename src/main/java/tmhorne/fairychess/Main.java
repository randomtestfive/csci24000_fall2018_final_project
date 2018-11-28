package tmhorne.fairychess;

import tmhorne.fairychess.graphics.GameFrame;
import tmhorne.fairychess.graphics.GraphicsRegistry;
import tmhorne.fairychess.player.DumbPlayer;
import tmhorne.fairychess.util.*;

import java.io.File;

/**
 * The main class
 */
public class Main {
    public static void main(String[] args) {
        PieceRegistry.init();
        GraphicsRegistry.init();
        BoardRegistry.init();

        new GameFrame();
    }
}
