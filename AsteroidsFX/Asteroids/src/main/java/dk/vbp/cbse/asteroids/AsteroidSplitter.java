package dk.vbp.cbse.asteroids;

import dk.vbp.cbse.common.asteroid.Asteroid;
import dk.vbp.cbse.common.asteroid.IAsteroidSplitter;
import dk.vbp.cbse.common.data.Entity;
import dk.vbp.cbse.common.data.World;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import org.springframework.web.client.RestTemplate;

public class AsteroidSplitter implements IAsteroidSplitter {

    private static RestTemplate restTemplate = new RestTemplate();

    @Override
    public void createSplitAsteroid(Asteroid asteroid, World world) {
        world.removeEntity(asteroid);
        //if is bigger than 1, spawn more: smaller asteroids
        if (asteroid.getScale() > 1){
            for (int i = 0; i <= asteroid.getScale(); i++){
                double rotation = (360 / asteroid.getScale()) * i;

                createSmallAsteroid(world,rotation,asteroid.getPosition());
            }
            //add points to score
            try{
                Long response = restTemplate.getForObject("http://localhost:8080/score/add?point=1", Long.class);
                System.out.println("Score updated successfully: " + response);
            } catch (Exception e) {
                System.err.println("Failed to update score: " + e.getMessage());
            }

        }
    }

    private void createSmallAsteroid(World world,double rotation, Point2D pos) {
        Entity newAsteroid = new Asteroid();
        newAsteroid.setSprite(new Image("asteroid.png"));

        newAsteroid.setScale(1);
        newAsteroid.setPosition(pos);
        newAsteroid.setRotation(rotation);
        world.addEntity(newAsteroid);

    }
}
