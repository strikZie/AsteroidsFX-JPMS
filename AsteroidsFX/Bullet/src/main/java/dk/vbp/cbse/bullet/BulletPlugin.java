package dk.vbp.cbse.bullet;

import dk.vbp.cbse.common.bullet.Bullet;
import dk.vbp.cbse.common.data.Entity;
import dk.vbp.cbse.common.data.World;
import dk.vbp.cbse.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {
    @Override
    public void start(World world) {

    }

    @Override
    public void stop(World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }

    }
}
