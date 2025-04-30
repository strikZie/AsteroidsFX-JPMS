package dk.vbp.cbse.spacemap;

import dk.vbp.cbse.common.data.GameData;
import dk.vbp.cbse.common.map.IMap;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;

public class Map implements IMap {

    private final URL backgroundUrl = getClass().getResource("/background.jpg");


    @Override
    public void drawMap(Pane pane) {
        GameData gameData = GameData.getInstance();
        ImageView backgroundImg = new ImageView(String.valueOf(backgroundUrl));
        backgroundImg.setFitWidth(gameData.getDisplayWidth());
        backgroundImg.setFitHeight(gameData.getDisplayHeight());
        backgroundImg.setPreserveRatio(false);
        pane.getChildren().add(backgroundImg);
    }
}
