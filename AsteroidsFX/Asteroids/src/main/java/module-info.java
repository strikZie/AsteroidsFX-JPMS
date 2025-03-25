import dk.vbp.cbse.asteroids.AsteroidControlSystem;
import dk.vbp.cbse.asteroids.AsteroidPlugin;
import dk.vbp.cbse.asteroids.AsteroidSplitter;

module Asteroids {
    requires Common;
    requires CommonAsteroids;
    requires javafx.graphics;


    provides dk.vbp.cbse.common.services.IGamePluginService with AsteroidPlugin;
    provides dk.vbp.cbse.common.services.IEntityProcessService with AsteroidControlSystem;
    provides dk.vbp.cbse.common.asteroid.IAsteroidSplitter with AsteroidSplitter;
}