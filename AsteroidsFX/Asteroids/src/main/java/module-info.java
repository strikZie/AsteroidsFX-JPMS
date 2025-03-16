import dk.vbp.cbse.asteroids.AsteroidControlSystem;
import dk.vbp.cbse.asteroids.AsteroidPlugin;

module Asteroids {
    requires Common;
    requires CommonAsteroids;
    requires javafx.graphics;


    provides dk.vbp.cbse.common.services.IGamePluginService with AsteroidPlugin;
    provides dk.vbp.cbse.common.services.IEntityProcessService with AsteroidControlSystem;
}