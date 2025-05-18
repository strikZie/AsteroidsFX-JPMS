This is a project for SE4-KOM

<h1>Project</h1>

Project is the Asteroids game, with focus on component based software.

the project is structured with the main game implemented on the main branch, the main game is considered as both the intro lab, the GameLab, the JPMS1 lab and the JPMS2 lab,
then subsequent excersizes are implemented on relevant branches with name of the excersize in question.
all of the excersizes have the main game as the base

e.g. the JavaLab is implemented on the branch JavaLab-ServiceLoader
<h1>Current Excersize: TestLab - Collision tests</h1>
The current branch is the implementation of the TestLab where we use Junit Testing and Mockito to mock classes, 

On this branch this is done on the Collision component

MockEntity() method for mocking entities to check collision tests for

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


Example of a test method:

    @Test
    void testIdenticalEntitiesSkipped() {
        Entity e1 = mockEntity("1", 0, 0, 10f, DummyEntity.class);
        Entity e2 = mockEntity("1", 0, 0, 10f, DummyEntity.class); // same ID

        when(world.getEntities()).thenReturn(Arrays.asList(e1, e2));
        collisionEngine.process(world);
        verify(e1, never()).hit(world);
        verify(e2, never()).hit(world);
    }
