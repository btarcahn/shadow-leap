package io.github.btarcahn.shadowleap.gelems;

public interface Collidable {
    boolean collidesWith(Collidable other);

    void onCollision();

    void onCollision(Collidable other);
}
