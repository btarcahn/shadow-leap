package io.github.btarcahn.shadowleap.gelems;

import org.newdawn.slick.Input;

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

    public void update(Input input, int delta) {
        switch (this.direction) {
            case HORIZONTAL:
                moveHorizontally(speed, delta);
                break;
            case VERTICAL:
                moveVertically(speed, delta);
                break;
        }
    }

    private void moveHorizontally(float speed, int delta) {
        this.setX(this.getX() + speed * delta);
    }

    private void moveVertically(float speed, int delta) {
        this.setY(this.getY() + speed * delta);
    }
}
