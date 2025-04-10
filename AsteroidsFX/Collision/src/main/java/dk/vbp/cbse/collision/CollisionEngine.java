package dk.vbp.cbse.collision;

import dk.vbp.cbse.common.data.Entity;
import dk.vbp.cbse.common.data.World;
import dk.vbp.cbse.common.services.IPostProcessService;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class CollisionEngine implements IPostProcessService {
    @Override
    public void process(World world) {
        List<Entity> entities = new ArrayList<>(world.getEntities());
        // two for loops for all entities in the world
        for (Entity entity1 : entities) {
            for (Entity entity2 : entities) {

                // if the two entities are identical, skip
                if (entity1.getId().equals(entity2.getId())) {
                    continue;
                }
                // if classes are indentical, skip
                if (entity1.getClass().equals(entity2.getClass())){
                    continue;
                }

                // CollisionDetection
                if (collides(entity1, entity2)) {
                    entity1.hit(world);
                    entity2.hit(world);
                }
            }
        }

    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getPosition().getX() - (float) entity2.getPosition().getX();
        float dy = (float) entity1.getPosition().getY() - (float) entity2.getPosition().getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getSprite().getWidth()/2 + entity2.getSprite().getWidth()/2);
    }
}
