package dk.vbp.cbse.common.data;


import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.UUID;

public class Entity {
    private final UUID id = UUID.randomUUID();
    private Point2D position;
    private double rotation;
    private float scale;
    private Image sprite;

    public String getId() {
        return id.toString();
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }
}
