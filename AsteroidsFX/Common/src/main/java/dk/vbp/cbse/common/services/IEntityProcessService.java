package dk.vbp.cbse.common.services;

import dk.vbp.cbse.common.data.World;

/**
 * interface for processing entity's in update loop.
 */
public interface IEntityProcessService {
    /**
     * process method to run on entity by game loop.
     * @param world - world instance to use in process run.
     */
    void process(World world);
}
