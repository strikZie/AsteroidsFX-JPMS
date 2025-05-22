package dk.vbp.cbse.common.bullet;

import dk.vbp.cbse.common.data.Entity;
/**
 * service provider interface for creating a bullet.
 */
public interface BulletSPI {
    /**
     * method for creating a bullet Entity.
     * @param shooter - the Entity that shoots the created bullet.
     * @return the bullet Entity created.
     */
    Entity createBullet(Entity shooter);
}
