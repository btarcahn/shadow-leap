package io.github.btarcahn.shadowleap.gelems;

public class HazardDecorator extends SpriteDecorator {


    public HazardDecorator(Sprite sprite) {
        super(sprite);
    }

    @Override
    public boolean collidesWith(Collidable other) {
        return this.sprite.collidesWith(other);
    }

    @Override
    public void onCollision() {
        this.sprite.onCollision();
    }

    @Override
    public void onCollision(Collidable other) {
        if (other instanceof Player) {
            this.sprite.screen.decreaseLife();
        }
        this.sprite.onCollision(other);
    }
}
