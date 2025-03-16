package dk.vbp.cbse.common.services;

import dk.vbp.cbse.common.data.World;

public interface IGamePluginService {
    void start(World world);

    void stop(World world);
}
