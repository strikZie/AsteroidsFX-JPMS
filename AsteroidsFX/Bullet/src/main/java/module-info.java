import dk.vbp.cbse.bullet.BulletControlSystem;
import dk.vbp.cbse.bullet.BulletPlugin;

module Bullet {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    requires spring.context;

    provides dk.vbp.cbse.common.services.IGamePluginService with BulletPlugin;
    provides dk.vbp.cbse.common.services.IEntityProcessService with BulletControlSystem;
    provides dk.vbp.cbse.common.bullet.BulletSPI with BulletControlSystem;

    exports dk.vbp.cbse.bullet to spring.beans;
}