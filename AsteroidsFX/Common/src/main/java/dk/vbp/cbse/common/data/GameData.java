package dk.vbp.cbse.common.data;
//singleton class game data
public class GameData {
    private int displayWidth = 800;
    private int displayHeight = 600;
    private final GameKey gameKey = new GameKey();
    private static GameData instance;

    private GameData() {}

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }


    public GameKey getGameKey() {
        return gameKey;
    }


    public int getDisplayHeight() {
        return displayHeight;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }
}
