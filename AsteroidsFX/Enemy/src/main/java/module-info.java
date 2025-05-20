import dk.vbp.cbse.enemy.EnemyControlSystem;
import dk.vbp.cbse.enemy.EnemyPlugin;

module Enemy {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    requires spring.context;

    uses dk.vbp.cbse.common.bullet.BulletSPI;

    provides dk.vbp.cbse.common.services.IGamePluginService with EnemyPlugin;
    provides dk.vbp.cbse.common.services.IEntityProcessService with EnemyControlSystem;

    exports dk.vbp.cbse.enemy to spring.beans;
}