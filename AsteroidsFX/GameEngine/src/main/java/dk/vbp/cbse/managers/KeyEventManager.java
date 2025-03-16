package dk.vbp.cbse.managers;

import dk.vbp.cbse.common.data.GameData;
import dk.vbp.cbse.common.data.GameKey;
import dk.vbp.cbse.common.data.Keys;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyEventManager {


    public static void setupKeyEvents(Scene scene, GameData gameData) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getGameKey().setKey(Keys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getGameKey().setKey(Keys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getGameKey().setKey(Keys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getGameKey().setKey(Keys.SPACE, true);
            }
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getGameKey().setKey(Keys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getGameKey().setKey(Keys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getGameKey().setKey(Keys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getGameKey().setKey(Keys.SPACE, false);
            }
        });
    }



}
