package io.github.btarcahn.shadowLeap.utils;

public class MovingSprite extends Sprite {

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

    /**
     * Displaces the Sprite with the amount of (dx, dy).
     * The Sprite new position should be (x + dx, y + dy).
     * @param dx horizontal displacement.
     * @param dy vertical displacement.
     */
    public void displace(float dx, float dy) {
        setX(x() + dx);
        setY(y() + dy);
    }
}
