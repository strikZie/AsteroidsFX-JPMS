package dk.vbp.cbse.common.map;
import javafx.scene.layout.Pane;

/**
 * interface to use drawing a map on the Pane.
 */
public interface IMap {
    /**
     * method to draw map and add to Pane children.
     * @param gamePane - pane to add to.
     */
    void drawMap(final Pane gamePane);
}
