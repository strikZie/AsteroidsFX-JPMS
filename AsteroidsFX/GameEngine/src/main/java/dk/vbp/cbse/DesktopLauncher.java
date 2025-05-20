package dk.vbp.cbse;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DesktopLauncher extends Application {
    AnnotationConfigApplicationContext applicationContext;

    public static void main(final String[] args) {
        launch(DesktopLauncher.class);
    }

    @Override
    public void init() throws Exception {
        applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game game = applicationContext.getBean(Game.class);
        game.start(primaryStage);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        applicationContext.close();
    }
}
