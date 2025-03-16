package dk.vbp.cbse.player;

import dk.vbp.cbse.common.data.Entity;
import dk.vbp.cbse.common.data.GameData;
import dk.vbp.cbse.common.data.World;
import dk.vbp.cbse.common.services.IGamePluginService;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class PlayerPlugin implements IGamePluginService {
    private GameData gameData = GameData.getInstance();
    private Entity player;

    @Override
    public void start(World world) {
        player = createPlayer();
        world.addEntity(player);
    }

    private Entity createPlayer() {
        Entity newPlayer = new Player();
        newPlayer.setSprite(new Image("player.png"));
        newPlayer.setScale(1);
        newPlayer.setPosition(new Point2D((double) gameData.getDisplayWidth() /2, (double) gameData.getDisplayHeight() /2));
        return newPlayer;
    }

    @Override
    public void stop(World world) {
        world.removeEntity(player);
    }
}
