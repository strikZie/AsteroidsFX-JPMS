package dk.vbp.cbse;

import dk.vbp.cbse.common.map.IMap;
import dk.vbp.cbse.common.services.IEntityProcessService;
import dk.vbp.cbse.common.services.IGamePluginService;
import dk.vbp.cbse.common.services.IPostProcessService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import java.util.List;


@Configuration
@ComponentScan(basePackages = "dk.vbp.cbse")
public class SpringConfig {
    public SpringConfig() {}

    @Bean
    public Game gameSetup(List<IGamePluginService> pluginServices,
                          List<IEntityProcessService> entityProcessors,
                          List<IPostProcessService> postProcessors,
                          IMap mapService) {
        Game gameInstance = new Game();

        gameInstance.setGamePluginServices(pluginServices);
        gameInstance.setEntityProcessServices(entityProcessors);
        gameInstance.setPostProcessServices(postProcessors);
        gameInstance.setMapService(mapService);

        return gameInstance;
    }
}
