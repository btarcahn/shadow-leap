package io.github.btarcahn.shadowLeap.utils;


/**
 * Sprite that can move on the game screen.
 * The movement is directed by a speed constant,
 * which can be modified using a setter.
 * MovingSprite may move in four perpendicular
 * directions: UP, DOWN, LEFT, RIGHT.
 * @author Bach Tran
 * @since 2.0
 */
public abstract class MovingSprite extends Sprite {

    private float speed = 0.0f;
    private short horizontal = 0;
    private short vertical = 0;

    @Override
    public void reaction(Interactable obj) {
        // Do nothing...
    }

    public enum Directions {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * Creates a new Sprite object.
     *
     * @param ref the image reference of the Sprite.
     * @param x   the starting x-pos.
     * @param y   the starting y-pos.
     */
    public MovingSprite(String ref, float x, float y) {
        super(ref, x, y);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDir(Directions dir) {
        switch (dir) {
            case UP:
                vertical = -1;
                horizontal = 0;
                break;
            case DOWN:
                vertical = 1;
                horizontal = 0;
            case LEFT:
                vertical = 0;
                horizontal = -1;
            case RIGHT:
                vertical = 0;
                horizontal = 1;
        }
    }



    /**
     * Displaces the Sprite with the amount of (dx, dy).
     * The Sprite new position should be (x + dx, y + dy).
     * @param dx horizontal displacement.
     * @param dy vertical displacement.
     */
    protected void displace(float dx, float dy) {
        setX(x() + dx);
        setY(y() + dy);
    }

    /**
     * Actions shall be taken if the Sprite
     * hits the border of the game screen.
     * @since 2.0
     * @author Bach Tran
     */
    public abstract void onBorder();

    /**
     * Updates the position of the Sprite.
     * This performs a smooth displacement using
     * delta.
     * @param delta time (milliseconds) passes since
     *              last frame.
     * @since 2.0
     */
    public void update(int delta) {
        displace(delta * speed * horizontal, delta * speed * vertical);
    }
}
