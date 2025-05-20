package dk.vbp.cbse;

import dk.vbp.cbse.common.map.IMap;
import dk.vbp.cbse.common.services.IEntityProcessService;
import dk.vbp.cbse.common.services.IGamePluginService;
import dk.vbp.cbse.common.services.IPostProcessService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

@Configuration
public class SpringConfig {
    public SpringConfig() {}

    @Bean
    public Game gameSetup() {
        Game gameInstance = new Game();

        gameInstance.setGamePluginServices(getPluginServiceList());
        gameInstance.setEntityProcessServices(getEntityProcessServiceList());
        gameInstance.setPostProcessServices(getPostProcessServiceList());
        gameInstance.setMapService(getMapService());

        return gameInstance;
    }

    @Bean
    public List<IGamePluginService> getPluginServiceList() {
        return ServiceLoader.load(IGamePluginService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }

    @Bean
    public List<IEntityProcessService> getEntityProcessServiceList() {
        return ServiceLoader.load(IEntityProcessService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }

    @Bean
    public List<IPostProcessService> getPostProcessServiceList() {
        return ServiceLoader.load(IPostProcessService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }

    @Bean
    public IMap getMapService() {
        return ServiceLoader.load(IMap.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .findFirst()
                .orElse(null);
    }

}
