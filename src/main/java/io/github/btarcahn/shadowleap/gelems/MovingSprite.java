package io.github.btarcahn.shadowleap.gelems;

public abstract class MovingSprite extends Sprite {

    private float speed;
    private Direction direction;

    public MovingSprite(String pathToImg, float x, float y) {
        super(pathToImg, x, y);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }
}
