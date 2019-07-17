package io.github.btarcahn.shadowLeap.utils;

/**
 * Objects implementing this functional interface
 * may be displayed on the game screen.
 * @author Bach Tran
 * @since 2.0
 */
public interface Renderable {
    /**
     * Renders the object image to the screen.
     */
    void render();
}
