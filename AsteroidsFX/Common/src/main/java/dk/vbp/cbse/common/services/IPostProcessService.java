package dk.vbp.cbse.common.services;

import dk.vbp.cbse.common.data.World;

/**
 * interface to process post services, like collision post updating entities.
 * ran by game loop.
 */
public interface IPostProcessService {
    /**
     * method to process post services like collision
     * @param world - world instance to use in process run.
     */
    void process(World world);
}
