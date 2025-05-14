import dk.vbp.cbse.Map;

module AbyssMap {
    requires CommonMap;
    requires javafx.graphics;
    requires Common;

    provides dk.vbp.cbse.common.map.IMap with Map;
}