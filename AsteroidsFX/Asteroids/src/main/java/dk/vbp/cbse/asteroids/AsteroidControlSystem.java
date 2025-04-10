package dk.vbp.cbse.asteroids;

import dk.vbp.cbse.common.asteroid.Asteroid;
import dk.vbp.cbse.common.data.Entity;
import dk.vbp.cbse.common.data.GameData;
import dk.vbp.cbse.common.data.World;
import dk.vbp.cbse.common.services.IEntityProcessService;
import javafx.geometry.Point2D;

public class AsteroidControlSystem implements IEntityProcessService {
    GameData gameData = GameData.getInstance();
    AsteroidPlugin asteroidPlugin = new AsteroidPlugin();
    @Override
    public void process(World world) {
        for (Entity entity : world.getEntities(Asteroid.class)) {
            Asteroid asteroid = (Asteroid) entity;

            double angleRad = Math.toRadians(asteroid.getRotation());

            double changeX = -Math.cos(angleRad) * asteroid.getSpeed();
            double changeY = -Math.sin(angleRad) * asteroid.getSpeed();
            Point2D newPos = asteroid.getPosition().add(changeX, changeY);
            asteroid.setPosition(newPos);


            //enemy is out of bounds at left side
            if (asteroid.getPosition().getX() < 0) {
                world.removeEntity(asteroid);
                asteroidPlugin.start(world);
            }
            //enemy is out of bounds at right side
            if (asteroid.getPosition().getX() > gameData.getDisplayWidth()) {
                world.removeEntity(asteroid);
                asteroidPlugin.start(world);
            }
            //enemy is out of bounds at top side
            if (asteroid.getPosition().getY() < 0) {
                world.removeEntity(asteroid);
                asteroidPlugin.start(world);
            }
            //enemy is out of bounds at bottom side
            if (asteroid.getPosition().getY() > gameData.getDisplayHeight()) {
                world.removeEntity(asteroid);
                asteroidPlugin.start(world);
            }
        }
    }
}
