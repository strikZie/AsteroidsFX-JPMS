package dk.vbp.cbse.enemy;

import dk.vbp.cbse.common.data.Entity;
import dk.vbp.cbse.common.data.GameData;
import dk.vbp.cbse.common.data.World;
import dk.vbp.cbse.common.services.IGamePluginService;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class EnemyPlugin implements IGamePluginService {
    private GameData gameData = GameData.getInstance();
    private Entity enemy;

    @Override
    public void start(World world) {
        enemy = createEnemy();
        world.addEntity(enemy);
    }

    private Entity createEnemy() {
        Entity newEnemy = new Enemy();
        newEnemy.setSprite(new Image("enemy.png"));
        newEnemy.setScale(1);
        newEnemy.setPosition(new Point2D((double) gameData.getDisplayWidth() /5, (double) gameData.getDisplayHeight() /5));
        return newEnemy;
    }

    @Override
    public void stop(World world) {
        world.removeEntity(enemy);
    }
}
