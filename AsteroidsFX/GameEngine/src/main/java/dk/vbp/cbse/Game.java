package dk.vbp.cbse;

import dk.vbp.cbse.common.bullet.Bullet;
import dk.vbp.cbse.common.data.Entity;
import dk.vbp.cbse.common.data.GameData;
import dk.vbp.cbse.common.data.GameKey;
import dk.vbp.cbse.common.data.World;
import dk.vbp.cbse.common.services.IEntityProcessService;
import dk.vbp.cbse.common.services.IGamePluginService;
import dk.vbp.cbse.common.services.IPostProcessService;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.ServiceLoader;

import static dk.vbp.cbse.managers.KeyEventManager.setupKeyEvents;
import static java.util.stream.Collectors.toList;

public class Game extends Application {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Pane gamePane;
    private GameData gameData;
    private final World world = new World();

    public static void main(String[] args){
        launch(Game.class);
    }

    @Override
    public void start(Stage stage) throws Exception {

        gameData = GameData.getInstance();


        canvas = new Canvas(gameData.getDisplayWidth(),gameData.getDisplayHeight());
        graphicsContext = canvas.getGraphicsContext2D();




        gamePane = new Pane(canvas);
        Scene scene = new Scene(gamePane);
        setupKeyEvents(scene,gameData);

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : ServiceLoader.load(IGamePluginService.class)) {

            iGamePlugin.start(world);
        }
        for (Entity entity : world.getEntities()) {
            ImageView spriteView = new ImageView(entity.getSprite());
            spriteView.setX(entity.getPosition().getX());
            spriteView.setY(entity.getPosition().getY());
            spriteView.setRotate(entity.getRotation());
            spriteView.setScaleX(entity.getScale());
            spriteView.setScaleY(entity.getScale());
            gamePane.getChildren().add(spriteView);
        }

        setupGameLoop();


        stage.setScene(scene);
        stage.setTitle("AsteroidsFX - JPMS");
        stage.show();



    }


    private void setupGameLoop(){

        new AnimationTimer() {
            @Override
            public void handle(long now) {

                update();
                draw();

            }
        }.start();
    }

    private void update() {
        for (IEntityProcessService entityProcessorService : ServiceLoader.load(IEntityProcessService.class)) {
            entityProcessorService.process(world);
        }

        for (IPostProcessService postProcessorService : ServiceLoader.load(IPostProcessService.class)) {
            postProcessorService.process(world);
        }

        // Remove bullets that move off-screen
        world.getEntities().removeIf(entity ->
                entity instanceof Bullet &&
                        (entity.getPosition().getX() < 0 || entity.getPosition().getX() > gameData.getDisplayWidth() ||
                                entity.getPosition().getY() < 0 || entity.getPosition().getY() > gameData.getDisplayHeight()));
    }

    private void draw(){
        gamePane.getChildren().clear();
        for (Entity entity : world.getEntities()) {
            ImageView spriteView = new ImageView(entity.getSprite());
            spriteView.setX(entity.getPosition().getX());
            spriteView.setY(entity.getPosition().getY());
            spriteView.setRotate(entity.getRotation());
            spriteView.setScaleX(entity.getScale());
            spriteView.setScaleY(entity.getScale());
            gamePane.getChildren().add(spriteView);
        }
    }
    



}
