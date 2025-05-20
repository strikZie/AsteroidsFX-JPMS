import dk.vbp.cbse.collision.CollisionEngine;

module Collision {
    requires Common;
    requires javafx.graphics;
    requires spring.context;

    provides dk.vbp.cbse.common.services.IPostProcessService with CollisionEngine;

    exports dk.vbp.cbse.collision to spring.beans;
}