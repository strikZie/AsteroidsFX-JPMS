package dk.vbp.cbse.player;

import dk.vbp.cbse.common.bullet.BulletSPI;
import dk.vbp.cbse.common.data.Entity;
import dk.vbp.cbse.common.data.GameData;
import dk.vbp.cbse.common.data.KeyAction;
import dk.vbp.cbse.common.data.World;
import dk.vbp.cbse.common.services.IEntityProcessService;
import javafx.geometry.Point2D;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PlayerControlSystem implements IEntityProcessService {




    public PlayerControlSystem(){

    }

    @Override
    public void process(World world) {
        GameData gameData = GameData.getInstance();

        for (Entity entity : world.getEntities(Player.class)){
            Player player = (Player) entity;
            if(gameData.getGameKey().isDown(gameData.getGameKey().getActionToKey().get(KeyAction.MOVE_LEFT))){
                player.setRotation(player.getRotation() - 2);
            }
            if(gameData.getGameKey().isDown(gameData.getGameKey().getActionToKey().get(KeyAction.MOVE_RIGHT))){
                player.setRotation(player.getRotation() + 2);
            }
            if(gameData.getGameKey().isDown(gameData.getGameKey().getActionToKey().get(KeyAction.MOVE_UP))){

                double angleRad = Math.toRadians(player.getRotation() + 90);
                double changeX = -Math.cos(angleRad) * player.getSpeed();
                double changeY = -Math.sin(angleRad) * player.getSpeed();
                Point2D newPos = player.getPosition().add(changeX, changeY);
                player.setPosition(newPos);
            }
            if(gameData.getGameKey().isDown(gameData.getGameKey().getActionToKey().get(KeyAction.MOVE_DOWN))){

            }
            long currentTime = System.currentTimeMillis();
            long lastShotTime = player.getLastShotTime();
            long shotCooldown = player.getShotCooldown();

            if (gameData.getGameKey().isDown(gameData.getGameKey().getActionToKey().get(KeyAction.SHOOT)) && (currentTime - lastShotTime > shotCooldown)) {
                getBulletSPIs().stream().findFirst().ifPresent(spi -> {
                    world.addEntity(spi.createBullet(player));
                    player.setLastShotTime(System.currentTimeMillis()); // Update the last shot time
                });

            }
        }

    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}
