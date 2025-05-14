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

import java.lang.module.Configuration;
import java.lang.module.FindException;
import java.lang.module.ModuleFinder;
import java.nio.file.Path;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.Set;

import static dk.vbp.cbse.managers.KeyEventManager.setupKeyEvents;

public class Game extends Application {
    private Pane gamePane;
    private Pane backgroundLayer;
    private Pane entityLayer;
    private GameData gameData;
    private final World world = new World();

    public static void main(String[] args){
        launch(Game.class);
    }

    @Override
    public void start(Stage stage) throws Exception {

        gameData = GameData.getInstance();


        backgroundLayer = new Pane();
        entityLayer = new Pane();
        gamePane = new Pane(backgroundLayer, entityLayer);
        Scene scene = new Scene(gamePane, gameData.getDisplayWidth(), gameData.getDisplayHeight());
        setupKeyEvents(scene,gameData);

        initMap();
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
            entityLayer.getChildren().add(spriteView);
        }

        setupGameLoop();

        stage.setScene(scene);
        stage.setTitle("AsteroidsFX - JPMS");
        stage.setResizable(false);
        stage.show();
    }

    private Optional<IMap> loadMap(Path modulePath, String moduleName) {
        ModuleFinder finder = ModuleFinder.of(modulePath);

        ModuleLayer parent = ModuleLayer.boot();
        Configuration config = null;
        try {
            config = parent.configuration()
                    .resolve(finder, ModuleFinder.of(), Set.of(moduleName));
        } catch (FindException e) {
            System.out.println("Module '" + moduleName + "' not found" + e.getMessage());
            return Optional.empty();
        }


        ClassLoader scl = ClassLoader.getSystemClassLoader();
        ModuleLayer layer = parent.defineModulesWithOneLoader(config, scl);

        ServiceLoader<IMap> loader = ServiceLoader.load(layer, IMap.class);
        return loader.findFirst();
    }


    /**
     * initalize map module found.
     */
    private void initMap() {
        Optional<IMap> map1 = loadMap(Path.of("maps/AbyssMap-1.0-SNAPSHOT.jar"), "AbyssMap");
        Optional<IMap> map2 = loadMap(Path.of("maps/SpaceMap-1.0-SNAPSHOT.jar"), "SpaceMap");

        if (map1.isPresent()) {
            map1.ifPresent(iMap -> iMap.drawMap(backgroundLayer));
        } else  {
            map2.ifPresent(iMap -> iMap.drawMap(backgroundLayer));
        }


        return;
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
    



}
