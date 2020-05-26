package io.github.btarcahn.shadowleap.gelems;

import org.newdawn.slick.Input;

public class Player extends Sprite {

    private float speed;


    public Player(String pathToImg, float x, float y, FakeScreen screen) {
        super(pathToImg, x, y, screen);
    }

    @Override
    public void setX(float x) {
        try {
           Player fake = (Player) this.clone();
           fake.setX(x);
           if (fake.onscreen()) {
               super.setX(x);
           }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setY(float y) {
        try {
            Player fake = (Player) this.clone();
            fake.setY(y);
            if (fake.onscreen()) {
                super.setY(y);
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void setSpeed(float speed) {
        this.speed = speed;
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
