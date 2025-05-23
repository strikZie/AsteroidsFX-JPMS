This is a project for SE4-KOM

<h1>Project</h1>

Project is the Asteroids game, with focus on component based software.

the project is structured with the main game implemented on the main branch, the main game is considered as both intro lab and the GameLab,
then subsequent excersizes are implemented on relevant branches with name of the excersize in question.
all of the excersizes have the main game as the base

e.g. the JavaLab is implemented on the branch JavaLab-ServiceLoader

<h1>Current Excersize: JPMSLab - Module Layers</h1>
The current branch is the implementation of the JPMSLab3 where we use Module layers, 
to avoid the jar hell, by using java's ModuleLayer functionality when serviceloading the components.

On this branch this is done on the two Map componenets : AbyssMap and SpaceMap, via the GameEngine component in the Game.java class.
Here i use the initMap() and loadMap() methods to define hard coded module layers for these two .jar components.
<h2>How to use:</h2>
to run the project, you must first compile with clean install, then manually move AbyssMap and SpaceMap into plugins folder, then mvn exec:exec

<h2>how layers work</h2>
InitMap() method for deciding which map to show
        
    private void initMap() {
        Optional<IMap> map1 = loadMap(Path.of("plugins/AbyssMap-1.0-SNAPSHOT.jar"), "AbyssMap");
        Optional<IMap> map2 = loadMap(Path.of("plugins/SpaceMap-1.0-SNAPSHOT.jar"), "SpaceMap");

        if (map1.isPresent()) {
            map1.ifPresent(iMap -> iMap.drawMap(backgroundLayer));
        } else  {
            map2.ifPresent(iMap -> iMap.drawMap(backgroundLayer));
        }
        return;
    }


loadMap() method for defining module layer and service loading module 

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


