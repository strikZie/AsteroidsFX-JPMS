package dk.vbp.cbse.common.services;

import dk.vbp.cbse.common.data.World;

/**
 * interface to use for starting entities like asteroids and player.
 */
public interface IGamePluginService {
    /**
     * method to start the service.
     * @param world - world instance to use in process run.
     */
    void start(World world);

    /**
     * method to stop the service.
     * @param world - world instance to use in process run.
     */
    void stop(World world);
}
