import dk.vbp.cbse.Map;

module SpaceMap {
    requires CommonMap;
    requires javafx.graphics;
    requires Common;

    provides dk.vbp.cbse.common.map.IMap with Map;
}