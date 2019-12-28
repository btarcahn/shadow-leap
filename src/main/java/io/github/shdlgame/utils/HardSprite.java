package io.github.shdlgame.utils;

/**
 * Motionless Sprite that make other movable Sprites
 * become unable to pass through.
 * @author Bach Tran
 * @since 2.0
 */
public class HardSprite extends Sprite {

    /**
     * Creates a new Sprite object.
     *
     * @param ref the image reference of the Sprite.
     * @param x   the starting x-pos.
     * @param y   the starting y-pos.
     */
    public HardSprite(String ref, float x, float y) {
        super(ref, x, y);
    }

    @Override
    public void reaction(Interactable obj) {
        if (obj.getClass().equals(MovingSprite.class)) {
            MovingSprite mSprite = (MovingSprite) obj;
            // TODO implement pushy behavior
        }
    }
}
