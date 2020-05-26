package io.github.btarcahn.shadowleap.gelems;

public class RideableDecorator extends SpriteDecorator {

    public RideableDecorator(MovingSprite sprite) {
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
            Player player = (Player) other;
            MovingSprite rideable = (MovingSprite) this.sprite;
            player.setSpeed(rideable.getSpeed());
            player.addTag("protected");
        }
        this.sprite.onCollision(other);
    }
}
