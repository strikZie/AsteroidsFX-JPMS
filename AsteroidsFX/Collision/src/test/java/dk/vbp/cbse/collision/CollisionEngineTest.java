package dk.vbp.cbse.collision;

import dk.vbp.cbse.common.data.Entity;
import dk.vbp.cbse.common.data.World;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.scene.image.Image;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class CollisionEngineTest {
    private CollisionEngine collisionEngine;
    private World world;

    @BeforeEach
    void setUp() {
        collisionEngine = new CollisionEngine();
        world = mock(World.class);
    }

    private Entity mockEntity(String id, double x, double y, float width, Class<?> clazz) {
        Entity entity = (Entity) mock(clazz);
        when(entity.getId()).thenReturn(id);

        Point2D position = mock(Point2D.class);
        when(position.getX()).thenReturn(x);
        when(position.getY()).thenReturn(y);
        when(entity.getPosition()).thenReturn(position);

        Image image = mock(Image.class);
        when(image.getWidth()).thenReturn((double) width);
        when(entity.getSprite()).thenReturn(image);

        // Spy hit() to verify
        doNothing().when(entity).hit(any(World.class));

        return entity;
    }

    @Test
    void testNoEntities() {
        when(world.getEntities()).thenReturn(Collections.emptyList());
        collisionEngine.process(world);
        // No exceptions should occur
    }

    @Test
    void testOneEntityOnly() {
        Entity e1 = mockEntity("1", 0, 0, 10f, DummyEntity.class);
        when(world.getEntities()).thenReturn(List.of(e1));

        collisionEngine.process(world);
        verify(e1, never()).hit(world);
    }

    @Test
    void testIdenticalEntitiesSkipped() {
        Entity e1 = mockEntity("1", 0, 0, 10f, DummyEntity.class);
        Entity e2 = mockEntity("1", 0, 0, 10f, DummyEntity.class); // same ID

        when(world.getEntities()).thenReturn(Arrays.asList(e1, e2));
        collisionEngine.process(world);
        verify(e1, never()).hit(world);
        verify(e2, never()).hit(world);
    }

    @Test
    void testSameClassEntitiesSkipped() {
        Entity e1 = mockEntity("1", 0, 0, 10f, DummyEntity.class);
        Entity e2 = mockEntity("2", 0, 0, 10f, DummyEntity.class);

        when(world.getEntities()).thenReturn(Arrays.asList(e1, e2));
        collisionEngine.process(world);
        verify(e1, never()).hit(world);
        verify(e2, never()).hit(world);
    }

    @Test
    void testNoCollision() {
        Entity e1 = mockEntity("1", 0, 0, 10f, DummyEntity.class);
        Entity e2 = mockEntity("2", 100, 100, 10f, OtherDummyEntity.class);

        when(world.getEntities()).thenReturn(Arrays.asList(e1, e2));
        collisionEngine.process(world);
        verify(e1, never()).hit(world);
        verify(e2, never()).hit(world);
    }

    @Test
    void testCollisionDetected() {
        Entity e1 = mockEntity("e1", 0, 0, 10f, DummyEntity.class);
        Entity e2 = mockEntity("e2", 5, 0, 10f, OtherDummyEntity.class); // Within collision radius
        List<Entity> entities = List.of(e1, e2);
        when(world.getEntities()).thenReturn(entities);
        collisionEngine.process(world);
        System.out.println("test: " + collisionEngine.collides(e1, e2));
        verify(e1, times(2)).hit(world);
        verify(e2, times(2)).hit(world);
    }

    @Test
    void testMultipleEntitiesMultipleCollisions() {
        Entity e1 = mockEntity("1", 0, 0, 10f, DummyEntity.class);
        Entity e2 = mockEntity("2", 5, 0, 10f, OtherDummyEntity.class); // collides with e1
        Entity e3 = mockEntity("3", 100, 100, 10f, YetAnotherDummyEntity.class); // no collision
        Entity e4 = mockEntity("4", 0, 0, 10f, YetAnotherDummyEntity.class); // collides with e1

        when(world.getEntities()).thenReturn(List.of(e1, e2, e3, e4));

        collisionEngine.process(world);

        verify(e1, times(4)).hit(world);
        verify(e2, times(4)).hit(world);
        verify(e4, times(4)).hit(world);
        verify(e3, never()).hit(world);
    }

    @Test
    void testCollisionMethodEdgeCaseExactTouching() {
        Entity e1 = mockEntity("1", 0, 0, 10f, DummyEntity.class);
        Entity e2 = mockEntity("2", 10, 0, 10f, OtherDummyEntity.class); // exactly touching

        boolean result = collisionEngine.collides(e1, e2);
        assert !result; // touching, but not overlapping
    }

    @Test
    void testCollisionMethodOverlapping() {
        Entity e1 = mockEntity("1", 0, 0, 10f, DummyEntity.class);
        Entity e2 = mockEntity("2", 9, 0, 10f, OtherDummyEntity.class); // overlapping

        boolean result = collisionEngine.collides(e1, e2);
        assert result;
    }

    // Dummy classes for type testing
    private static class DummyEntity extends Entity {}
    private static class OtherDummyEntity extends Entity {}
    private static class YetAnotherDummyEntity extends Entity {}

}
