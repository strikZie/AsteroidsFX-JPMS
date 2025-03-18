import dk.vbp.cbse.collision.CollisionEngine;

module Collision {
    requires Common;
    requires javafx.graphics;

    provides dk.vbp.cbse.common.services.IPostProcessService with CollisionEngine;
}