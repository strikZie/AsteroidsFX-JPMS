package dk.vbp.cbse.player;

import dk.vbp.cbse.common.data.Entity;

public class Player extends Entity {
    private double speed = 5; // Adjust movement speed
    private long lastShotTime = 0;

    private long shotCooldown = 1000; //ms
    public double getSpeed() {
        return speed;
    }

    public long getLastShotTime() {
        return lastShotTime;
    }

    public void setLastShotTime(long lastShotTime) {
        this.lastShotTime = lastShotTime;
    }

    public long getShotCooldown() {
        return shotCooldown;
    }
}
