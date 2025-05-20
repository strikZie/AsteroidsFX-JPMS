module GameEngine {
    uses dk.vbp.cbse.common.services.IGamePluginService;
    uses dk.vbp.cbse.common.services.IEntityProcessService;
    uses dk.vbp.cbse.common.services.IPostProcessService;
    uses dk.vbp.cbse.common.map.IMap;
    requires Common;

    requires spring.context;
    requires spring.core;
    requires spring.beans;

    requires javafx.graphics;
    requires javafx.base;
    requires javafx.controls;
    requires CommonMap;

    opens dk.vbp.cbse to spring.core;

    exports dk.vbp.cbse;
}