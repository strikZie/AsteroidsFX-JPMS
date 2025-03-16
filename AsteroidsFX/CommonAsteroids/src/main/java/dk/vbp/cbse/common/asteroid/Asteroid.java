package dk.vbp.cbse.common.asteroid;

import dk.vbp.cbse.common.data.Entity;

public class Asteroid extends Entity {
    private int speed = 1;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
