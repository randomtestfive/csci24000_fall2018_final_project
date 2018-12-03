package tmhorne.fairychess;

import tmhorne.fairychess.graphics.GraphicsRegistry;
import tmhorne.fairychess.graphics.MainMenuFrame;

/**
 * The main class
 */
public class Main {
    public static void main(String[] args) {
        // intialize the registries
        PieceRegistry.init();
        GraphicsRegistry.init();
        BoardRegistry.init();

        // create the main menu
        new MainMenuFrame();
    }
}
