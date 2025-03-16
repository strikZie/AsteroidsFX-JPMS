package dk.vbp.cbse.common.bullet;

import dk.vbp.cbse.common.data.Entity;

public interface BulletSPI {
    Entity createBullet(Entity shooter);
}
