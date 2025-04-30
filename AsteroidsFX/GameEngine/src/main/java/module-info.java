module GameEngine {
    uses dk.vbp.cbse.common.services.IGamePluginService;
    uses dk.vbp.cbse.common.services.IEntityProcessService;
    uses dk.vbp.cbse.common.services.IPostProcessService;
    uses dk.vbp.cbse.common.map.IMap;
    requires Common;
    requires CommonBullet;

    requires javafx.graphics;
    requires javafx.base;
    requires javafx.controls;
    requires CommonMap;

    exports dk.vbp.cbse;
}