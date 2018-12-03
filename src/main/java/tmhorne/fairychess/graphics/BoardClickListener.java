package tmhorne.fairychess.graphics;

import tmhorne.fairychess.util.Vector2;

public interface BoardClickListener {
    /**
     * allows another class to listen for when a board is clicked on
     * @param click location of click
     */
    void onBoardClicked(Vector2 click);
}
