package io.github.btarcahn.shadowleap.gelems;

/**
 * Caution: PushyDecorator can only decorate on
 * MovingSprite objects only. Using this decorator
 * on Sprite objects does not work.
 * @author Bach Tran
 * @since 1.1
 */
public class PushyDecorator extends SpriteDecorator {

    public PushyDecorator(MovingSprite sprite) {
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

        // Push the player towards the direction
        if (other instanceof Player) {
            Player player = (Player) other;
            MovingSprite movingSprite = (MovingSprite) this.sprite;
            player.setSpeed(movingSprite.getSpeed());
        }

        this.sprite.onCollision(other);
    }
}
