package dk.vbp.cbse.common.asteroid;

import dk.vbp.cbse.common.data.World;

/**
 * interface for an asteroid splitter, that splits a Asteroid.
 */
public interface IAsteroidSplitter{
    /**
     * method for creating the split asteroids
     * @param asteroid - Asteroid object as extension of Entity
     * @param world - World object to use for spawning and deleting asteroids.
     */
    void createSplitAsteroid(Asteroid asteroid, World world);
}
