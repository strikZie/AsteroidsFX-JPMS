package dk.vbp.cbse.enemy;

import dk.vbp.cbse.common.data.Entity;
import dk.vbp.cbse.common.data.World;

public class Enemy extends Entity {
    private double speed = 1; // Adjust movement speed
    private long lastShotTime = 0;

    private long shotCooldown = 1000; //ms

    private long lastRotatedTime = 0;

    private long rotateCooldown = 2000;


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

    public long getLastRotatedTime() {
        return lastRotatedTime;
    }

    public void setLastRotatedTime(long lastRotatedTime) {
        this.lastRotatedTime = lastRotatedTime;
    }

    public long getRotateCooldown() {
        return rotateCooldown;
    }

    @Override
    public void hit(World world) {
        world.removeEntity(this);
        EnemyPlugin enemyPlugin = new EnemyPlugin();
        enemyPlugin.start(world);
    }
}
