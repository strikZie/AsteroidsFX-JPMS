package dk.vbp.cbse.bullet;

import dk.vbp.cbse.common.bullet.Bullet;
import dk.vbp.cbse.common.bullet.BulletSPI;
import dk.vbp.cbse.common.data.Entity;
import dk.vbp.cbse.common.data.World;
import dk.vbp.cbse.common.services.IEntityProcessService;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class BulletControlSystem implements IEntityProcessService, BulletSPI {
    @Override
    public void process(World world) {
        for (Entity entity : world.getEntities(Bullet.class)) {
            Bullet bullet = (Bullet) entity;
            // Bullet movement logic (update this inside a game loop)
            double bulletSpeed = bullet.getBulletSpeed();  // Adjust speed as needed
            double angleRad = Math.toRadians(bullet.getRotation());
            double changeX = Math.cos(angleRad) * bulletSpeed;
            double changeY = Math.sin(angleRad) * bulletSpeed;

            bullet.setPosition(new Point2D(bullet.getPosition().getX() + changeX,bullet.getPosition().getY() + changeY));
        }
    }

    @Override
    public Entity createBullet(Entity shooter) {
        Entity bullet = new Bullet();
        bullet.setSprite(new Image("bullet.png"));



        double shooterX = shooter.getPosition().getX();
        double shooterY = shooter.getPosition().getY();


        double bulletSpawnOffset = shooter.getSprite().getWidth() / 2;
        double bulletX = shooterX +  bulletSpawnOffset;
        double bulletY = shooterY +  bulletSpawnOffset;

        bullet.setPosition(new Point2D(bulletX, bulletY));
        bullet.setRotation(shooter.getRotation() - 90);
        bullet.setScale(2);

        return bullet;
    }
}
