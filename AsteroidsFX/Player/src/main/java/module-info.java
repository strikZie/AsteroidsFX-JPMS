import dk.vbp.cbse.player.PlayerControlSystem;
import dk.vbp.cbse.player.PlayerPlugin;

module Player {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;

    uses dk.vbp.cbse.common.bullet.BulletSPI;

    provides dk.vbp.cbse.common.services.IGamePluginService with PlayerPlugin;
    provides dk.vbp.cbse.common.services.IEntityProcessService with PlayerControlSystem;
}