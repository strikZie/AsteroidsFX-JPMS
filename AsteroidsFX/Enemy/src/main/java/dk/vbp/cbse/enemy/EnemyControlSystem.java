package dk.vbp.cbse.enemy;

import dk.vbp.cbse.common.bullet.BulletSPI;
import dk.vbp.cbse.common.data.Entity;
import dk.vbp.cbse.common.data.GameData;
import dk.vbp.cbse.common.data.KeyAction;
import dk.vbp.cbse.common.data.World;
import dk.vbp.cbse.common.services.IEntityProcessService;
import javafx.geometry.Point2D;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessService {
    @Override
    public void process(World world) {
        Random random = new Random();
        GameData gameData = GameData.getInstance();

        for (Entity entity : world.getEntities(Enemy.class)){
            Enemy enemy = (Enemy) entity;


            if (System.currentTimeMillis() - enemy.getLastRotatedTime() > enemy.getRotateCooldown()) {
                int rotationChange = random.nextInt(360) - 180;
                enemy.setRotation(enemy.getRotation() + rotationChange);
                enemy.setLastRotatedTime(System.currentTimeMillis());
            }

            double angleRad = Math.toRadians(enemy.getRotation() + 90);
            double changeX = -Math.cos(angleRad) * enemy.getSpeed();
            double changeY = -Math.sin(angleRad) * enemy.getSpeed();
            Point2D newPos = enemy.getPosition().add(changeX, changeY);
            enemy.setPosition(newPos);


            long currentTime = System.currentTimeMillis();
            long lastShotTime = enemy.getLastShotTime();
            long shotCooldown = enemy.getShotCooldown();

            if ((currentTime - lastShotTime > shotCooldown)) {
                getBulletSPIs().stream().findFirst().ifPresent(spi -> {
                    world.addEntity(spi.createBullet(enemy));
                    enemy.setLastShotTime(System.currentTimeMillis()); // Update the last shot time
                });

            }

            //enemy is out of bounds at left side
            if (enemy.getPosition().getX() < 0) {
                Point2D correctedPosLeft = enemy.getPosition().add(1, 0);
                enemy.setPosition(correctedPosLeft);
                enemy.setRotation(enemy.getRotation() + 180);
                System.out.println("left");
            }
            //enemy is out of bounds at right side
            if (enemy.getPosition().getX() > gameData.getDisplayWidth()) {
                Point2D correctedPosRight = enemy.getPosition().subtract(1, 0);
                enemy.setPosition(correctedPosRight);
                enemy.setRotation(enemy.getRotation() + 180);
                System.out.println("right");
            }
            //enemy is out of bounds at top side
            if (enemy.getPosition().getY() < 0) {
                Point2D correctedPosTop = enemy.getPosition().add(0, 1);
                enemy.setPosition(correctedPosTop);
                enemy.setRotation(enemy.getRotation() + 180);
                System.out.println("top");
            }
            //enemy is out of bounds at bottom side
            if (enemy.getPosition().getY() > gameData.getDisplayHeight()) {
                Point2D correctedPosBottom = enemy.getPosition().subtract(0, 1);
                enemy.setPosition(correctedPosBottom);
                enemy.setRotation(enemy.getRotation() + 180);
                System.out.println("bottom");
            }

        }

    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}
