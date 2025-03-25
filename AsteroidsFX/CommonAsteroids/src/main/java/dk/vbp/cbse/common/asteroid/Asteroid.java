package dk.vbp.cbse.common.asteroid;

import dk.vbp.cbse.common.data.Entity;
import dk.vbp.cbse.common.data.World;

import java.util.Optional;
import java.util.ServiceLoader;

public class Asteroid extends Entity {
    private int speed = 1;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * used for handling entity getting hit,
     * asteroid: split the asteroid if splitter present.
     * @param world - world to manipulate entities on
     */
    @Override
    public void hit(World world){
        Optional<IAsteroidSplitter> splitter = ServiceLoader.load(IAsteroidSplitter.class).findFirst();
        splitter.ifPresent(iAsteroidSplitter -> iAsteroidSplitter.createSplitAsteroid(this, world));
    }
}
