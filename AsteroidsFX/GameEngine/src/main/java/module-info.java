module GameEngine {
    uses dk.vbp.cbse.common.services.IGamePluginService;
    uses dk.vbp.cbse.common.services.IEntityProcessService;
    uses dk.vbp.cbse.common.services.IPostProcessService;
    requires Common;
    requires CommonBullet;

    requires javafx.graphics;
    requires javafx.base;
    requires javafx.controls;

    exports dk.vbp.cbse;
}