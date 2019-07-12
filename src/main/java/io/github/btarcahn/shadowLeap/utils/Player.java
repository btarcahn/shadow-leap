package io.github.btarcahn.shadowLeap.utils;

import org.newdawn.slick.Input;

public class Player extends MovingSprite
        implements Controllable {

    /**
     * Creates a new Sprite object.
     *
     * @param ref the image reference of the Sprite.
     * @param x   the starting x-pos.
     * @param y   the starting y-pos.
     */
    public Player(String ref, float x, float y) {
        super(ref, x, y);
    }

    @Override
    public void accept(Input input) {
        if (input.isKeyPressed(Input.KEY_LEFT)) {
            displace(-width(), 0);
        }

        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            displace(width(), 0);
        }

        if (input.isKeyPressed(Input.KEY_UP)) {
            displace(0, -height());
        }

        if (input.isKeyPressed(Input.KEY_DOWN)) {
            displace(0, height());
        }
    }
}
