package dk.vbp.cbse;


import dk.vbp.cbse.common.data.Entity;
import dk.vbp.cbse.common.data.GameData;
import dk.vbp.cbse.common.data.World;
import dk.vbp.cbse.common.services.IEntityProcessService;
import dk.vbp.cbse.common.services.IGamePluginService;
import dk.vbp.cbse.common.services.IPostProcessService;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import dk.vbp.cbse.common.map.IMap;

import java.util.List;



import static dk.vbp.cbse.managers.KeyEventManager.setupKeyEvents;

public class Game {
    private Pane gamePane;
    private Pane backgroundLayer;
    private Pane entityLayer;
    private GameData gameData;
    private final World world = new World();

    //Spring related service loading variables of services

    private List<IGamePluginService> gamePluginServices;
    private List<IEntityProcessService> entityProcessServices;
    private List<IPostProcessService> postProcessServices;
    private IMap mapService;

    public void start(Stage stage) throws Exception {

        gameData = GameData.getInstance();


        backgroundLayer = new Pane();
        entityLayer = new Pane();
        gamePane = new Pane(backgroundLayer, entityLayer);
        Scene scene = new Scene(gamePane, gameData.getDisplayWidth(), gameData.getDisplayHeight());
        setupKeyEvents(scene,gameData);

        initMap();

        for (IGamePluginService iGamePlugin : gamePluginServices) {
            iGamePlugin.start(world);
        }

        for (Entity entity : world.getEntities()) {
            ImageView spriteView = new ImageView(entity.getSprite());
            spriteView.setX(entity.getPosition().getX());
            spriteView.setY(entity.getPosition().getY());
            spriteView.setRotate(entity.getRotation());
            spriteView.setScaleX(entity.getScale());
            spriteView.setScaleY(entity.getScale());
            entityLayer.getChildren().add(spriteView);
        }

        setupGameLoop();

        stage.setScene(scene);
        stage.setTitle("AsteroidsFX - JPMS");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * initalize map module found.
     */
    private void initMap() {
        if (mapService != null) {
            mapService.drawMap(backgroundLayer);
        }
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
        for (IEntityProcessService entityProcessorService : entityProcessServices) {
            entityProcessorService.process(world);
        }

        for (IPostProcessService postProcessorService : postProcessServices) {
            postProcessorService.process(world);
        }

        // Remove entities that move off-screen
        world.getEntities().removeIf(entity ->
                entity != null &&
                        (entity.getPosition().getX() < 0 || entity.getPosition().getX() > gameData.getDisplayWidth() ||
                                entity.getPosition().getY() < 0 || entity.getPosition().getY() > gameData.getDisplayHeight()));
    }

    private void draw(){
        entityLayer.getChildren().clear();
        for (Entity entity : world.getEntities()) {
            ImageView spriteView = new ImageView(entity.getSprite());
            spriteView.setX(entity.getPosition().getX());
            spriteView.setY(entity.getPosition().getY());
            spriteView.setRotate(entity.getRotation());
            spriteView.setScaleX(entity.getScale());
            spriteView.setScaleY(entity.getScale());
            entityLayer.getChildren().add(spriteView);
        }

    }


    //setters used by spring


    public void setGamePluginServices(List<IGamePluginService> gamePluginServices) {
        this.gamePluginServices = gamePluginServices;
    }

    public void setEntityProcessServices(List<IEntityProcessService> entityProcessServices) {
        this.entityProcessServices = entityProcessServices;
    }

    public void setPostProcessServices(List<IPostProcessService> postProcessServices) {
        this.postProcessServices = postProcessServices;
    }

    public void setMapService(IMap mapService) {
        this.mapService = mapService;
    }
}
