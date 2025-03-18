package dk.vbp.cbse.asteroids;

import dk.vbp.cbse.common.asteroid.Asteroid;
import dk.vbp.cbse.common.data.Entity;
import dk.vbp.cbse.common.data.GameData;
import dk.vbp.cbse.common.data.World;
import dk.vbp.cbse.common.services.IGamePluginService;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {
    private GameData gameData = GameData.getInstance();
    private Entity asteroid;

    private Random random = new Random();

    @Override
    public void start(World world) {
        asteroid = createAsteroid();
        world.addEntity(asteroid);
    }

    private Entity createAsteroid() {
        Entity newAsteroid = new Asteroid();
        newAsteroid.setSprite(new Image("asteroid.png"));

        int size = random.nextInt(1,5);
        newAsteroid.setScale(size);

        Point2D position = new Point2D(0,0);
        int side = random.nextInt(0, 3);
        int rotation = 0;

        //rotation checking to determine where to spawn: 0 = bottom, 1 = right, 2 = top, 3 = left
        //then set rotation to random within relevant range
        if (side == 0) { // bottom
            rotation = random.nextInt(45,135 );
            position = new Point2D(random.nextInt(0, gameData.getDisplayWidth()), gameData.getDisplayHeight());
        }  else if (side == 1){ // right
            rotation = random.nextInt(135,225 );
            position = new Point2D(gameData.getDisplayWidth(), random.nextInt(0, gameData.getDisplayHeight()));
        } else if (side == 2){ // top
            rotation = random.nextInt(225,315 );
            position = new Point2D(random.nextInt(0, gameData.getDisplayWidth()), 0);
        } else if (side == 3){ // left
            rotation = random.nextInt(315,405 );
            position = new Point2D(0, random.nextInt(0, gameData.getDisplayHeight()));
        }
        newAsteroid.setScale(size);
        newAsteroid.setPosition(position);
        newAsteroid.setRotation(rotation);
        return newAsteroid;
    }

    @Override
    public void stop(World world) {
        world.removeEntity(asteroid);
    }
}
