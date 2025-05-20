import dk.vbp.cbse.spacemap.Map;

module SpaceMap {
    requires CommonMap;
    requires javafx.graphics;
    requires Common;
    requires spring.context;

    provides dk.vbp.cbse.common.map.IMap with Map;
    opens dk.vbp.cbse.spacemap to spring.core;



    exports dk.vbp.cbse.spacemap to spring.beans;
}