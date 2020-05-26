package io.github.btarcahn.shadowleap.gelems;

import org.newdawn.slick.Input;

public class Player extends Sprite {


    public Player(String pathToImg, float x, float y) {
        super(pathToImg, x, y);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
    }

    @Override
    public void setY(float y) {

        super.setY(y);
    }

    @Override
    public void onCollision() {

    }

    @Override
    public void onCollision(Collidable other) {

    }

    public void update(Input input, int delta) {
        float dx = 0, dy = 0;
        if (input.isKeyPressed(Input.KEY_LEFT)) {
            dx -= this.getWidth();
        }
        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            dx += this.getWidth();
        }
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            dy += this.getHeight();
        }
        if (input.isKeyPressed(Input.KEY_UP)) {
            dy -= this.getHeight();
        }
    }
}
