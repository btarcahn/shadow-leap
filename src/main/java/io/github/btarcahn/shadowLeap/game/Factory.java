package io.github.btarcahn.shadowLeap.game;

import io.github.btarcahn.shadowLeap.utils.Sprite;

/**
 * Functional interface of the
 * Factory pattern
 * @author Bach Tran
 * @since 2.0
 * @param <T>
 */
interface Factory<T> {
    /**
     * Factory method: initializing the
     * creation sequence.
     * @return a new instance of type T.
     */
    T create();
}

/**
 * Interface for creating Sprites.
 * @param <T> Sprite-types.
 */
interface SpriteFactory<T extends Sprite> {

    /**
     * Creates a new Sprite object at a designated position.
     * @param x_start the horizontal starting position of the Sprite.
     * @param y_start the vertical starting position of the Sprite.
     * @return a new Sprite object.
     */
    T create(float x_start, float y_start);
}
